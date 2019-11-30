/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.jtattoo.demo.app;

import com.jtattoo.demo.images.ImageHelper;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Michael Hagen
 */
public class SplashPanel extends JPanel {
    private ImageIcon splashImage = ImageHelper.loadImage("splash.png");
    private Dimension size = new Dimension(splashImage.getIconWidth(), splashImage.getIconHeight());

    public SplashPanel() {
        super();
       setForeground(new Color(233, 115, 103));
        setFont(new Font("Serif", Font.PLAIN, 28));
    }

    public Dimension getPreferredSize() {
        return size;
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        splashImage.paintIcon(this, g, 0, 0);
        int x = 316;
        int y = 172;
        Object rh = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(getFont());
//        g.setColor(Color.black);
//        g.drawString("Version 1.0", x + 3, y + 2);
//        g.setColor(getForeground());
//        g.drawString("Version 1.0", x, y);
//        g.drawString("Version 1.0", x + 1, y);
//        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, rh);
    }

}
