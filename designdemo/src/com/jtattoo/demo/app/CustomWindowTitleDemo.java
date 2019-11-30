/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.jtattoo.demo.app;

import com.jtattoo.demo.images.ImageHelper;
import com.jtattoo.plaf.BaseRootPaneUI;
import com.jtattoo.plaf.BaseTitlePane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomWindowTitleDemo extends JFrame {

    private JTitleCheckBox checkBox = null;
    private JTitleButton button = null;
    private JTitleMenuButton menuButton = null;

//------------------------------------------------------------------------------
// inner classes
//------------------------------------------------------------------------------
    public class JTitleCheckBox extends JCheckBox {

        public JTitleCheckBox(String title) {
            super(title);
            setForeground(UIManager.getColor("activeCaptionText"));
            setOpaque(false);
            setFocusable(false);
        }
    }

//------------------------------------------------------------------------------
    public class JTitleButton extends JButton {

        public JTitleButton(String title) {
            super(title);
            setFocusable(false);
        }

    }

//------------------------------------------------------------------------------
    public class JTitleMenuButton extends JButton {

        public JTitleMenuButton(String title) {
            super(title, ImageHelper.loadImage("DownArrow.gif"));

            setHorizontalAlignment(SwingConstants.CENTER);
            setHorizontalTextPosition(SwingConstants.LEFT);
            setForeground(UIManager.getColor("activeCaptionText"));
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusable(false);

            addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent e) {
                    setForeground(Color.black);
                    setContentAreaFilled(true);
                }

                public void mouseExited(MouseEvent e) {
                    setForeground(UIManager.getColor("activeCaptionText"));
                    setContentAreaFilled(false);
                }
            });

            addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    showPopup();
                }
            });
        }

        private void showPopup() {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("Menu #1");
            menuItem.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(CustomWindowTitleDemo.this, "Menu #1 clicked");
                }
            });
            popupMenu.add(menuItem);
            menuItem = new JMenuItem("Menu #2");
            menuItem.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(CustomWindowTitleDemo.this, "Menu #2 clicked");
                }
            });
            popupMenu.add(menuItem);
            menuItem = new JMenuItem("Menu #3");
            menuItem.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(CustomWindowTitleDemo.this, "Menu #3 clicked");
                }
            });
            popupMenu.add(menuItem);
            popupMenu.show(this, 0, getHeight() - 1);
        }

    }
//------------------------------------------------------------------------------
    public class TitlePaneLayout implements LayoutManager {

        public void addLayoutComponent(String name, Component comp) {
        }

        public void removeLayoutComponent(Component comp) {
        }

        public Dimension preferredLayoutSize(Container parent) {
            return parent.getSize();
        }

        public Dimension minimumLayoutSize(Container parent) {
            return parent.getMinimumSize();
        }

        public void layoutContainer(Container parent) {
            int width = 0;
            int count = parent.getComponentCount();
            int w[] = new int[count];
            for (int i = 0; i < count; i++) {
                Component c = parent.getComponent(i);
                if (c.isVisible()) {
                    w[i] = c.getPreferredSize().width;
                    width += w[i];
                    width += 4;
                }
            }
            int x = parent.getWidth() - width;
            if (!parent.getComponentOrientation().isLeftToRight()) {
                x = 0;
            }
            int y = 0;
            int h = parent.getHeight() - 2;
            for (int i = 0; i < count; i++) {
                Component c = parent.getComponent(i);
                if (c.isVisible()) {
                    c.setBounds(x, y, w[i], h);
                    x += w[i] + 4;
                }
            }
        }

    }

//------------------------------------------------------------------------------

    public CustomWindowTitleDemo() {
        super("CustomWindowTitleSample");

        // setup menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        JMenuItem menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic('x');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        menu.add(menuItem);

        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // setup widgets
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
        JScrollPane westPanel = new JScrollPane(new JTree());
        JEditorPane editor = new JEditorPane("text/plain", "Hello World");
        JScrollPane eastPanel = new JScrollPane(editor);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, westPanel,eastPanel);
        splitPane.setDividerLocation(148);
        contentPanel.add(splitPane, BorderLayout.CENTER);
        setContentPane(contentPanel);

        // controls for title pane
        checkBox = new JTitleCheckBox("Check");
        button = new JTitleButton("Button");
        menuButton = new JTitleMenuButton("Menu");

        // add listeners
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        checkBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CustomWindowTitleDemo.this, "CheckBox clicked");
            }
        });
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CustomWindowTitleDemo.this, "Button clicked");
            }
        });

        // show application
        setLocation(32, 32);
        setSize(800, 600);
        setVisible(true);

    } // end CTor MinFrame

    public void customizeTitlePanel() {
        if (getRootPane().getUI() instanceof BaseRootPaneUI) {
            BaseRootPaneUI rootPaneUI = (BaseRootPaneUI)getRootPane().getUI();
            TitlePaneLayout layout = new TitlePaneLayout();
            JPanel panel = new JPanel(layout);
            panel.setOpaque(false);
            panel.add(checkBox);
            panel.add(button);
            panel.add(menuButton);
            BaseTitlePane tp = (BaseTitlePane)rootPaneUI.getTitlePane();
            tp.setCustomizedTitlePanel(panel);
        }
    }

    public static void main(String[] args) {
        try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            // start application
            CustomWindowTitleDemo frame = new CustomWindowTitleDemo();
            frame.customizeTitlePanel();
            //frame.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    } // end main

}