package Snapshot;
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

public class Server_X_Client {

    constants connect = new constants();
    Connection con = null;

    public Server_X_Client() {
//      try 
//              {
//               
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
//            
//            String qr = "select * from snapshot order by ID DESC LIMIT 1";
//            PreparedStatement insert = con.prepareStatement(qr);
//            ResultSet rs2 = insert.executeQuery();
//            while(rs2.next())
//            {
//                username = rs2.getString("USERNAME");
//                ipaddress = rs2.getString("IP_ADDRESS");
//                System.out.println("username-->"+username);
//                System.out.println("ip address"+ipaddress);
//            }
        //   String qr1 = "insert into snapshot(SNAPSHOT_PATH) value(?)";
        //     PreparedStatement insert1 = con.prepareStatement(qr1);
        // insert1.setString(1,fileNameToSaveTo);
        // int rs3 = insert1.executeUpdate();
        //   System.out.println("path save successfull"+fileNameToSaveTo);
        //System.out.println("Sanpshot are store in Drive Full path is:"+fileNameToSaveTo);
//            con.close(); 
//            }
//            catch (Exception ex) 
//            {
//               ex.printStackTrace(); 
//            }
    }

    public static void main(String args[]) {
        Socket s = null;
        ServerSocket ss2 = null;
        System.out.println("Server Listening......");
        try {
            ss2 = new ServerSocket(4445); // can also use static final PORT_NUM , when defined
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
    Server_X_Client obj = new Server_X_Client();
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

    public ServerThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);

            String qr = "select * from ip_address order by ID DESC LIMIT 1";
            PreparedStatement insert = con.prepareStatement(qr);
            ResultSet rs2 = insert.executeQuery();
            while (rs2.next()) {
                // username = rs2.getString("USERNAME");
                ipaddress = rs2.getString("IP_ADDRESS");
                //  System.out.println("username-->"+username);
                System.out.println("ip address" + ipaddress);
            }

            System.out.println("server ready");
            Date curdate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
            String datetostr = format.format(curdate);

            datetostr = format.format(curdate);
            System.out.println(datetostr);
            fileNameToSaveTo = "D:/temp/" + datetostr + createTimeStampStr() + ".JPG";
            BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(s.getInputStream()));
            ImageIO.write(img, "JPG", new File("D:/temp/" + datetostr + createTimeStampStr() + ".JPG"));
            System.out.println("Image received!!!!");
            String qr1 = "insert into snapshot(IP_ADDRESS,SNAPSHOT_PATH) values(?,?)";
            PreparedStatement insert1 = con.prepareStatement(qr1);
            //insert1.setString(1,user_name);
            insert1.setString(1, ipaddress);
            insert1.setString(2, fileNameToSaveTo);
            int rs3 = insert1.executeUpdate();
            s.close();
            System.out.println("path save successfull" + fileNameToSaveTo);
            System.out.println("Sanpshot are store in Drive Full path is:" + fileNameToSaveTo);
            System.out.println("Server side run successfully" + rs3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}