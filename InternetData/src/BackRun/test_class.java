/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackRun;

import connection.constants;
import internetdata.CapturePacket;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

class test_class {

    final static Illegal_access_Main ill = new Illegal_access_Main();
    final static NetworkClient snap = new NetworkClient();
    final static pentest penobj = new pentest();
    final static Labon_off lab = new Labon_off();
    final static Ip_update ip = new Ip_update();
    final static CapturePacket net = new CapturePacket();
   public static echoClient echo = new echoClient();
    static public int interval;
    public static int time1;
   public  static int str, str2,str1;
    static constants connect = new constants();
    static Connection con = null;
    public static int flag;
   
    //  String s1="sunil",s2="sunil";
    //  private String threadName;
    
     public int start_snap()
    {
        try {
            
            Statement st;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connection.constants.url, connection.constants.userName, connection.constants.Password);

            String qr1 = "SELECT Snap_Start_Stop FROM snap_yes_no";
            st = con.createStatement();
            ResultSet rs1 = st.executeQuery(qr1);
            if (rs1 != null) {
                while (rs1.next()) {
                    str2 = rs1.getInt("Snap_Start_Stop");
                }
                rs1.close();
            }
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        return str2;
    }
         public int timevalue()
         {
            try
            {
            Statement st1;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connection.constants.url, connection.constants.userName, connection.constants.Password);

            String qr = "SELECT Time_Value FROM timervalue";
            st1 = con.createStatement();
            ResultSet rs = st1.executeQuery(qr);

            while (rs.next()) {
                str = rs.getInt("Time_Value");
                
            }
            rs.close();
            str1 = str * 60;
            System.out.println("timer start" + str1);

            if (str2 == 1) {
                System.out.println("Taking snap shot");
                
//                 sc.send_data();
            } else {
                System.out.println("not take snap------>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str1;
    }
     
   
    
    public static void main(String args[]) 
    {
        try {
            
            final test_class obj1 = new test_class();
           
           
             
             Timer myTimer = new Timer();
             TimerTask mytask = new TimerTask() {
                 @Override
                 public void run() {
                     
                     
                     
                     
                     flag = obj1.start_snap();
                         
                         time1 = obj1.timevalue();
                     try {

                         System.out.println("Runafter 10 seconds---->");

                         Thread t1 = new Thread() {  // illegal Access process
                             @Override
                             public void run() {
                                 try {
                                     
                                     ill.my();
                                     
                                     System.out.println("Calling Illegal--------->>");
                                 } catch (Exception ex) {
                                     ex.printStackTrace();
                                 }
                             }
                         };
                         t1.start();
                         
                         Thread tmain = new Thread(){    // echo calling 

                             @Override
                             public void run() 
                             {
                                 try 
                                 {
                                     echo.clientside();
                                     System.out.println("Echo socket runnig");
                                 } catch (Exception ex) 
                                 {
                                     
                                 }
                             }
                             
                         };
                         tmain.start();


                         Thread t3 = new Thread() {     // lab/off
                             @Override
                             public void run() {
                                 try {
                                        lab.labmain();
                                        System.out.println("Calling the LAb On/Off---->");
                                 } catch (Exception ex) {
                                     ex.printStackTrace();
                                 }
                             }
                         };
                         t3.start();

     //                    

                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }
                 }
             };
             myTimer.schedule(mytask, 15000, 15000);

             Thread t2 = new Thread() {     // netdownloading monitoring
                 @Override
                 public void run() {
                     try {
                          if(echoClient.finalflag)
                                     {
                         System.out.println("Calling the Net Dwon---->");
                         
                         net.Netdownload();
                                     }
                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }
                 }
             };
             t2.start();

             Thread t6 = new Thread() {     // ip address assinging
                 @Override
                 public void run() {        
                     try {
                          if(echoClient.finalflag)
                                     {
                         System.out.println("Calling the Ip Update>");
                         ip.myfun();
                                     }
                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }
                 }
             };
             t6.start();

             Thread t4 = new Thread() {     //pendrive
                 @Override
                 public void run() {
                     try {
                         System.out.println("Calling the Pen Drive---->");
                         penobj.my();

                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }
                 }
             };
             t4.start();
             
               Thread t5 = new Thread() {       //snapshot running
                 @Override
                 public void run() {
                     while(true)
                     {
                     try {
                          if(echoClient.finalflag)
                                     {
                         System.out.println("Calling snapshot--->");
                         
                          flag = obj1.start_snap();
                          time1 = obj1.timevalue();
                          System.out.println("Falg value:--->"+flag);
                          System.out.println("time value:--->"+time1);
                          
                         if (flag == 1) 
                         {
                             
                             System.out.println("Taking snap shot");
                             snap.clientside();
                             // sc.send_data();
                         } else 
                         {
                             System.out.println("not take snap------>");
                         }
                         
                       Thread.sleep(time1);
                                     }
                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }
                     }
                 }
              };
             t5.start();
            
             
        


     //        Timer myTimer1 = new Timer();
     //        TimerTask mytask1 = new TimerTask() {
     //            @Override
     //            public void run() {
     //                try {
     //                     flag = obj1.start_snap();
     //                    
     //                     time1 = obj1.timevalue();
     //                     System.out.println("Falg value:--->"+flag);
     //                     System.out.println("time value:--->"+time1);
     //                     
     //                    if (flag == 1) 
     //                    {
     //                        System.out.println("Taking snap shot");
     //                        snap.clientside();
     //                        // sc.send_data();
     //                    } else {
     //                        System.out.println("not take snap------>");
     //                    }
     //                } catch (Exception ex) {
     //                    ex.printStackTrace();
     //                }
     //               
     //                System.out.println("Runafter " + time1 + " seconds---->");
     //            }
     //        };
     //        myTimer1.schedule(mytask1, time1,time1);
              
              
        } catch (Exception ex) {
            Logger.getLogger(test_class.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
