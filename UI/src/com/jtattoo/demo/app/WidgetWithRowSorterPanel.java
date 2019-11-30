/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.demo.app;

import com.jtattoo.border.JTBorderFactory;
import com.jtattoo.demo.utils.GridBagHelper;
import connection.constants;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

/**
 *
 * @author Michael Hagen
 */
public class WidgetWithRowSorterPanel extends JPanel
{
     userlogin obj = new userlogin();
     JTabbedPane tab =null;
    private JPanel widgetPanel = null;
    private JScrollPane tablePanel = null;
    private JSplitPane splitPane = null;
    private JComboBox addressCombo = null;
  //  private JTextField firstNameField = null;
   // public JLabel enrll = null;
    
    public JLabel ipaddr=null;
   static public String enrll,ip ;
   public String oldvalue;
   static public int datause;
      private JPasswordField newpass = null;
      private JPasswordField renewpass = null;
      private JPasswordField oldpass =null;
      private JButton chgepass = null;
      private JLabel dis ;
    //   private JTextField cityField = null;
    //   private ButtonGroup buttonGroup = null;
    //   private JRadioButton redButton = null;
    //   private JRadioButton greenButton = null;
    //   private JRadioButton blueButton = null;
    //   private JCheckBox bananaButton = null;
    //   private JCheckBox burgerButton = null;
    //   private JCheckBox icecreamButton = null;
    private JButton updateButton = null;
    //   private JButton insertButton = null;
    //   private JButton deleteButton = null;
    private JScrollPane tableScrollPane = null;
    private JTable table = null;
    private ArrayList colNames = new ArrayList();
    private ArrayList dataList = new ArrayList();
    private boolean initialized = false;
    
     String tpass,trepass,toldpass;

    public WidgetWithRowSorterPanel() throws ClassNotFoundException, SQLException 
    {
        super(new BorderLayout());
        init();
        initialized = true;


    }

    public void updateUI() {
        super.updateUI();
        if (initialized) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (UIManager.getLookAndFeel().getName().equals("Texture")) {
                        tablePanel.setBorder(BorderFactory.createEmptyBorder());
                    } else {
                        Border border = UIManager.getBorder("ScrollPane.border");
                        tablePanel.setBorder(border);
                    }
                }
            });
        }
    }

    private void init() throws ClassNotFoundException, SQLException  {
        setName("User Current Status");
        initModel();
        initControls();
        initListeners();


    }

    private void initModel() throws ClassNotFoundException, SQLException {

        String  ltime, w1, w2, w3, w4, w5, m;
        int datause1, total;

        ArrayList rowData = new ArrayList();
        Statement st;
        Connection con = null;
        constants connect = new constants();
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
        
        System.out.println("login user as------>"+obj.tlogin);
        String qr = "SELECT * FROM student_work_detail where ENROLLMENT_NUMBER='"+obj.tlogin+"'";
    
        st = con.createStatement();
        ResultSet rs = st.executeQuery(qr);
        colNames.add("WEEK-1 ");
        colNames.add("WEEK-2");
        colNames.add("WEEK-3");
        colNames.add("WEEK-4");
        colNames.add("WEEK-5");
        colNames.add("ENTIRE MONTH");
        while (rs.next()) {
            enrll = rs.getString("ENROLLMENT_NUMBER");
            datause = rs.getInt("DATA_USE");
            ip = rs.getString("IP_ADDRESS");
            System.out.println("name is"+enrll);
            w1 = rs.getString("WEEK_1");
            w2 = rs.getString("WEEK_2");
            w3 = rs.getString("WEEK_3");
            w4 = rs.getString("WEEK_4");
            w5 = rs.getString("WEEK_5");
            total = Integer.parseInt(w1) + Integer.parseInt(w2) + Integer.parseInt(w3) + Integer.parseInt(w4) + Integer.parseInt(w5);
            //total = Integer.parseInt(m);
            rowData.add(w1 + " MB");
            rowData.add(w2 + " MB");
            rowData.add(w3 + " MB");
            rowData.add(w4 + " MB");
            rowData.add(w5 + " MB");
            rowData.add(total + " MB");
            System.out.println("MOnth" + total);
            dataList.add(rowData);
        }
        rs.close();

    }

    private void initControls() {
        widgetPanel = createWidgetPanel();
        tablePanel = createTablePanel();
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, widgetPanel, tablePanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(320);
        add(splitPane, BorderLayout.CENTER);
    }

    private void initListeners()  {
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run()
                    {
                        if (getRootPane() != null) {
                            getRootPane().setDefaultButton(updateButton);
                            System.out.println("update this now press");
                            updateButton.repaint();
                        }
                    }
                });
            }
        });
    }
    

    private JPanel createWidgetPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createFormPanel(), BorderLayout.CENTER);
        try {
            panel.add(createButtonPanel(), BorderLayout.EAST);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WidgetWithRowSorterPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WidgetWithRowSorterPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return panel;
    }

    private JScrollPane createFormPanel() 
    {
        addressCombo = new JComboBox(new String[]{"Mr.", "Mrs.", "Sir", "Lady", "Herr", "Frau"});
        addressCombo.setToolTipText("Select salutation!");
        newpass = new JPasswordField();
        renewpass= new JPasswordField();
        oldpass=new JPasswordField();

        JPanel panel = new JPanel(new GridBagLayout());
        //panel.setOpaque(false);
        JPanel topDistPanel = new JPanel();
        JPanel bottomDistPanel = new JPanel();
        GridBagHelper.addComponent(panel, topDistPanel, 0, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST);

        GridBagHelper.addComponent(panel, new JLabel("User Login Name: "+enrll), 0, 1, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
       // GridBagHelper.addComponent(panel, addressCombo, 1, 1, 1, 1, 0, 0, 0.3, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
        GridBagHelper.addComponent(panel, new JPanel(), 2, 1, 1, 1, 0, 0, 0.7, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
       
        GridBagHelper.addComponent(panel, new JLabel("Today Data Usage:"+datause+" MB"), 0, 2, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
       // GridBagHelper.addComponent(panel, firstNameField, 1, 2, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
         GridBagHelper.addComponent(panel, new JLabel("IP_Address Of Access Pcs: "+ip), 0, 3, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
//        GridBagHelper.addComponent(panel, lastNameField, 1, 3, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
         
          GridBagHelper.addComponent(panel, new JLabel("Enter Old Password"), 0, 5, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel,oldpass , 1, 5, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

         
         GridBagHelper.addComponent(panel, new JLabel("Enter New PassWord:"), 0, 6, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
         GridBagHelper.addComponent(panel, newpass, 1, 6, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
         
         GridBagHelper.addComponent(panel, new JLabel("Enter Conform PassWord:"), 0, 7, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
         GridBagHelper.addComponent(panel, renewpass, 1, 7, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
         
          //GridBagHelper.addComponent(panel, new JLabel("Enter , 0, 7, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
         //GridBagHelper.addComponent(panel, renewpass, 1, 8, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
         //GridBagHelper.addComponent(panel, chgepass, 1, 8, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
       // GridBagHelper.addComponent(panel, radioPanel, 0, 6, 2, 1, 0, 0, 0.0, 0.0, GridBagConstraints.BOTH, GridBagConstraints.WEST);
        //GridBagHelper.addComponent(panel, checkPanel, 2, 6, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.BOTH, GridBagConstraints.WEST);

        GridBagHelper.addComponent(panel, bottomDistPanel, 0, 10, 1, 1, 0, 0, 0.0, 1.0, GridBagConstraints.VERTICAL, GridBagConstraints.NORTHWEST);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

    private JScrollPane createButtonPanel() throws ClassNotFoundException, SQLException 
    {
      
      
        
        JPanel panel = new JPanel(new GridBagLayout());
        updateButton = new JButton("Change Password");
        updateButton.setDefaultCapable(true);
        updateButton.setMnemonic(KeyEvent.VK_U);
        chgepass = new JButton("Change PASSWORD HERE");
        updateButton.addActionListener(new ActionListener() 
        {
         
            @Override
            public void actionPerformed(ActionEvent e)
            {
           Statement st;
           Connection con = null;
           constants connect = new constants();
           try
           {
               tpass = newpass.getText().toString();
               trepass = renewpass.getText().toString();
               toldpass = oldpass.getText().toString();
               System.out.println("--->"+tpass);
               System.out.println("-->"+trepass);
               System.out.println("->"+toldpass);
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
                String qr1 = "select PASSWORD from login where LOGIN_ID='"+obj.tlogin+"'";
                 st = con.createStatement();
                ResultSet rs = st.executeQuery(qr1);
               System.out.println("text feild value is:-->"+obj.tlogin);
              while(rs.next())
              {
                   oldvalue = rs.getString("PASSWORD");
                  // System.out.println("old password from database"+oldvalue);
               //    System.out.println(toldpass.toString());
              
              }
              rs.close();
              
            
              if(tpass.equals(trepass))
                  
              {
                  System.out.println("password is match");
              
                 if(toldpass.contains(oldvalue))
                {
                   
                  //  System.out.println("button is --here");
                    String qr = "update login set PASSWORD=?, REPASSWORD=? where LOGIN_ID='"+obj.tlogin+"' && PASSWORD='"+oldvalue.toString()+"'";
                    PreparedStatement insert = con.prepareStatement(qr);
                    insert.setString(1, tpass);
                    insert.setString(2, trepass);
//                  insert.setString(3, str);
                    int rs1 = insert.executeUpdate();
                    //System.out.println("row are affected"+tpass+"----->"+trepass);
                    System.out.println("password change successfully....coolboy");
                          JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel, "Password Change Successfully..", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
             
                      else
                       {
                             JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel, "Please Enter Correct Old Password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                         System.out.println("old password is wrong");
                    }
           }
              else
              {
                            JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel, "Password Are Not Match", "Error",
                            JOptionPane.ERROR_MESSAGE);
              }
           }
          catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
               //  System.out.println("button click this coolboy--->");
        
             }
        });
//        insertButton = new JButton("insert");
//        insertButton.setMnemonic(KeyEvent.VK_I);
//        deleteButton = new JButton("delete");
//        deleteButton.setMnemonic(KeyEvent.VK_D);

        GridBagHelper.setMinRowHeight(panel, 0, 8);
        GridBagHelper.setMinColWidth(panel, 0, 100);
        GridBagHelper.addComponent(panel, updateButton, 5, 7, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.LAST_LINE_END);
        GridBagHelper.addComponent(panel, chgepass, 5, 7, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.ABOVE_BASELINE);
//      GridBagHelper.addComponent(panel, insertButton, 0, 2, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
//      GridBagHelper.addComponent(panel, deleteButton, 0, 3, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
        GridBagHelper.addComponent(panel, new JPanel(), 0, 4, 1, 1, 0, 0, 0.0, 1.0, GridBagConstraints.VERTICAL, GridBagConstraints.NORTHWEST);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
    }

    private JScrollPane createTablePanel() {
        MyTableModel model = new MyTableModel();
        table = new JTable(model);
        TableColumn tableCol = table.getColumnModel().getColumn(5);
        //    tableCol.setCellRenderer(new CheckBoxRenderer());
        tableCol.setPreferredWidth(30);
        //table.getTableHeader().setReorderingAllowed(false);
        table.setRowSorter(new TableRowSorter(model));
        tableScrollPane = new JScrollPane(table);
        //tableScrollPane.getViewport().setBackground(Color.orange);
        return tableScrollPane;
    }

   

//------------------------------------------------------------------------------
    private class MyTableModel extends AbstractTableModel {

        public int getColumnCount() {
            return colNames.size();
        }

        public String getColumnName(int index) {
            return (String) colNames.get(index);
        }

        public int getRowCount() {
            return dataList.size();
        }

        public Object getValueAt(int rowIndex, int colIndex) {
            if (rowIndex < dataList.size()) {
                ArrayList rowData = (ArrayList) dataList.get(rowIndex);
                if (colIndex < rowData.size()) {
                    return rowData.get(colIndex);
                }
            }
            return "ERROR";
        }
    }
//------------------------------------------------------------------------------
//    class CheckBoxRenderer extends DefaultTableCellRenderer {
//
//        public Component getTableCellRendererComponent(JTable jTable, Object obj, boolean isSelected, boolean hasFocus, int row, int col) {
//            JCheckBox checkBox = new JCheckBox("");
//            checkBox.setOpaque(true);
//            checkBox.setForeground(jTable.getForeground());
//            checkBox.setBackground(jTable.getBackground());
//            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
//            if (isSelected) {
//                checkBox.setForeground(jTable.getSelectionForeground());
//                Color bc = new Color(jTable.getSelectionBackground().getRGB());
//                checkBox.setBackground(bc);
//            }
//            if (obj instanceof Boolean) {
//                checkBox.setSelected(((Boolean) obj).booleanValue());
//            }
//            return checkBox;
//        }
//    }
}
