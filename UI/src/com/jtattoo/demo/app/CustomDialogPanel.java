/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.demo.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author Michael Hagen
 */
public class CustomDialogPanel extends javax.swing.JPanel {

    private JDialog dlg = null;
    
    public CustomDialogPanel(JDialog aDlg) {
        dlg = aDlg;
        initComponents();
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dlg.dispose();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputLabel = new javax.swing.JLabel();
        inputScrollPane = new javax.swing.JScrollPane();
        inputField = new javax.swing.JTextArea();
        okButton = new javax.swing.JButton();

        setLayout(null);

        inputLabel.setText("Input");
        add(inputLabel);
        inputLabel.setBounds(10, 10, 28, 18);

        inputField.setColumns(20);
        inputField.setRows(5);
        inputScrollPane.setViewportView(inputField);

        add(inputScrollPane);
        inputScrollPane.setBounds(10, 30, 310, 130);

        okButton.setText("OK");
        add(okButton);
        okButton.setBounds(140, 175, 56, 24);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea inputField;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JScrollPane inputScrollPane;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
