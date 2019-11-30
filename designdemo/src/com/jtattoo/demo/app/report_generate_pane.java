/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtattoo.demo.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Sunil
 */
public class report_generate_pane extends javax.swing.JPanel {

    /**
     * Creates new form report_generate_pane
     */
    public report_generate_pane() {
        setName("Report Generation");
        initComponents();
        jButton1.addActionListener(new ActionListener() {                            //for process
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MysqlToXlsprocess mysqlToXls = new MysqlToXlsprocess();
                    mysqlToXls.generateXls("process", "person.xls");
                    mysqlToXls.close();
                    jLabel11.setText("process_excel is generated");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jLabel11.setText(ex.toString());
                }
            }
        });

        jButton5.addActionListener(new ActionListener() {                           //login database
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    MysqlToXlslogin mysqlToXls = new MysqlToXlslogin();
                    mysqlToXls.generateXls("login", "login.xls");
                    mysqlToXls.close();
                    jLabel10.setText("login excel is generated");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jLabel10.setText(ex.toString());
                }

            }
        });


        jButton2.addActionListener(new ActionListener() {                          //illegal activity
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    MysqlToXlsIllegal_activity mysqlToXls = new MysqlToXlsIllegal_activity();
                    mysqlToXls.generateXls("illegal_access", "illegal_activity.xls");
                    mysqlToXls.close();
                    jLabel7.setText("illegal_activity_excel generated");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jLabel7.setText(ex.toString());
                }

            }
        });

        jButton4.addActionListener(new ActionListener() {                       //employee work
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    MysqlToXlsEmployee_work mysqlToXls = new MysqlToXlsEmployee_work();
                    mysqlToXls.generateXls("student_work_detail", "employee_work_detail.xls");
                    mysqlToXls.close();
                    jLabel9.setText("student_work_detail excel generated");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jLabel9.setText(ex.toString());
                }

            }
        });

        jButton6.addActionListener(new ActionListener() {                        //faculty work detail
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    MysqlToXlsFaculty_work mysqlToXls = new MysqlToXlsFaculty_work();
                    mysqlToXls.generateXls("faculty_work_detail", "faculty_work_detail.xls");
                    mysqlToXls.close();
                    jLabel12.setText("Faculty_work_detail excel generated");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jLabel12.setText(ex.toString());
                }

            }
        });

        Lab_Schedule.addActionListener(new ActionListener() {                      //lab_schedule
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    MysqlToXlsLab_scedule mysqlToXls = new MysqlToXlsLab_scedule();
                    mysqlToXls.generateXls("schedule_for_lab", "lab_time_table.xls");
                    mysqlToXls.close();
                    jLabel8.setText("lab_timetable excel generated");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jLabel8.setText(ex.toString());
                }

            }
        });
    
       pendrive.addActionListener(new ActionListener() {                      //lab_schedule
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    MysqlToXlsLab_scedule mysqlToXls = new MysqlToXlsLab_scedule();
                    mysqlToXls.generateXls("pendrive", "Pendrive.xls");
                    mysqlToXls.close();
                    jLabel16.setText("Pendrive_access_Detail  excel generated");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jLabel16.setText(ex.toString());
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Lab_Schedule = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pendrive = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        jLabel2.setText("Report of list of process :");

        jLabel1.setText("Report of Rstricted activity :");

        jLabel3.setText("Report of lab time-table :");

        jLabel4.setText("Report of Employee work :");

        jLabel5.setText("Report of List of login :");

        jLabel6.setText("Report of faculty work :");

        jButton1.setText("Process");

        jButton2.setText("Illegal Activity");

        Lab_Schedule.setText("Lab_schedule");

        jButton4.setText("Employee_work_detail");

        jButton5.setText("Login_detail");

        jButton6.setText("Faculty_work_detail");

        jLabel7.setText("*");

        jLabel8.setText("*");

        jLabel9.setText("*");

        jLabel10.setText("*");

        jLabel11.setText("*");

        jLabel12.setText("*");

        jLabel13.setText("NOTE : just click on button generate the excel report which you want");

        jLabel14.setText("*");

        jLabel15.setText("Report of pendrive Detail:");

        pendrive.setText("Pendrive_Access_Detail ");
        pendrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendriveActionPerformed(evt);
            }
        });

        jLabel16.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Lab_Schedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pendrive, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel16))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Lab_Schedule)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton4)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton6)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(pendrive)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pendriveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendriveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pendriveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Lab_Schedule;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton pendrive;
    // End of variables declaration//GEN-END:variables
}
