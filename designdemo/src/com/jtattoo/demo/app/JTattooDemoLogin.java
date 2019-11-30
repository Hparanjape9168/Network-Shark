package com.jtattoo.demo.app;
import com.jtattoo.demo.images.ImageHelper;
import com.jtattoo.plaf.AbstractLookAndFeel;
import com.jtattoo.plaf.JTattooUtilities;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class JTattooDemoLogin extends JFrame implements IDemoApp {

    public static JTattooDemoLogin app1 = new JTattooDemoLogin();
    public static GUIProperties guiProps = new GUIProperties();
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension appSize = new Dimension(880, 660);
    private static final int appPosX = (screenSize.width / 2) - (appSize.width / 2);
    private static final int appPosY = (screenSize.height / 2) - (appSize.height / 2);
    private static Rectangle appBounds = new Rectangle(appPosX, appPosY, appSize.width, appSize.height);
    private static final String appTitle = "Network-Shark 1.0";
    private JWindow splashScreen = null;
    private SplashPanel splashPanel = null;
    private MainMenuBar menuBar = null;
    private MainToolBar toolBar = null;
    private JPanel contentPanel = null;
    private LeftPanel leftPanel = null;
    public RightPanelLogin rightPanel = null;
    private JSplitPane splitPane = null;
    private JTabbedPane mainTabbedPane = null;

    public JTattooDemoLogin() {
        super(appTitle);
      //  init();
    }

    public JTattooDemoLogin(Rectangle bounds) {
        super(appTitle);
        appBounds = bounds;
        //init();
    }

    public void performExit() {
        System.exit(0);
    }

    private void init() {
        // create splash screen
        splashPanel = new SplashPanel();
        splashScreen = new JWindow();
        splashScreen.getContentPane().add(splashPanel);
        splashScreen.pack();
        Dimension size = splashScreen.getSize();
        splashScreen.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);

        // Show the splash screen on the gui thread using invokeLater
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                splashScreen.setVisible(true);
            }
        });

        initModel();
        initMenuBar();
        initToolBar();
        initContentPane();
        initListeners();

        // Show the demo and take down the splash screen. Note that we again must do this on the GUI thread using invokeLater.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showApp();
                if (splashScreen != null) {
                    splashScreen.setVisible(false);
                }
            }
        });
    }

    private void initModel() {
    }

    private void initMenuBar() {
        menuBar = new MainMenuBar(this);
        setJMenuBar(menuBar);
    }

    private void initToolBar() {
        toolBar = new MainToolBar();
        toolBar.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
    }

    private void initContentPane() {
        contentPanel = new JPanel(new BorderLayout());
        leftPanel = new LeftPanel(this);
        rightPanel = new RightPanelLogin(this);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, leftPanel, rightPanel);
        splitPane.setDividerLocation(180);
        splitPane.setOneTouchExpandable(true);
        splitPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
        contentPanel.add(toolBar, BorderLayout.NORTH);
        contentPanel.add(splitPane, BorderLayout.CENTER);
        setContentPane(contentPanel);
    }

    private void initListeners() 
    {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                performExit();
            }
        });
    }

    private void showApp() {
        setIconImage(ImageHelper.loadImage("logo.png").getImage());
        setBounds(appBounds);
        //setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public GUIProperties getGuiProps() {
        return guiProps;
    }

    public void setMainTabbedPane(JTabbedPane tabPane) {
        mainTabbedPane = tabPane;
    }

    public JTabbedPane getMainTabbedPane() {
        return mainTabbedPane;
    }
    public void demologinclose()
   {
     app1.setVisible(false);
   }
    
     public void demologinopen()
   {
     app1.setVisible(true);
   }
  public void demodispose()
  {
      app1.dispose();
  }

    private void updateComponentTree() {
        // Update the application
        getRootPane().updateUI();
        if (JTattooUtilities.getJavaVersion() >= 1.6) {
            Window windows[] = Window.getWindows();
            for (int i = 0; i < windows.length; i++) {
                if (windows[i].isDisplayable()) {
                    SwingUtilities.updateComponentTreeUI(windows[i]);
                }
            }
        } else {
            Frame frames[] = Frame.getFrames();
            for (int i = 0; i < frames.length; i++) {
                if (frames[i].isDisplayable()) {
                    SwingUtilities.updateComponentTreeUI(frames[i]);
                }
            }
        }
        menuBar.updateLookAndFeel();
        leftPanel.updateLookAndFeel();
        rightPanel.updateLookAndFeel();
    }

    public void updateLookAndFeel(String lf) {
        try {
            // If new look handles the WindowDecorationStyle not in the same manner as the old look
            // we have to reboot our application.

            LookAndFeel oldLAF = UIManager.getLookAndFeel();
            boolean oldDecorated = false;
            if (oldLAF instanceof MetalLookAndFeel) {
                oldDecorated = true;
            }
            if (oldLAF instanceof AbstractLookAndFeel) {
                oldDecorated = AbstractLookAndFeel.getTheme().isWindowDecorationOn();
            }

            // reset to default theme
            if (lf.equals(GUIProperties.PLAF_METAL)) {
                javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(new javax.swing.plaf.metal.DefaultMetalTheme());
            } else if (lf.equals(GUIProperties.PLAF_ACRYL)) {
                com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_AERO)) {
                com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_ALUMINIUM)) {
                com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_BERNSTEIN)) {
                com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_FAST)) {
                com.jtattoo.plaf.fast.FastLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_GRAPHITE)) {
                com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_HIFI)) {
                com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_LUNA)) {
                com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_MCWIN)) {
                com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_MINT)) {
                com.jtattoo.plaf.mint.MintLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_NOIRE)) {
                com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_SMART)) {
                com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_TEXTURE)) {
                com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme("Default");
            } else if (lf.equals(GUIProperties.PLAF_CUSTOM)) {
//                com.jtattoo.plaf.custom.flx.FLXLookAndFeel.setTheme("Default");
//                com.jtattoo.plaf.custom.quantycarlo.DarkRoastLookAndFeel.setTheme("Default");
//                com.jtattoo.plaf.custom.systemx.SystemXLookAndFeel.setTheme("Default");
            }
            guiProps.setTheme("Default");
            guiProps.setLookAndFeel(lf);
            UIManager.setLookAndFeel(guiProps.getLookAndFeel());

            LookAndFeel newLAF = UIManager.getLookAndFeel();
            boolean newDecorated = false;
            if (newLAF instanceof MetalLookAndFeel) {
                newDecorated = true;
            }
            if (newLAF instanceof AbstractLookAndFeel) {
                newDecorated = AbstractLookAndFeel.getTheme().isWindowDecorationOn();
            }
            if (oldDecorated != newDecorated) {
                // Reboot the application
                Rectangle savedBounds = getBounds();
                app1.dispose();
                app1 = new JTattooDemoLogin(savedBounds);
                app1.setBounds(savedBounds);
            } else {
                updateComponentTree();
            }
        } catch (Exception ex) {
            System.out.println("Failed loading L&F: " + guiProps.getLookAndFeel() + " Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void updateTheme(String theme) {
        if (theme != null) {
            try {
                guiProps.setTheme(theme);
                LookAndFeel laf = UIManager.getLookAndFeel();
                if (laf instanceof com.jtattoo.plaf.acryl.AcrylLookAndFeel) {
                    com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.aero.AeroLookAndFeel) {
                    com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.aluminium.AluminiumLookAndFeel) {
                    com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.bernstein.BernsteinLookAndFeel) {
                    com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.fast.FastLookAndFeel) {
                    com.jtattoo.plaf.fast.FastLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.graphite.GraphiteLookAndFeel) {
                    com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.hifi.HiFiLookAndFeel) {
                    com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.luna.LunaLookAndFeel) {
                    com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.mcwin.McWinLookAndFeel) {
                    com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.mint.MintLookAndFeel) {
                    com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.noire.NoireLookAndFeel) {
                    com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.smart.SmartLookAndFeel) {
                    com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme(theme);
                } else if (laf instanceof com.jtattoo.plaf.texture.TextureLookAndFeel) {
                    com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(theme);
                } else if (laf instanceof javax.swing.plaf.metal.MetalLookAndFeel) {
                    javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(new javax.swing.plaf.metal.DefaultMetalTheme());
                }

                // Must set look and feel so changes takes effect in all components.
                UIManager.setLookAndFeel(guiProps.getLookAndFeel());
                updateComponentTree();
                
            } catch (Exception ex) {
                System.out.println("Failed setting theme! Exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void setLookAndFeelFlag(String propName, boolean flag) {
        try {
            LookAndFeel laf = UIManager.getLookAndFeel();
            String themeName = guiProps.getTheme();
            Properties props = null;
            if (laf instanceof com.jtattoo.plaf.acryl.AcrylLookAndFeel) {
                props = com.jtattoo.plaf.acryl.AcrylLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.acryl.AcrylLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.aero.AeroLookAndFeel) {
                props = com.jtattoo.plaf.aero.AeroLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.aero.AeroLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.aluminium.AluminiumLookAndFeel) {
                props = com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.bernstein.BernsteinLookAndFeel) {
                props = com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.fast.FastLookAndFeel) {
                props = com.jtattoo.plaf.fast.FastLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.fast.FastLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.graphite.GraphiteLookAndFeel) {
                props = com.jtattoo.plaf.graphite.GraphiteLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.hifi.HiFiLookAndFeel) {
                props = com.jtattoo.plaf.hifi.HiFiLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.hifi.HiFiLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.luna.LunaLookAndFeel) {
                props = com.jtattoo.plaf.luna.LunaLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.luna.LunaLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.mcwin.McWinLookAndFeel) {
                props = com.jtattoo.plaf.mcwin.McWinLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.mcwin.McWinLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.mint.MintLookAndFeel) {
                props = com.jtattoo.plaf.mint.MintLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.mint.MintLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.noire.NoireLookAndFeel) {
                props = com.jtattoo.plaf.noire.NoireLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.noire.NoireLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.smart.SmartLookAndFeel) {
                props = com.jtattoo.plaf.smart.SmartLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.smart.SmartLookAndFeel.setCurrentTheme(props);
            } else if (laf instanceof com.jtattoo.plaf.texture.TextureLookAndFeel) {
                props = com.jtattoo.plaf.texture.TextureLookAndFeel.getThemeProperties(themeName);
                props.put(propName, flag ? "on" : "off");
                com.jtattoo.plaf.texture.TextureLookAndFeel.setCurrentTheme(props);
            }
            if (props != null) {
                UIManager.setLookAndFeel(laf);
                updateComponentTree();
            }
        } catch (Exception ex) {
            System.out.println("Failed setting theme! Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        try {
            ToolTipManager.sharedInstance().setInitialDelay(500);
            ToolTipManager.sharedInstance().setDismissDelay(60000);
            ToolTipManager.sharedInstance().setReshowDelay(0);
            //ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
            //UIManager.put("ScrollBar.squareButtons", Boolean.TRUE);
            //UIManager.put("ScrollBar.incrementButtonGap", new Integer(-1));
            //UIManager.put("ScrollBar.decrementButtonGap", new Integer(-1));
            
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
            UIManager.setLookAndFeel(guiProps.getLookAndFeel());
            app1.init();
            //app1 = new JTattooDemoLogin();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
