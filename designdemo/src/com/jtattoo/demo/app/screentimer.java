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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hp
 */
public class screentimer extends javax.swing.JPanel implements ActionListener{

    /**
     * Creates new form screentimer
     */
    int str,str1;
     Statement st;
    public screentimer()
    {
        setName("Snapshot Timer Form");
        initComponents();
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jButton3.setVisible(false);
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("Enter Time In Min.:-");

        jButton1.setText("Set Time");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jButton2.setText("Click Here To Start ScreenSnot");

        jButton3.setText("Click Here To Stop ScreenShot");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) 
    {
         Connection con=null;
         constants connect = new constants();
         
        try 
        {            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }
            
            
        if(e.getSource() == jButton1)
        {
            String qr= "SELECT Time_Value FROM timervalue";
           
            try
            {
                 st = con.createStatement();
                 ResultSet rs = st.executeQuery(qr);
             while(rs.next())
            {
             str=rs.getInt("Time_Value");
            }
                rs.close(); 
                int val = Integer.valueOf(jTextField1.getText());
                int val1 = val*1000;
            
            String qr1 = "update timervalue set Time_Value= "+ val1+ " where Time_Value="+ str;
            st.executeUpdate(qr1);
            jLabel2.setText("Timer Set Take SnapShot After"+ val1/1000+ " Minute successfully");
            jLabel2.setVisible(true);
            
            }
             catch (Exception ex) 
            {
            ex.printStackTrace();    
            }
        }
        if(e.getSource()== jButton2)
        {
          
                try
                {
                    st = con.createStatement();
                    String query = "update snap_yes_no set Snap_Start_Stop=1  where Snap_Start_Stop=0;";
                    st.executeUpdate(query);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                jLabel3.setVisible(true);
                jLabel3.setText("Start The Pcs ScreenShot");
                jButton2.setVisible(false);
                jButton3.setVisible(true);
          }
          if(e.getSource() == jButton3)
          {
            try 
            {
                st = con.createStatement();
                 String query = "update snap_yes_no set Snap_Start_Stop=0  where Snap_Start_Stop=1;";
                 st.executeUpdate(query);
            } catch (Exception ex) 
            {
                ex.printStackTrace();
            }
              jLabel3.setVisible(true);      
            jLabel3.setText("Stop the Pcs ScreenShot");
            jButton2.setVisible(true);
            jButton3.setVisible(false);
           }
        }
   }

