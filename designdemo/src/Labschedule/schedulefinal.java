/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Labschedule;

import connection.constants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author 03
 */
public class schedulefinal extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form schedulefinal
     */
    constants connect = new constants();
    Connection con = null;
    static String update1, delete1;
    String labid, datause;
    String time_from, time_to, department, sub, day1, schedule_id;
    boolean internet = false;
    String data_use = "null";
    String datatime, datatime1, datatime2;
    static String s, s1;

    //  jRadioButton1.addActionListener(this);
    //jRadioButton2.addActionListener(this);
    public schedulefinal() {

        initComponents();
        setName("Schedule For Lab Setting");
        setschudule.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
        jLabel2.setVisible(true);
        fill();
        fill1();
    }

    public void fill() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
            PreparedStatement ps = con.prepareStatement("select SCHEDULE_ID from schedule_for_lab");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s = rs.getString("SCHEDULE_ID");
                deletebox.addItem(s);
                //   v.add(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void remv() {
        final int itemCount = deletebox.getItemCount();
        int count = itemCount;
        deletebox.removeAllItems();
        fill();
    }

    public void fill1() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
            PreparedStatement ps = con.prepareStatement("select SCHEDULE_ID from schedule_for_lab");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s1 = rs.getString("SCHEDULE_ID");
                updatebox.addItem(s1);
                //   v.add(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void remv1() {
        final int itemCount = updatebox.getItemCount();
        int count = itemCount;
        updatebox.removeAllItems();
        fill1();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        setschudule = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        delete = new javax.swing.JButton();
        day = new javax.swing.JComboBox();
        labend1 = new javax.swing.JTextField();
        labstart1 = new javax.swing.JTextField();
        updatebox = new javax.swing.JComboBox();
        deletebox = new javax.swing.JComboBox();
        labstart = new javax.swing.JTextField();
        labend = new javax.swing.JTextField();
        subject = new javax.swing.JComboBox();
        subject1 = new javax.swing.JComboBox();
        lab_id = new javax.swing.JComboBox();
        lab_id1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        scheduleid = new javax.swing.JTextField();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(null);

        jLabel1.setText("Lab_id");

        jLabel4.setText("Days");

        jLabel5.setText("Lab start");

        jLabel6.setText("Lab End");

        setschudule.setText("Set Schedule");
        setschudule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setschuduleActionPerformed(evt);
            }
        });

        jLabel7.setText("Lab_id");

        jLabel8.setText("Subject");

        jLabel10.setText("Lab_Start");

        jLabel11.setText("Lab End");

        update.setText("Update schedule");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("Delete Schedule");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        day.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" }));

        updatebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateboxActionPerformed(evt);
            }
        });

        subject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "java", "C", "C++", "PHP" }));

        subject1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "java", "C", "C++", "PHP" }));

        lab_id.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "101", "102", "103", "104" }));

        lab_id1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "101", "102", "103", "104" }));

        jLabel2.setText("subject");

        jLabel12.setText("schedule_id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addGap(41, 41, 41)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lab_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(scheduleid, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labend1)
                                        .addGap(76, 76, 76))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labstart1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(78, 78, 78))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lab_id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(subject1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(81, 81, 81)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(update)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(updatebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel11)
                                                        .addComponent(jLabel10)))))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labend, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labstart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(158, 158, 158)
                                        .addComponent(setschudule, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 98, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(delete)
                .addGap(18, 18, 18)
                .addComponent(deletebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labend1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(scheduleid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(labstart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(setschudule)
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(labstart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lab_id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(subject1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(labend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update)
                    .addComponent(updatebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delete)
                    .addComponent(deletebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setschuduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setschuduleActionPerformed
        int rs1 = 0;
        time_to = labend1.getText().toString();
        time_from = labstart1.getText().toString();
        labid = lab_id.getSelectedItem().toString();
        sub = subject.getSelectedItem().toString();
//        department=Department.getSelectedItem().toString();
        day1 = day.getSelectedItem().toString();
        schedule_id = scheduleid.getText().toString();
        System.out.println("liza" + time_to);
        System.out.println("mihir" + time_from);
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
            String query = "insert into schedule_for_lab(LAB_ID,SCHEDULE_ID,SUBJECT,TIME_FROM,TIME_TO,DAY,DATA_USE) value(?,?,?,?,?,?,?);";
            PreparedStatement insert = con.prepareStatement(query);

            insert.setString(1, labid);
            insert.setString(2, schedule_id);
            insert.setString(3, sub);
            // insert.setString(4, department);
            insert.setString(4, time_to);
            insert.setString(5, time_from);
            insert.setString(6, day1);

            //insert.setBoolean(8, internet);
            insert.setString(7, data_use);
            rs1 = insert.executeUpdate();
            //System.out.println("affected rows:" + rs1);
            System.out.println("Data inserted successfully");
            if (rs1 != 0) {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Create New Schedule Successfully..", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
             JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Can't Create A New Schedule", "Message",
                        JOptionPane.INFORMATION_MESSAGE);   
            }
            remv();
            fill();
            remv();
            remv1();
            fill1();
            remv1();
        } catch (Exception ex) {

            if (rs1 == 0) {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Exception is Occurs due to Connection Error..", "Server Error",
                JOptionPane.ERROR);
            }
            ex.printStackTrace();
        }
        // TODO add your handling code here:
        String sqr = "update student_work_detail set PERMISSION_NET='YES' where LAB_ID='" + labid + "'";
        Statement st2;
        try {
            st2 = con.createStatement();
            st2.execute(sqr);
        } catch (SQLException ex) {
            Logger.getLogger(schedulefinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_setschuduleActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        int rs1 = 0;
        time_to = labstart.getText().toString();
        time_from = labend.getText().toString();
        labid = lab_id1.getSelectedItem().toString();
        sub = subject1.getSelectedItem().toString();
        //department=Department1.getSelectedItem().toString();
        day1 = day.getSelectedItem().toString();
        //schedule_id=scheduleid1.getText().toString();
        update1 = updatebox.getSelectedItem().toString();
        System.out.println("liza" + time_to);
        System.out.println("mihir" + time_from);
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
            String query = "update  schedule_for_lab set LAB_ID=?,SCHEDULE_ID=?,SUBJECT=? , TIME_FROM=?,TIME_TO=? where SCHEDULE_ID=?";
            // String query1 = "select schedule_for_lab Id";
            PreparedStatement insert = con.prepareStatement(query);

            insert.setString(1, labid);
            insert.setString(2, update1);

            insert.setString(3, sub);
            //insert.setString(4, department);
            insert.setString(4, time_to);
            insert.setString(5, time_from);
            //insert.setString(6, day1);

            //insert.setBoolean(7, internet);
            insert.setString(6, update1);



            rs1 = insert.executeUpdate();
            //System.out.println("affected rows:" + rs1);
            System.out.println("Data updated successfully");
            if (rs1 != 0) 
            {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Update Schedule Successfully..", "Message",
                JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Can't Update Schedule", "Message",
                JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {

            if (rs1 == 0) 
            {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Exception is Occurs due to Connection Error..", "Server Error",
                JOptionPane.ERROR_MESSAGE);
            }
            ex.printStackTrace();
        }

        // remv1();
        //fill1();
        //remv1();


    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int rs1 = 0;
        try {
            //fill();
            delete1 = deletebox.getSelectedItem().toString();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
            String query = "delete from schedule_for_lab where SCHEDULE_ID=?";
            PreparedStatement insert = con.prepareStatement(query);
            //PreparedStatement insert1 = con.prepareStatement("select ID from schedule_for_lab");
            //ResultSet rs=insert1.executeQuery();

            insert.setString(1, delete1);




            rs1 = insert.executeUpdate();
            System.out.println("affected rows:" + rs1);
            System.out.println("Data deleted successfully");
            if (rs1 != 0) 
            {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Delete Schedule Successfully..", "Message",
                JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                 JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Can't Delete Schedule", "Message",
                JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            if (rs1 == 0) {
                JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Exception is Occurs due to Connection Error..", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            ex.printStackTrace();

        }

        remv();
        remv1();
    }//GEN-LAST:event_deleteActionPerformed

    private void updateboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateboxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox day;
    private javax.swing.JButton delete;
    private javax.swing.JComboBox deletebox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox lab_id;
    private javax.swing.JComboBox lab_id1;
    private javax.swing.JTextField labend;
    private javax.swing.JTextField labend1;
    private javax.swing.JTextField labstart;
    private javax.swing.JTextField labstart1;
    private javax.swing.JTextField scheduleid;
    private javax.swing.JButton setschudule;
    private javax.swing.JComboBox subject;
    private javax.swing.JComboBox subject1;
    private javax.swing.JButton update;
    private javax.swing.JComboBox updatebox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
