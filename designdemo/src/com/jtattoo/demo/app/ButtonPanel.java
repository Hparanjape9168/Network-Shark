/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.jtattoo.demo.app;

import com.jtattoo.demo.utils.GridBagHelper;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  Michael Hagen
 */
public class ButtonPanel extends JPanel {

    private JButton standardButton = null;
    private JButton defaultButton = null;
    private JButton htmlButton = null;
    private JButton disabledButton = null;
    private JButton coloredButton = null;

    private JButton noBorderButton = null;
    private JButton emptyBorderButton = null;
    private JButton lineBorderButton = null;
    private JButton noContentAreaButton = null;

    public ButtonPanel() {
        super(new BorderLayout());
        init();
    }

    private void init() {
        setName("Buttons");
        GridBagLayout layout = new GridBagLayout();
        JPanel contentPanel = new JPanel(layout);
        // if you want to fill the background with a texture - uncomment the next line
        // contentPanel.putClientProperty("backgroundTexture", ImageHelper.loadImage("BackgroundTexture.jpg"));

        standardButton = new JButton("Standard");

        defaultButton = new JButton("Default");
        defaultButton.setDefaultCapable(true);

        htmlButton = new JButton("<html><b>HTML</b>-Button</html>");

        disabledButton = new JButton("Disabled");
        disabledButton.setEnabled(false);

        coloredButton = new JButton("Colored");
        coloredButton.setForeground(new Color(128, 255, 128));
        coloredButton.setBackground(new Color(128, 128, 255));

        noBorderButton = new JButton("NoBorder");
        noBorderButton.setBorderPainted(false);

        emptyBorderButton = new JButton("EmptyBorder");
        emptyBorderButton.setBorder(BorderFactory.createEmptyBorder());

        lineBorderButton = new JButton("LineBorder");
        lineBorderButton.setBorder(BorderFactory.createLineBorder(Color.red));

        noContentAreaButton = new JButton("NoContentArea");
        noContentAreaButton.setContentAreaFilled(false);


        GridBagHelper.addComponent(contentPanel, standardButton,  0, 1, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, defaultButton,   0, 2, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, htmlButton,      0, 3, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, disabledButton,  0, 4, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, coloredButton,   0, 5, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        GridBagHelper.addComponent(contentPanel, noBorderButton,      0, 6, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, emptyBorderButton,   0, 7, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, lineBorderButton,    0, 8, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, noContentAreaButton, 0, 9, 1, 1, 16, 8,  0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if ((aFlag == true) && (getRootPane() != null)) {
            getRootPane().setDefaultButton(defaultButton);
        }
    }
}
