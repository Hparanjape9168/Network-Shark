/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtattoo.demo.app;

import connection.constants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Liza
 */
public class setuserlab extends javax.swing.JPanel  implements ActionListener{
    String labid1, user,s1; 
    constants connect = new constants();
    Connection con = null;
    public setuserlab() {
        setName("Set Lab Id");
        initComponents();
        setlab.addActionListener(this);
        fill1();
    }

    public void fill1() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
            PreparedStatement ps = con.prepareStatement("select ENROLLMENT_NUMBER from student_work_detail");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s1 = rs.getString("ENROLLMENT_NUMBER");
                username.addItem(s1);
                //   v.add(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void remv1() {
        final int itemCount = username.getItemCount();
        int count = itemCount;
        username.removeAllItems();
        fill1();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JComboBox();
        labid = new javax.swing.JComboBox();
        setlab = new javax.swing.JButton();

        jLabel1.setText("User_Name");

        jLabel2.setText("Lab_ID");

        labid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "101", "102", "103", "104" }));

        setlab.setText("SetLab");
        setlab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setlabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setlab)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labid, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(setlab)
                .addContainerGap(140, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setlabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setlabActionPerformed
        labid1=labid.getSelectedItem().toString();
        user=username.getSelectedItem().toString();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
           
            String query = "update  student_work_detail set LAB_ID=? where ENROLLMENT_NUMBER=?";
            // String query1 = "select schedule_for_lab Id";
            PreparedStatement insert = con.prepareStatement(query);
            insert.setString(1, labid1);
            insert.setString(2, user);
             insert.executeUpdate();
             System.out.println("update successful");
        } catch (Exception ex) {
          ex.printStackTrace();  
        } 
            
            
    }//GEN-LAST:event_setlabActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox labid;
    private javax.swing.JButton setlab;
    private javax.swing.JComboBox username;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
