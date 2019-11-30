/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.jtattoo.demo.app;

import com.jtattoo.demo.images.ImageHelper;
import com.jtattoo.demo.utils.GridBagHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

/**
 *
 * @author  Michael Hagen
 */
public class RadioButtonPanel extends JPanel {
    private JRadioButton standardRadioButton = null;
    private JRadioButton selectedRadioButton = null;
    private JRadioButton htmlRadioButton = null;
    private JRadioButton disabledRadioButton = null;
    private JRadioButton disabledSelectedRadioButton = null;
    private JRadioButton coloredRadioButton = null;
    private JRadioButton iconRadioButton = null;
    private JPanel contentPanel = null;
    
    public RadioButtonPanel() {
        super(new BorderLayout());
        init();
    }

    private void init() {
        setName("RadioButton");
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        GridBagLayout layout = new GridBagLayout();
        contentPanel = new JPanel(layout);

        standardRadioButton = new JRadioButton("Standard");
        selectedRadioButton = new JRadioButton("Selected");
        selectedRadioButton.setSelected(true);
        htmlRadioButton = new JRadioButton("<html><b>HTML</b>-RadioButton</html>");
        disabledRadioButton = new JRadioButton("Disabled");
        disabledRadioButton.setEnabled(false);
        disabledSelectedRadioButton = new JRadioButton("Disabled selected");
        disabledSelectedRadioButton.setSelected(true);
        disabledSelectedRadioButton.setEnabled(false);
        coloredRadioButton = new JRadioButton("Colored");
        coloredRadioButton.setForeground(Color.blue);
        iconRadioButton = new JRadioButton("Owner icons");
        iconRadioButton.setIcon(ImageHelper.loadImage("checkbox.png"));
        iconRadioButton.setSelectedIcon(ImageHelper.loadImage("checkbox_selected.png"));
        iconRadioButton.setPressedIcon(ImageHelper.loadImage("checkbox_pressed.png"));
        iconRadioButton.setRolloverIcon(ImageHelper.loadImage("checkbox_rollover.png"));
        iconRadioButton.setRolloverSelectedIcon(ImageHelper.loadImage("checkbox_rollover_selected.png"));
        iconRadioButton.setSelected(true);


        ButtonGroup group = new ButtonGroup();
        group.add(standardRadioButton);
        group.add(selectedRadioButton);
        group.add(htmlRadioButton);
        group.add(disabledRadioButton);
        group.add(coloredRadioButton);
        group.add(iconRadioButton);

        GridBagHelper.addComponent(contentPanel, standardRadioButton,           0, 1, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, selectedRadioButton,           0, 2, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, htmlRadioButton,               0, 3, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, disabledRadioButton,           0, 4, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, disabledSelectedRadioButton,   0, 5, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, coloredRadioButton,            0, 6, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, iconRadioButton,               0, 7, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

}
