/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.jtattoo.demo.app;

import com.jtattoo.demo.images.ImageHelper;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author Michael Hagen
 */
public class MainToolBar extends JToolBar {
    private ImageIcon newImage = null;
    private ImageIcon openImage = null;
    private ImageIcon saveImage = null;
    private ImageIcon cutImage = null;
    private ImageIcon copyImage = null;
    private ImageIcon pasteImage = null;
    private ImageIcon undoImage = null;
    private ImageIcon redoImage = null;
    private ToolButton newButton = null;
    private ToolButton openButton = null;
    private ToolButton saveButton = null;
    private ToolButton cutButton = null;
    private ToogleToolButton copyButton = null;
    private ToolButton pasteButton = null;
    private ToolButton undoButton = null;
    private ToolButton redoButton = null;

//    private JButton defaultBorderButton = null;

    public MainToolBar() {
        super();
        setFloatable(true);
        setMargin(new Insets(2, 0, 2, 0));
        newImage = ImageHelper.loadImage("new.png");
        newButton = new ToolButton(newImage);
        newButton.setToolTipText("Opens a new document (just a demo).");
        openImage = ImageHelper.loadImage("open.png");
        openButton = new ToolButton(openImage);
        openButton.setToolTipText("Opens the file chooser dialog to open an existing document (just a demo).");
        openButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(JTattooDemo.app);
            }
        });
        saveImage = ImageHelper.loadImage("save.png");
        saveButton = new ToolButton(saveImage);
        saveButton.setToolTipText("Saves the active document (just a demo).");
        cutImage = ImageHelper.loadImage("cut.png");
        cutButton = new ToolButton(cutImage);
        cutButton.setToolTipText("Cuts the marked contens out of your document (just a demo).");
        copyImage = ImageHelper.loadImage("copy.png");
        copyButton = new ToogleToolButton(copyImage);
        copyButton.setToolTipText("Copies the marked contens out of your document into the clipboard (just a demo).");
        pasteImage = ImageHelper.loadImage("paste.png");
        pasteButton = new ToolButton(pasteImage);
        pasteButton.setToolTipText("Inserts the contens of the clipboard into your document (just a demo).");
        pasteButton.setEnabled(false);
        undoImage = ImageHelper.loadImage("undo.png");
        undoButton = new ToolButton(undoImage);
        undoButton.setToolTipText("Undos the last action (just a demo).");
        redoImage = ImageHelper.loadImage("redo.png");
        redoButton = new ToolButton(redoImage);
        redoButton.setToolTipText("Redos the last action (just a demo).");

//        defaultBorderButton = new JButton("DefaultBorder");
//        defaultBorderButton.setFocusable(false);
//        defaultBorderButton.putClientProperty("paintToolBarBorder", Boolean.FALSE);

//        add(newButton);
//        add(openButton);
//        add(saveButton);
//        addSeparator();
//        add(cutButton);
//        add(copyButton);
//        add(pasteButton);
//        addSeparator();
//        add(undoButton);
//        add(redoButton);

//        addSeparator();
//        add(defaultBorderButton);

    }

    private class ToolButton extends JButton {

        public ToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }

    private class ToogleToolButton extends JToggleButton {

        public ToogleToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }
}
