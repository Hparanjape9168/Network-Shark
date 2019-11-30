/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackRun;

import connection.constants;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Illegal_access_Main {

    static String date1, myip, host, act;
    public static String str1 = null;
    public static String str[] = new String[30];
    Runtime runtime = Runtime.getRuntime();
    // constants connect = new constants();
    Connection con = null;
    static int counter = 0;
    echoClient echo = new echoClient();
    static public String[] list;

    public void my() throws RemoteException, ClassNotFoundException, SQLException, IOException {

        illegal_access obj = new illegal_access();

        list = obj.receive();
        String date1 = obj.send_date();
        String myip = obj.ip_address();
        String host = obj.hostname();

        if (echo.finalflag) {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connection.constants.url, connection.constants.userName, connection.constants.Password);
            String qr = "SELECT * FROM process";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qr);
            //   System.out.println("hiiii.......");
            int j = 0;
            while (rs.next()) {
                str[j] = rs.getString("Process_Name");
                j++;
            }
            rs.close();
            for (int k = 0; k < str.length; k++) {
                for (int i = 0; i < list.length; i++) {
                    //System.out.println(list[i]);
                    if (list[i] != null) {
                        if ((list[i].contains(str[k]))) {


                            new jopitionpane();
                            Process proc = runtime.exec("TASKKILL /IM " + str[k]);

                            //System.out.println(str);
                            //System.out.println("found");

                            System.out.println("found");
                            System.out.println(str[k]);
                            String query = "insert into illegal_access(ENROLL_NUMBER,IP_ADDRESS,ACTIVITY,DATE) value(?,?,?,?);";
                            PreparedStatement insert = con.prepareStatement(query);
                            insert.setString(1, host);
                            insert.setString(2, myip);
                            insert.setString(3, str[k]);
                            insert.setString(4, date1);

                            int rs1 = insert.executeUpdate();
                            //System.out.println("affected rows:" + rs1);
                            System.out.println("Data inserted successfully");
                            break;


                        }
                    }
                }
            }


            // System.out.println("Client Date is:-->" + date1);
            //System.out.println("Ip Address of Client side:-->" + myip);
            //System.out.println("Host Name Is:-->" + host);
            //System.out.println("Ip Address of Client side:-->" + myip);
//            con.close();
        } else {
            for (int k = 0; k < str.length; k++) {
                for (int i = 0; i < list.length; i++) {
                    //System.out.println(list[i]);
                    if (list[i] != null) {
                        if ((list[i].contains(str[k]))) {

                            new jopitionpane();
                            Process proc = runtime.exec("TASKKILL /IM " + str[k]);
                            System.out.println("In else condition...");
                        }
                    }
                }
            }
        }
    }
//    public static void main(String[] args)
//    {
//             final Illegal_access_Main obj1 = new Illegal_access_Main();
//             Timer myTimer = new Timer();
//             TimerTask mytask = new TimerTask() 
//             {
//              
//           @Override
//            public void run()
//            {
//                     try 
//                     {
//                         obj1.my();
//                         System.out.println("Runafter 5 seconds---->");
//                     } catch (Exception ex) 
//                     {
//                         ex.printStackTrace();
//                     }
//            }
//            };
//             myTimer.schedule(mytask, 6000, 6000);
//             
//         
//           try
//           {
//         Thread.sleep(5000);
//         }
//           catch (InterruptedException exc)
//       {
//           exc.printStackTrace();
//        }
//     } 
//
//            
}
//

