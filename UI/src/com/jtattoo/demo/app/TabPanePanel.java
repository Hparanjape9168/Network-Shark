/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.demo.app;

import com.jtattoo.demo.images.ImageHelper;
import com.jtattoo.demo.utils.GridBagHelper;
import com.jtattoo.plaf.JTattooUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Michael Hagen
 */
public class TabPanePanel extends JPanel {

    private JTabbedPane tabbedPane = null;
    private JCheckBox scrollableTabCheck = null;
    private JRadioButton topTabButton = null;
    private JRadioButton leftTabButton = null;
    private JRadioButton bottomTabButton = null;
    private JRadioButton rightTabButton = null;
    private JButton addTabButton = null;

    public TabPanePanel() {
        super(new BorderLayout());
        init();
    }

    private void init() {
        setName("TabPane");
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JPanel contentPanel = new JPanel(new BorderLayout());
        Color colorYellow = new Color(255, 255, 192);
        Color colorGreen = new Color(192, 255, 192);
        Color colorBlue = new Color(192, 192, 255);

        JPanel htmlPanel = new JPanel();
        JPanel yellowPanel = new JPanel();
        JPanel greenPanel = new JPanel();
        JPanel bluePanel = new JPanel();

        yellowPanel.setBackground(colorYellow);
        greenPanel.setBackground(colorGreen);
        bluePanel.setBackground(colorBlue);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.add("<html><b>HTML</b>-Tab</html>", htmlPanel);
        tabbedPane.setToolTipTextAt(0, "This is a HTML tab");
        tabbedPane.add("Yellow-Tab", yellowPanel);
        tabbedPane.setToolTipTextAt(1, "This tab is yellow");
        tabbedPane.add("Green-Tab", greenPanel);
        tabbedPane.setToolTipTextAt(2, "This tab is green");
        tabbedPane.add("Blue-Tab with a verry long text", bluePanel);
        tabbedPane.setToolTipTextAt(3, "This tab is blue");
        tabbedPane.add("Disabled-Tab", new JPanel());
        tabbedPane.setToolTipTextAt(4, "This is a disabled Tab");
        tabbedPane.setEnabledAt(4, false);

        if (JTattooUtilities.getJavaVersion() >= 1.6) {
            int tabCount = tabbedPane.getTabCount();
            tabbedPane.add("Tab", new JPanel());
            tabbedPane.setTabComponentAt(tabCount, new CloseableTabComponent(tabbedPane, tabCount));
            tabbedPane.setToolTipTextAt(tabCount, "This is tab No. " + (tabCount + 1));
        }

        tabbedPane.setBackgroundAt(1, colorYellow);
        tabbedPane.setForegroundAt(1, Color.red);
        tabbedPane.setBackgroundAt(2, colorGreen);
        tabbedPane.setForegroundAt(2, Color.blue);
        tabbedPane.setBackgroundAt(3, colorBlue);
        tabbedPane.setForegroundAt(3, Color.black);

        JPanel controlerPanel = new JPanel(new GridBagLayout());
        scrollableTabCheck = new JCheckBox("scrollable");
        scrollableTabCheck.setSelected(false);
        scrollableTabCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JTattooUtilities.getJavaVersion() >= 1.4) {
                    if (scrollableTabCheck.isSelected()) {
                        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                    } else {
                        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
                    }
                }
            }
        });

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (ev.getSource().equals(topTabButton)) {
                    tabbedPane.setTabPlacement(JTabbedPane.TOP);
                }
                if (ev.getSource().equals(leftTabButton)) {
                    tabbedPane.setTabPlacement(JTabbedPane.LEFT);
                }
                if (ev.getSource().equals(bottomTabButton)) {
                    tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
                }
                if (ev.getSource().equals(rightTabButton)) {
                    tabbedPane.setTabPlacement(JTabbedPane.RIGHT);
                }
            }
        };
        ButtonGroup group = new ButtonGroup();
        topTabButton = new JRadioButton("top");
        leftTabButton = new JRadioButton("left");
        bottomTabButton = new JRadioButton("bottom");
        rightTabButton = new JRadioButton("right");
        group.add(topTabButton);
        group.add(leftTabButton);
        group.add(bottomTabButton);
        group.add(rightTabButton);
        topTabButton.setSelected(true);
        topTabButton.addActionListener(al);
        leftTabButton.addActionListener(al);
        bottomTabButton.addActionListener(al);
        rightTabButton.addActionListener(al);

        addTabButton = new JButton("add tab");
        addTabButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JTattooUtilities.getJavaVersion() >= 1.6) {
                    int tabCount = tabbedPane.getTabCount();
                    tabbedPane.add("Tab", new JPanel());
                    tabbedPane.setTabComponentAt(tabCount, new CloseableTabComponent(tabbedPane, tabCount));
                    tabbedPane.setToolTipTextAt(tabCount, "This is tab No. " + (tabCount + 1));
                }
            }
        });

        if (JTattooUtilities.getJavaVersion() >= 1.4) {
            GridBagHelper.addComponent(controlerPanel, scrollableTabCheck, 0, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.VERTICAL, GridBagConstraints.WEST);
        }
        GridBagHelper.addComponent(controlerPanel, topTabButton, 1, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.VERTICAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(controlerPanel, leftTabButton, 2, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.VERTICAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(controlerPanel, bottomTabButton, 3, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.VERTICAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(controlerPanel, rightTabButton, 4, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.VERTICAL, GridBagConstraints.WEST);

        GridBagHelper.addComponent(controlerPanel, new JPanel(), 5, 0, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        if (JTattooUtilities.getJavaVersion() >= 1.6) {
            GridBagHelper.addComponent(controlerPanel, addTabButton, 6, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.VERTICAL, GridBagConstraints.WEST);
        }
        contentPanel.add(controlerPanel, BorderLayout.NORTH);
        contentPanel.add(tabbedPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

//        contentPanel.setBackground(Color.blue);

    }

    public static class CloseableTabComponent extends JPanel {

        private static ImageIcon closerImage = ImageHelper.loadImage("closer.gif");
        private static ImageIcon closerRolloverImage = ImageHelper.loadImage("closer_rollover.gif");
        private static ImageIcon closerPressedImage = ImageHelper.loadImage("closer_pressed.gif");
        private JLabel titleLabel = null;
        private JButton closeButton = null;
        private JTabbedPane tabbedPane = null;
        private int tabIndex = -1;

        public CloseableTabComponent(JTabbedPane aTabbedPane, int aTabIndex) {
            super(new BorderLayout());
            tabbedPane = aTabbedPane;
            tabIndex = aTabIndex;

            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));

            titleLabel = new JLabel("close me tab " + tabbedPane.getTabCount() + " ");
            titleLabel.setOpaque(false);

            closeButton = new JButton(closerImage);
            closeButton.setRolloverIcon(closerRolloverImage);
            closeButton.setPressedIcon(closerPressedImage);
            closeButton.setBorderPainted(false);
            closeButton.setBorder(BorderFactory.createEmptyBorder());
            closeButton.setFocusPainted(false);
            closeButton.setRolloverEnabled(true);
            closeButton.setOpaque(false);
            closeButton.setContentAreaFilled(false);
            closeButton.setPreferredSize(new Dimension(closerImage.getIconWidth(), closerImage.getIconHeight()));
            closeButton.setSize(new Dimension(closerImage.getIconWidth(), closerImage.getIconHeight()));
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                        if (CloseableTabComponent.this.equals(tabbedPane.getTabComponentAt(i))) {
                            tabbedPane.removeTabAt(i);
                            break;
                        }
                    }
                }
            });

            add(titleLabel, BorderLayout.CENTER);
            add(closeButton, BorderLayout.EAST);
        }

        public void paint(Graphics g) {
            titleLabel.setForeground(tabbedPane.getForegroundAt(tabIndex));
            if (tabbedPane != null && titleLabel != null && tabIndex >= 0 && tabIndex < tabbedPane.getTabCount()) {
                if (tabbedPane.getSelectedIndex() == tabIndex && tabbedPane.getForegroundAt(tabIndex) instanceof ColorUIResource) {
                    Color c = UIManager.getColor("TabbedPane.selectedForeground");
                    if (c != null) {
                        titleLabel.setForeground(c);
                    }
                }
            }
            super.paint(g);
        }
    }
}
