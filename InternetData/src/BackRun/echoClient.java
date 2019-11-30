package BackRun;

//import com.jtattoo.demo.app.userlogin;
import Nettest.*;
import BackRun.*;
import connection.constants;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import connection.constants;
import java.awt.Graphics;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.util.Scanner;

public class echoClient {

    public static int str1;
    static int str, str2;
    constants connect = new constants();
    Connection con = null;
    String uname, ip;
    public static String strmain;
    //echoServer server = new echoServer();
    public static boolean finalflag;
    
    public void clientside() throws Exception {
        try {

           String i;
            InetAddress address = InetAddress.getLocalHost();
            Socket s1 = null;
            s1 = new Socket("192.168.0.100", 4555); // You can use static final constant PORT_NUM
            System.out.println("Client Address : " + address);
             
        OutputStream os = s1.getOutputStream();
        InputStream is = s1.getInputStream();
        Scanner scan = new Scanner(System.in);
        PrintStream pw = new PrintStream(os);
        
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is));

            System.out.println("Enter The message:");
            i ="hi";
            pw.println(i);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is));
            String my = br2.readLine();
            
           if(i.equals(my))
           {
            
            System.out.println("Message Back From Server\n" + my);
            finalflag=true;
            }
            
          
            
           

           

            System.out.println("Client side run successfully");
        
        }catch (Exception ex) 
        {
          finalflag=false;
            System.out.println("Socket Closeee.....");

        }

    }

 
        
    public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
        final Connection con;
        constants connect = new constants();

       
    
        final echoClient sc = new echoClient();

        java.util.Timer myTimer = new java.util.Timer();
        TimerTask mytask = new TimerTask() {
            @Override
            public void run() 
            {
                try {
                    sc.clientside();
                } catch (Exception ex) {
                    Logger.getLogger(echoClient.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
        };
        System.out.println("timer start" + 10);

        myTimer.schedule(mytask, 10000, 10000);
       
//
    }
}