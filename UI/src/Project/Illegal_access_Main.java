
package Project;

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





public class Illegal_access_Main 
{
    static String date1, myip, host, act;
     public static String str=null,str1=null,str2=null;
    static boolean b; 
    Runtime runtime = Runtime.getRuntime();
    static String uname=System.getProperty("user.name");
    public void my() throws RemoteException, ClassNotFoundException, SQLException, IOException
    {
    
    illegal_access obj = new illegal_access();
    
    String[] list = obj.receive();
    String date1 = obj.send_date();
    String myip = obj.ip_address();
    String host = obj.hostname();
    
      Connection con=null;
      constants connect = new constants();
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
            
            String qr1 = "SELECT PERMISSION_ILLEGAL from student_work_detail where ENROLLMENT_NUMBER='"+ uname+"'";
            Statement st1= con.createStatement();
             ResultSet rs2 = st1.executeQuery(qr1);
             while(rs2.next())
             {
                str2=rs2.getString("PERMISSION_ILLEGAL");
                
             }
             rs2.close();
             if(str2.equals("YES"))
                {
                    System.out.println("permission is given ");
                }
             else
             {
             
             
            
            String qr= "SELECT * FROM process";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(qr);
         //   System.out.println("hiiii.......");
            while(rs.next())
            {
             str=rs.getString("Process_Name");
            
            for(int i=0;i<list.length;i++)
            {
                //System.out.println(list[i]);
                if(list[i]!=null)
                {
                  if((list[i].contains(str))== true)
                 {
                     new jopitionpane();
                     Process proc = runtime.exec("TASKKILL /IM "+str );
                  
            System.out.println("found");
            System.out.println(str);
            String query = "insert into illegal_access(ENROLL_NUMBER,IP_ADDRESS,ACTIVITY,DATE) value(?,?,?,?);";
            PreparedStatement insert = con.prepareStatement(query);
            insert.setString(1, host);
            insert.setString(2, myip);
            insert.setString(3, str);
            insert.setString(4, date1);

            int rs1 = insert.executeUpdate();
            //System.out.println("affected rows:" + rs1);
            System.out.println("Data inserted successfully");
            break;
                  
                  
                  }
                 }
             }
            }
            rs.close();
            
             }    
    
 }
    public static void main(String[] args)
    {
             final Illegal_access_Main obj1 = new Illegal_access_Main();
             Timer myTimer = new Timer();
             TimerTask mytask = new TimerTask() 
             {
              
           @Override
            public void run()
            {
                     try 
                     {
                         obj1.my();
                         System.out.println("Runafter 5 seconds---->");
                     } catch (Exception ex) 
                     {
                         ex.printStackTrace();
                     }
            }
            };
             myTimer.schedule(mytask, 6000, 6000);
             
         
           try
           {
         Thread.sleep(10000);
         }
           catch (InterruptedException exc)
       {
           exc.printStackTrace();
        }
     } 

                  
   }