package com.jtattoo.demo.app;
import connection.constants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class illegal_pane extends javax.swing.JPanel implements ActionListener
{
                Connection con=null;
                constants connect = new constants();
    public illegal_pane() {
         setName("Illegal Process Form");
        initComponents();
        Add_process.addActionListener(this);
        Remove_process.addActionListener(this);
        fill();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Add_process = new javax.swing.JButton();
        Remove_process = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText("Process Name");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        Add_process.setText("ADD");

        Remove_process.setText("REMOVE");

        jLabel2.setText("*");

        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("(With Extention)");

        jLabel4.setText("Select Process For Delete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4)
                            .addGap(6, 6, 6)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Remove_process, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Add_process, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(Add_process))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(Remove_process))
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addContainerGap(106, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_process;
    private javax.swing.JButton Remove_process;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
public void fill()
{
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
        PreparedStatement ps = con.prepareStatement("select Process_Name from process");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String s = rs.getString("Process_Name");
            jComboBox1.addItem(s);
         //   v.add(s);
        }

        }
        catch (Exception ex) 
        {
        ex.printStackTrace();    
        }
    
}
public void remv()
{
    final int itemCount = jComboBox1.getItemCount();
     int count=itemCount;
  jComboBox1.removeAllItems();
     fill();
}
        
        @Override
    public void actionPerformed(ActionEvent e) {
       String name = jTextField1.getText();
     
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
        }
        catch (Exception ex) 
        {
        ex.printStackTrace();    
        }
        
   if(e.getSource()== Add_process)
       {
            try 
            {
            String query = "insert into process(Process_Name) value(?);";
            PreparedStatement insert = con.prepareStatement(query);
            insert.setString(1, name);
            int rs1 = insert.executeUpdate();
            
            if(rs1==1)
               {
                jLabel2.setText("Insert New Extension:"+ name+"Successfully");
               }
            else
               {
                jLabel2.setText("Something Wrong..");
               }
            con.close(); 
            }
            
            catch (Exception ex) 
            {
               ex.printStackTrace(); 
            }
           // jComboBox1.removeAll();
           // fill();
            remv();
      }
  if(e.getSource()== Remove_process)
       {
            try 
            {
            String query = "delete from process where Process_Name=?;";
            String combo_remove = jComboBox1.getSelectedItem().toString();
            PreparedStatement insert = con.prepareStatement(query);
            insert.setString(1, combo_remove);
            int rs1 = insert.executeUpdate();
            if(rs1==1)
               {
                jLabel2.setText("Delete Extension:"+ combo_remove+" Successfully");
               }
            else
               {
                jLabel2.setText("Something Wrong..");
               }
            con.close(); 
              }
            catch (Exception ex) 
            {
               ex.printStackTrace(); 
            }
            //jComboBox1.removeAll();
          //  fill();
            remv();
      }
    }
 }