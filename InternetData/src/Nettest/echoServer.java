package Nettest;

import connection.constants;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class echoServer {

    constants connect = new constants();
    Connection con = null;

    public echoServer() {
    }

    public static void main(String args[]) {
        Socket s = null;
        ServerSocket ss2 = null;
        System.out.println("Server Listening....Echo");
        try {
            ss2 = new ServerSocket(4555); // can also use static final PORT_NUM , when defined
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");
        }

        while (true) {
            try {
                s = ss2.accept();
                System.out.println("connection Established");
                ServerThread st = new ServerThread(s);
                st.start();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");
            }
        }
    }
}
class ServerThread extends Thread {

    constants connect = new constants();
    Connection con = null;
    static String fileNameToSaveTo;
    static public String path, username, ipaddress;
    echoServer obj = new echoServer();
//    String user_name=obj.username;
//    String ip_addr =obj.ipaddress;

    public static String createTimeStampStr() throws Exception {
        Calendar mycalendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("hhmmss");
        String timeStamp = formatter.format(mycalendar.getTime());
        return timeStamp;
    }
    String line = null;
    BufferedReader is = null;
    PrintWriter os = null;
    Socket s = null;

    public ServerThread(Socket s)
    {
        this.s = s;
    }

    public void run() {
        try 
        {
           
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();
            PrintStream ps = new PrintStream(os);
           
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String str=br.readLine();
                System.out.print(str);
                ps.println(str);
               
           
        
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
}