package com.jtattoo.demo.app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import connection.constants;
public class Pendrive_Database
{
    private JPanel mainPanel = new JPanel();
  constants obj =new constants();
    public Pendrive_Database()
    {
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();
       
        //  Connect to an MySQL Database, run query, get result set
        String url = obj.url;
        String userid = obj.userName;
        String password = obj.Password;
        String sql = "SELECT * FROM pendrive";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
          
            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                   // System.out.println(row);
                }

                data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }

        // Create Vectors and copy over elements from ArrayLists to them
        // Vector is deprecated but I am using them in this example to keep 
        // things simple - the best practice would be to create a custom defined
        // class which inherits from the AbstractTableModel class
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));
        JTable table = new JTable(dataVector, columnNamesVector);
        
        JPanel sidePanel = new JPanel();
        JLabel sideLabel = new JLabel("Pendrive Database");
        sidePanel.add(sideLabel);
        
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(table, BorderLayout.CENTER);
        viewPanel.add(sidePanel, BorderLayout.EAST);
        
        
        JScrollPane scroll = new JScrollPane(viewPanel);
        scroll.setColumnHeaderView(table.getTableHeader());
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scroll, BorderLayout.CENTER);
        
    }
 
    public JPanel getMainPanel1()
    {
        return mainPanel;
    }
 
    public static void createAndShowUI()
    {
        JFrame frame = new JFrame("TableAndStuff");
        frame.getContentPane().add(new Pendrive_Database().getMainPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
//    public static void main(String[] args)
//    {
//        java.awt.EventQueue.invokeLater(new Runnable()
//        {
//            public void run()
//            {
//                createAndShowUI();
//            }
//        });
//    }
}