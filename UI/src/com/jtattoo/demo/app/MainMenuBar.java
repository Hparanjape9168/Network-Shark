/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.demo.app;


import com.jtattoo.demo.images.ImageHelper;
import com.jtattoo.demo.utils.GridBagHelper;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author Michael Hagen
 */
public class MainMenuBar extends JMenuBar {

    private static final ImageIcon newIcon = ImageHelper.loadImage("new.png");
    private static final ImageIcon openIcon = ImageHelper.loadImage("open.png");
    private static final ImageIcon saveIcon = ImageHelper.loadImage("save.png");
    private static final ImageIcon filterIcon = ImageHelper.loadImage("filter.png");
    private Component parent = null;
    private IDemoApp demoApp = null;
    private ButtonGroup plafGroup = null;

//    private JMenuItem coloredMenuItem = null;
//    private JCheckBoxMenuItem coloredCheckMenuItem = null;
    public MainMenuBar(Component aParent) {
        parent = aParent;
        demoApp = (IDemoApp) parent;
        plafGroup = new ButtonGroup();

        //setBorderPainted(true);
        JMenu subMenu = new JMenu("Submenu");
        JMenuItem subMenuItem = new JMenuItem("Submenu one");
        //subMenu.add(subMenuItem);
        subMenuItem = new JMenuItem("Submenu two");
        // subMenu.add(subMenuItem);

        JMenu panelMenu = new JMenu("Panelmenu");
        panelMenu.add(createMenuPanel());

        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        JMenuItem menuItem = new JMenuItem("New", newIcon);
        menuItem.setMnemonic('N');
        menuItem.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK));
        //  menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JDialog dlg = new JDialog(JTattooDemo.app, "Modeless-Demo-Dialog", false);
                dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dlg.setSize(400, 300);
                dlg.setLocationRelativeTo(JTattooDemo.app);
                JButton changeTitleButton = new JButton("changeTitle");
                changeTitleButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dlg.setTitle("New Title");
                    }
                });
                JButton closeButton = new JButton("close");
                closeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dlg.dispose();
                    }
                });
                JPanel contentPanel = new JPanel(new BorderLayout());
                JPanel buttonPanel = new JPanel(new BorderLayout());
                buttonPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                buttonPanel.add(changeTitleButton, BorderLayout.NORTH);
                buttonPanel.add(Box.createRigidArea(new Dimension(120, 20)), BorderLayout.WEST);
                buttonPanel.add(closeButton, BorderLayout.CENTER);
                buttonPanel.add(Box.createRigidArea(new Dimension(120, 20)), BorderLayout.EAST);
                contentPanel.add(buttonPanel, BorderLayout.SOUTH);
                dlg.setContentPane(contentPanel);
                dlg.setVisible(true);
            }
        });

        menuItem = new JMenuItem("Open...", openIcon);

//        coloredMenuItem = menuItem;
//        coloredMenuItem.setForeground(Color.blue);
//        coloredMenuItem.setBackground(Color.orange);

        menuItem.setMnemonic('O');
        menuItem.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_MASK));
        //  menu.add(menuItem);
        menuItem = new JMenuItem("Save...", saveIcon);
        menuItem.setMnemonic('S');
        menuItem.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
        // menu.add(menuItem);
        //menu.add(new JSeparator());
        //menu.addSeparator();
        //   menu.add(subMenu);
        //menu.addSeparator();
        // menu.add(panelMenu);
        //menu.addSeparator();
        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic('x');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                JTattooDemoLogin obj = new JTattooDemoLogin();
                obj.demologinopen();
                
                 // JTattooDemo obj1 = new JTattooDemo();
                  //obj1.democlose();
                
                 demoApp.performExit();
            }
        });
        menu.add(menuItem);
        add(menu);

        userlogin user = null;
        try {
            user = new userlogin();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMenuBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainMenuBar.class.getName()).log(Level.SEVERE, null, ex);
        }

//        if (user.rs) {
//            menuItem = new JMenuItem("Logout");
//            menuItem.setMnemonic('L');
//            menu.add(menuItem);
//            menuItem.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                    JTattooDemo obj1 = new JTattooDemo();
//                    obj1.democlose();
//
//                    JTattooDemoLogin obj = new JTattooDemoLogin();
//                    obj.demologinopen();
//                    System.out.println("Logout click done---!!!");
//
//                }
//            });
//        }
       // add(menu);

        menu = new JMenu("Radio");
        menu.setMnemonic('R');
        JRadioButtonMenuItem radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem selected", true);
        //  menu.add(radioMenuItem);
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem unselected", false);
        //  menu.add(radioMenuItem);
        menu.addSeparator();
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem selected disabled", true);
        radioMenuItem.setEnabled(false);
        //  menu.add(radioMenuItem);
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem unselected disabled", false);
        radioMenuItem.setEnabled(false);
        //   menu.add(radioMenuItem);
        menu.addSeparator();
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem selected", filterIcon, true);
        //    menu.add(radioMenuItem);
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem unselected", filterIcon, false);
        //   menu.add(radioMenuItem);
        menu.addSeparator();
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem selected disabled", filterIcon, true);
        radioMenuItem.setEnabled(false);
        //   menu.add(radioMenuItem);
        radioMenuItem = new JRadioButtonMenuItem("RadioButtonMenuItem unselected disabled", filterIcon, false);
        radioMenuItem.setEnabled(false);
        //    menu.add(radioMenuItem);

        //   add(menu);
        //for look & feel
        menu = new JMenu("Themes");
        menu.setMnemonic('L');
        radioMenuItem = new JRadioButtonMenuItem("Metal");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_METAL);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isMetalLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Nimbus");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_NIMBUS);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isNimbusLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Motif");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_MOTIF);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isMotifLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("System");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_SYSTEM);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isSystemLook());
        radioMenuItem.setEnabled(true);
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        menu.addSeparator();

        radioMenuItem = new JRadioButtonMenuItem("Acryl");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_ACRYL);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isAcrylLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Aero");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_AERO);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isAeroLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Aluminium");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_ALUMINIUM);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isAluminiumLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Bernstein");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_BERNSTEIN);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isBernsteinLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Fast");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_FAST);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isFastLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Graphite");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_GRAPHITE);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isGraphiteLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("HiFi");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_HIFI);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isHiFiLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Luna");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_LUNA);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isLunaLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("McWin");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_MCWIN);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isMcWinLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Mint");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_MINT);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isMintLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Noire");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_NOIRE);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isNoireLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Smart");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_SMART);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isSmartLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Texture");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                demoApp.updateLookAndFeel(GUIProperties.PLAF_TEXTURE);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isTextureLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        if (GUIProperties.isCustomEnabled()) {
            menu.addSeparator();
            radioMenuItem = new JRadioButtonMenuItem("Custom");
            radioMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    demoApp.updateLookAndFeel(GUIProperties.PLAF_CUSTOM);
                }
            });
            radioMenuItem.setSelected(demoApp.getGuiProps().isCustomLook());
            plafGroup.add(radioMenuItem);
            menu.add(radioMenuItem);
        }
        add(menu);

        //for display database   
        menu = new JMenu("Database");
        radioMenuItem = new JRadioButtonMenuItem("Process");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
//                NewClass frame = new NewClass();
//                frame.pack();
//                frame.setVisible(true);
            }
        });
        radioMenuItem.setSelected(demoApp.getGuiProps().isMetalLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);
        // add(menu);

        //for help menu
        menu = new JMenu("Help");
        menu.setMnemonic('H');
        menuItem = new JMenuItem("Content...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HelpDialog dlg = new HelpDialog(parent);
            }
        });
        menuItem.setMnemonic('C');
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("About...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutDialog dlg = new AboutDialog(parent);
            }
        });
        menuItem.setMnemonic('A');
        menu.add(menuItem);
        add(menu);

    }

    private JPanel createMenuPanel() {
        GridBagLayout layout = new GridBagLayout();
        JPanel panel = new JPanel(layout);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(100, 120));
        GridBagHelper.addComponent(panel, new JButton("Button #1"), 0, 1, 1, 1, 16, 8, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(panel, new JButton("Button #2"), 0, 2, 1, 1, 16, 8, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(panel, new JButton("Button #3"), 0, 3, 1, 1, 16, 8, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        return panel;
    }

    public void updateLookAndFeel() {
//        coloredMenuItem.setForeground(Color.blue);
//        coloredMenuItem.setBackground(Color.orange);
//        coloredCheckMenuItem.setForeground(Color.blue);
//        coloredCheckMenuItem.setBackground(Color.orange);
    }
}
