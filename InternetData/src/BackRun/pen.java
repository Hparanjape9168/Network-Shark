package BackRun;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Timer;
import java.util.TimerTask;
import connection.constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pen {
 static String date1, myip, host, act,str;
    
       static String uname=System.getProperty("user.name");
    

    public static void main(String[] RK) throws SQLException, ClassNotFoundException {
       //final  pen p = new pen();
        
        
        Runtime runtime = Runtime.getRuntime();
         Timer myTimer = new Timer();
        //TimerTask mytask = new TimerTask() {
     
               
        Process proc = null;
        
        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        File[] drives = new File[letters.length];
        boolean[] isDrive = new boolean[letters.length];
        int totFiles = 0, totDirs = 0;

// init the file objects and the initial drive state
        for (int i = 0; i < letters.length; ++i) {
            drives[i] = new File(letters[i] + ":/");
            isDrive[i] = drives[i].canRead();
        }
      constants connect = new constants();
        Connection con1=null;
        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection(connection.constants.url,connection.constants.userName,connection.constants.Password);
         
            String qr1 = "SELECT PERMISSION from student_work_detail where ENROLLMENT_NUMBER='"+ uname+"'";
            Statement st1= con1.createStatement();
             ResultSet rs2 = st1.executeQuery(qr1);
             while(rs2.next())
             {
                str=rs2.getString("PERMISSION");
                
             }
             rs2.close();
             if(str.equals("YES"))
                {
                    System.out.println("permission is given ");
                }
             else
             {
        System.out.println("FindDrive: waiting for devices…");

        while (true) {

// check each drive
            for (int i = 0; i < letters.length; ++i) {
                boolean pluggedIn = drives[i].canRead();

// if the state has changed output a message
               
               
                if (pluggedIn != isDrive[i]) {
                    System.out.println("pen drive detect1" + letters[i]);
                    if (pluggedIn) {

                        String path = letters[i] + "://";
                        File files = new File(path);
                        String allFiles[] = files.list();

                        for (int k = 0; k < allFiles.length; k++) {
                            File f = new File(path + "/" + allFiles[k]);
                            if (f.isDirectory()) {
                                totDirs++;
                                System.out.println("Directory :" + allFiles[k]);
                            } else {
                                totFiles++;
                                System.out.println("File :" + allFiles[k]);
                            }
                        }
                        System.out.println("Total Directories and Files :" + allFiles.length);
                        System.out.println("Total Directories :" + allFiles.length);
                        System.out.println("Total Files :" + allFiles.length);
                        new BackRun.penjoptionpane();
                        try{
                         BackRun.illegal_access obj = new BackRun.illegal_access();
                         String date1 = obj.send_date();
                         String myip = obj.ip_address();
                         String host = obj.hostname();
        
        
        constants connect1 = new constants();
        Connection con=null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
        String query = "insert into pendrive(ipaddress,hostname,date) value(?,?,?);";
                        PreparedStatement insert = con.prepareStatement(query);
                        
                        insert.setString(1, myip);
                        insert.setString(2, host);
                        //insert.setString(3, str);
                        insert.setString(3, date1);

                        int rs1 = insert.executeUpdate();
                        //System.out.println("affected rows:" + rs1);
                        System.out.println("Data inserted successfully");
        
            
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                       /* try {
                            proc = runtime.exec("RUNDLL32 shell32.dll,Control_RunDLL hotplug.dll -l");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }*/
                    } else {
                        System.out.println("Drive " + letters[i] + " has been unplugged");
                    }

                    isDrive[i] = pluggedIn;
                }
                }            
            TimerTask mytask = new TimerTask() {

                @Override
                public void run() {
                    System.out.println("run after the 5 seconds");
                }
            };
            myTimer.schedule(mytask, 1000, 1000);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
        }
    }
    }
}
