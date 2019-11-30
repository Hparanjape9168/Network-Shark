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
public class userlogin extends JPanel {

    public JPanel widgetPanel = null;
    public JScrollPane tablePanel = null;
    public JSplitPane splitPane = null;
    private JComboBox addressCombo = null;
    private JTextField loginid = null;
    // public JLabel enrll = null;
    public JLabel ipaddr = null;
    static public String enrll, ip;
    public String oldvalue;
    static public int datause;
    private JPasswordField password = null;
    //  private JPasswordField renewpass = null;
    // private JPasswordField oldpass =null;
    private JButton login = null;
    private JLabel dis;
    static int count;
    
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
    public static String tlogin, tpassword;
    public static boolean rs;
    public String login1=null; 
    public String password1=null;
    

    public userlogin() throws ClassNotFoundException, SQLException {
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

    private void init() throws ClassNotFoundException, SQLException {
        setName("User Login");
        initModel();
        initControls();
        initListeners();


    }

    private void initModel() throws ClassNotFoundException, SQLException {
    }

    private void initControls() {
        widgetPanel = createWidgetPanel();
//        tablePanel = createTablePanel();
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, widgetPanel, tablePanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(320);
        add(splitPane, BorderLayout.CENTER);
    }

    private void initListeners() {
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (getRootPane() != null) {
                            getRootPane().setDefaultButton(updateButton);
                            System.out.println("update this now press");
                            login.repaint();
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

    private JScrollPane createFormPanel() {
        addressCombo = new JComboBox(new String[]{"Mr.", "Mrs.", "Sir", "Lady", "Herr", "Frau"});
        addressCombo.setToolTipText("Select salutation!");
        password = new JPasswordField();
        loginid = new JTextField();
        login = new JButton("Login");
        //oldpass=new JPasswordField();


//        streetField = new JTextField();
//        cityField = new JTextField();
//        cityField.setToolTipText("<html>This is a long tool tip (also known as bubble help)<br/>for the city text field.<br/><br/>Here comes some more useless text.");

        //    JPanel radioPanel = new JPanel(new BorderLayout());
        //  radioPanel.setBorder(JTBorderFactory.createTitleBorder("favorite color"));
//        redButton = new JRadioButton("red");
//        greenButton = new JRadioButton("green");
//        blueButton = new JRadioButton("blue");
//        radioPanel.add(redButton, BorderLayout.NORTH);
//        radioPanel.add(greenButton, BorderLayout.CENTER);
//        radioPanel.add(blueButton, BorderLayout.SOUTH);
//        buttonGroup = new ButtonGroup();
//        buttonGroup.add(redButton);
//        buttonGroup.add(greenButton);
//        buttonGroup.add(blueButton);
//        redButton.setSelected(true);

//        JPanel checkPanel = new JPanel(new BorderLayout());
//        checkPanel.setBorder(JTBorderFactory.createTitleBorder("favorite food"));
//        bananaButton = new JCheckBox("bananas");
//        burgerButton = new JCheckBox("hamburgers");
//        icecreamButton = new JCheckBox("icecream");
//        checkPanel.add(bananaButton, BorderLayout.NORTH);
//        checkPanel.add(burgerButton, BorderLayout.CENTER);
//        checkPanel.add(icecreamButton, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new GridBagLayout());
        //panel.setOpaque(false);
        JPanel topDistPanel = new JPanel();
        JPanel bottomDistPanel = new JPanel();
        GridBagHelper.addComponent(panel, topDistPanel, 0, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST);

        // GridBagHelper.addComponent(panel, new JLabel("User Login Name: "+enrll), 0, 1, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        // GridBagHelper.addComponent(panel, addressCombo, 1, 1, 1, 1, 0, 0, 0.3, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
        GridBagHelper.addComponent(panel, new JPanel(), 2, 1, 1, 1, 0, 0, 0.7, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        // GridBagHelper.addComponent(panel, new JLabel("Today Data Usage:"+datause+" MB"), 0, 2, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        // GridBagHelper.addComponent(panel, firstNameField, 1, 2, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        //GridBagHelper.addComponent(panel, new JLabel("IP_Address Of Access Pcs: "+ip), 0, 3, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
//        GridBagHelper.addComponent(panel, lastNameField, 1, 3, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagHelper.addComponent(panel, new JLabel("User Id"), 0, 5, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, loginid, 1, 5, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);


        GridBagHelper.addComponent(panel, new JLabel("PassWord:"), 0, 6, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, password, 1, 6, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

         GridBagHelper.addComponent(panel, login, 1, 8, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.VERTICAL, GridBagConstraints.WEST);

        GridBagHelper.addComponent(panel, bottomDistPanel, 0, 10, 1, 1, 0, 0, 0.0, 1.0, GridBagConstraints.VERTICAL, GridBagConstraints.NORTHWEST);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

    private JScrollPane createButtonPanel() throws ClassNotFoundException, SQLException {

    
   
          //toldpass = oldpass.getText();

        //trepass = renewpass.getText();

        JPanel panel = new JPanel(new GridBagLayout());
        //login = new JButton("Login");
        login.setDefaultCapable(true);
        login.setMnemonic(KeyEvent.VK_U);
        // login = new JButton("Change PASSWORD HERE");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement st;
                Connection con = null;
                constants connect = new constants();
                try {
                    tlogin = loginid.getText().toString();
                    tpassword = password.getText().toString();
                    //toldpass = oldpass.getText().toString();
                    System.out.println("--->" + tlogin);
//               System.out.println("-->"+trepass);
//               System.out.println("->"+toldpass);
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
                    st = con.createStatement();
                    String qr1 = "select LOGIN_ID, PASSWORD from login where LOGIN_ID='" + tlogin + "' AND PASSWORD='" + tpassword + "'";
                    ResultSet rs1 = st.executeQuery(qr1);
                    
                    while (rs1.next()) 
                    {
                        login1 = rs1.getString("LOGIN_ID");
                        password1 = rs1.getString("PASSWORD");
                        }
                    rs1.close();
                    if(login1!=null && password1!=null)
                    {
                        
                        if ((login1.equals(tlogin)) && (password1.equals(tpassword))) 
                        {
                            JTattooDemo obj = new JTattooDemo();
                            obj.init();
                           // int flag=1;
                            rs=true;
                            System.out.println("login successfullyy..."+login1);

                            JTattooDemoLogin obj1 = new JTattooDemoLogin();
                            obj1.demologinclose();
                        }
                        else
                        {
                            JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel, "Enter Wrona Password Try Again", "Error",
                            JOptionPane.WARNING_MESSAGE);
                            rs=false;
                          System.out.println("user not login"+rs);
                    // System.out.println("burtton is click");
                        }

                    }
                    else
                    {
                        
                            JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel, "Please Enter the Correct Password", "Error",
                            JOptionPane.WARNING_MESSAGE);
                        System.out.println("user not login");
                    }
                 

                 

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                  System.out.println("button click this coolboy--->");
                  count++;
                  if(count>=3)
                  {
                            JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel, "You Are Try To Access Unauthorized Account", "Error",
                            JOptionPane.WARNING_MESSAGE);
                            
                            JTattooDemoLogin obj = new JTattooDemoLogin();
                            obj.demodispose();
                                    
                  }

            }
        });
//        insertButton = new JButton("insert");
//        insertButton.setMnemonic(KeyEvent.VK_I);
//        deleteButton = new JButton("delete");
//        deleteButton.setMnemonic(KeyEvent.VK_D);

        GridBagHelper.setMinRowHeight(panel, 0, 8);
        GridBagHelper.setMinColWidth(panel, 0, 100);

        // GridBagHelper.addComponent(panel, login, 1, 7, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        //   GridBagHelper.addComponent(panel, login, 5, 7, 8, 2, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.BASELINE);
        // GridBagHelper.addComponent(panel, login, 7, 7, 5, 2, 0, 0, 1.0, 0.0, GridBagConstraints.VERTICAL, GridBagConstraints.CENTER);
        // GridBagHelper.addComponent(panel, chgepass, 5, 7, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.ABOVE_BASELINE);
       // GridBagHelper.addComponent(panel, login, 0, 5, 1, 3, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
//      GridBagHelper.addComponent(panel, deleteButton, 0, 3, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
        GridBagHelper.addComponent(panel, new JPanel(), 0, 4, 1, 1, 0, 0, 0.0, 1.0, GridBagConstraints.VERTICAL, GridBagConstraints.NORTHWEST);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
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
}
