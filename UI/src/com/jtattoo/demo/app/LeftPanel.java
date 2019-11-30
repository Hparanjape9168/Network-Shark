/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.jtattoo.demo.app;

import com.jtattoo.border.JTBorderFactory;
import com.jtattoo.demo.images.ImageHelper;
import com.jtattoo.demo.utils.GridBagHelper;
import com.jtattoo.plaf.AbstractLookAndFeel;
import com.jtattoo.plaf.JTattooUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Michael Hagen
 */
public class LeftPanel extends JPanel {

    private Component parent = null;
    private IDemoApp demoApp = null;
    private JScrollPane treePanel = null;
    private JScrollPane listPanel = null;
    private JScrollPane controlPanel = null;
    private JList themesList = null;
   // private JCheckBox taaCheck = null;
    private JCheckBox leftToRightCheck = null;
   // private JCheckBox scrollableTabCheck = null;
   // private JCheckBox patternCheck = null;
    private JCheckBox centerWindowTitleCheck = null;
    private JCheckBox macStyleWindowDecorationCheck = null;
    private JCheckBox linuxStyleScrollbarsCheck = null;
    
    private JRadioButton topTabButton = null;
    private JRadioButton leftTabButton = null;
    private JRadioButton bottomTabButton = null;
    private JRadioButton rightTabButton = null;
    private JSplitPane innerSplitPane = null;
    private JSplitPane outerSplitPane = null;
    private ListSelectionListener themeListener = null;
    private boolean initialized = false;

    public LeftPanel(Component aParent) {
        super(new BorderLayout());
        parent = aParent;
        demoApp = (IDemoApp) parent;
        init();
        initialized = true;
    }

    public void updateUI() {
        if (initialized) {
            if (UIManager.getLookAndFeel().getName().equals("Texture")) {
                Border innerBorder = JTBorderFactory.createTitleBorder(ImageHelper.loadImage("tree.gif"), "Tree-Demo", 0, 0);
                Border outerBorder = BorderFactory.createEmptyBorder(4, 8, 4, 0);
            //    treePanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
             //   treePanel.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
                innerBorder = JTBorderFactory.createTitleBorder(ImageHelper.loadImage("themes.gif"), "Themes-List", 0, 0);
                listPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
                listPanel.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
                innerBorder = JTBorderFactory.createTitleBorder(null, "Controls", 0, 0);
                controlPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
                controlPanel.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
            } else {
               // treePanel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("tree.gif"), "Tree-Demo", 0, 0));
                listPanel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("themes.gif"), "Themes-List", 0, 0));
                controlPanel.setBorder(JTBorderFactory.createTitleBorder(null, "Controls", 0, 0));
            }
            
            //controlPanel.getVerticalScrollBar().putClientProperty("JComponent.sizeVariant", "large");
        }
        super.updateUI();
    }

    private void init() {
        initControls();
    }

    private void initControls() {
       // treePanel = createTree();
        //treePanel.setMinimumSize(new Dimension(80, 60));
        listPanel = createList();
        listPanel.setMinimumSize(new Dimension(80, 60));
        controlPanel = createControl();
        controlPanel.setMinimumSize(new Dimension(80, 60));

        innerSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, treePanel, listPanel);
        innerSplitPane.setDividerLocation(160);
        innerSplitPane.setMinimumSize(new Dimension(100, 100));
        innerSplitPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);

        outerSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, innerSplitPane, controlPanel);
        outerSplitPane.setDividerLocation(360);
        outerSplitPane.setMinimumSize(new Dimension(100, 200));
        outerSplitPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
        add(outerSplitPane, BorderLayout.CENTER);
    }

//    private JScrollPane createTree() {
//        JTree tree = new JTree() {
//
//            public Insets getInsets() {
//                return new Insets(5, 5, 5, 5);
//            }
//        };
//        JScrollPane scrollPane = new JScrollPane(tree);
//        scrollPane.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("tree.gif"), "Tree-Demo", 0, 0));
//        return scrollPane;
//    }

    private JScrollPane createList() {
        themesList = new JList() {

            public Insets getInsets() {
                return new Insets(5, 5, 5, 5);
            }
        };

        themeListener = new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    updateTheme();
                }
            }
        };

        fillThemesList();
        JScrollPane scrollPane = new JScrollPane(themesList);
        scrollPane.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("themes.gif"), "Themes-List", 0, 0));
        return scrollPane;
    }

    private JScrollPane createControl() {
        JPanel panel = new JPanel(new GridBagLayout());
       // taaCheck = new JCheckBox("textAntiAliasing");
//        taaCheck.setSelected(true);
//        taaCheck.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//              demoApp.setLookAndFeelFlag("textAntiAliasing", taaCheck.isSelected());
//            }
//        });
        leftToRightCheck = new JCheckBox("left to right");
        leftToRightCheck.setSelected(true);
        leftToRightCheck.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (JTattooUtilities.getJavaVersion() >= 1.4) {
                    if (leftToRightCheck.isSelected()) {
                        parent.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    } else {
                        parent.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                    }
                    parent.validate();
                    parent.repaint();
                }
            }
        });
       // scrollableTabCheck = new JCheckBox("scrollable tabs");
       // scrollableTabCheck.setSelected(false);
        //scrollableTabCheck.addActionListener(new ActionListener() {

//            public void actionPerformed(ActionEvent e) {
//                if (JTattooUtilities.getJavaVersion() >= 1.4) {
//                    if (scrollableTabCheck.isSelected()) {
//                        demoApp.getMainTabbedPane().setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//                    } else {
//                        demoApp.getMainTabbedPane().setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
//                    }
//                }
//            }
        //});
//        patternCheck = new JCheckBox("backgroundPattern");
//        patternCheck.setSelected(true);
//        patternCheck.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                demoApp.setLookAndFeelFlag("backgroundPattern", patternCheck.isSelected());
//            }
//        });
        centerWindowTitleCheck = new JCheckBox("centerWindowTitle");
        centerWindowTitleCheck.setSelected(true);
        centerWindowTitleCheck.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                demoApp.setLookAndFeelFlag("centerWindowTitle", centerWindowTitleCheck.isSelected());
            }
        });

        ActionListener al = new ActionListener() {

            public void actionPerformed(ActionEvent ev) {
                if (ev.getSource().equals(topTabButton)) {
                    demoApp.getMainTabbedPane().setTabPlacement(JTabbedPane.TOP);
                }
                if (ev.getSource().equals(leftTabButton)) {
                    demoApp.getMainTabbedPane().setTabPlacement(JTabbedPane.LEFT);
                }
                if (ev.getSource().equals(bottomTabButton)) {
                    demoApp.getMainTabbedPane().setTabPlacement(JTabbedPane.BOTTOM);
                }
                if (ev.getSource().equals(rightTabButton)) {
                    demoApp.getMainTabbedPane().setTabPlacement(JTabbedPane.RIGHT);
                }
            }
        };
        ButtonGroup group = new ButtonGroup();
        topTabButton = new JRadioButton("top tab placement");
        leftTabButton = new JRadioButton("left tab placement");
        bottomTabButton = new JRadioButton("bottom tab placement");
        rightTabButton = new JRadioButton("right tab placement");
        group.add(topTabButton);
        group.add(leftTabButton);
        group.add(bottomTabButton);
        group.add(rightTabButton);
        topTabButton.setSelected(true);
        topTabButton.addActionListener(al);
        leftTabButton.addActionListener(al);
        bottomTabButton.addActionListener(al);
        rightTabButton.addActionListener(al);
        int row = 0;
        if (JTattooUtilities.getJavaVersion() >= 1.4) {
           // GridBagHelper.addComponent(panel, taaCheck, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        }
        GridBagHelper.addComponent(panel, leftToRightCheck, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        if (JTattooUtilities.getJavaVersion() >= 1.4) {
//            GridBagHelper.addComponent(panel, scrollableTabCheck, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        }
//        GridBagHelper.addComponent(panel, patternCheck, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, centerWindowTitleCheck, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, new JSeparator(), 0, row++, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(panel, topTabButton, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, leftTabButton, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, bottomTabButton, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, rightTabButton, 0, row++, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, new JPanel(), 0, row++, 1, 1, 0, 0, 0.0, 1.0, GridBagConstraints.VERTICAL, GridBagConstraints.WEST);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(JTBorderFactory.createTitleBorder(null, "Controls", 0, 0));
        return scrollPane;
    }

    public void updateLookAndFeel() {
        fillThemesList();
        LookAndFeel laf = UIManager.getLookAndFeel();
        if (laf instanceof AbstractLookAndFeel) {
          //  taaCheck.setSelected(AbstractLookAndFeel.getTheme().isTextAntiAliasingOn());
          //  patternCheck.setSelected(AbstractLookAndFeel.getTheme().isBackgroundPatternOn());
            centerWindowTitleCheck.setSelected(AbstractLookAndFeel.getTheme().isCenterWindowTitleOn());
        } else {
            //taaCheck.setSelected(false);
            //patternCheck.setSelected(false);
            centerWindowTitleCheck.setSelected(false);
        }
    }

    private void fillThemesList() {
        themesList.removeListSelectionListener(themeListener);
        LookAndFeel laf = UIManager.getLookAndFeel();
        if (laf instanceof com.jtattoo.plaf.acryl.AcrylLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.acryl.AcrylLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.aero.AeroLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.aero.AeroLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.aluminium.AluminiumLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.bernstein.BernsteinLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.fast.FastLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.fast.FastLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.graphite.GraphiteLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.graphite.GraphiteLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.hifi.HiFiLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.hifi.HiFiLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.luna.LunaLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.luna.LunaLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.mcwin.McWinLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.mcwin.McWinLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.mint.MintLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.mint.MintLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.noire.NoireLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.noire.NoireLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.smart.SmartLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.smart.SmartLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.texture.TextureLookAndFeel) {
            themesList.setListData(com.jtattoo.plaf.texture.TextureLookAndFeel.getThemes().toArray());
        } else {
            themesList.setListData(new Vector());
        }
        if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
            themesList.setSelectedValue(AbstractLookAndFeel.getTheme().getName(), true);
        }
        themesList.addListSelectionListener(themeListener);
    }

    private void updateTheme() {
        final String theme = (String) themesList.getSelectedValue();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                ((IDemoApp) parent).updateTheme(theme);
            }
        });
    }
}