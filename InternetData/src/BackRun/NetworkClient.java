package BackRun;

//import com.jtattoo.demo.app.userlogin;
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
import java.net.Inet4Address;

public class NetworkClient {

    public static int str1;
    static int str, str2;
    constants connect = new constants();
    Connection con = null;
    String uname, ip;
    // ServerThread my = null;
//    public void send_data() throws Exception {
//        try {
//            //  userlogin user = new userlogin();
////            uname=user.tlogin.toString();
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
//
//            InetAddress iapr = InetAddress.getLocalHost();
//            ip = iapr.getHostAddress().toString();
//
//            //ip=InetAddress.getLocalHost().getHostAddress();
//            String query = "insert into ip_address(IP_ADDRESS) value(?);";
//            PreparedStatement insert = con.prepareStatement(query);
//            // insert.setString(1, uname);
//            insert.setString(1, ip);
//            System.out.println("insert in database" + uname);
//            int rs1 = insert.executeUpdate();
//
//            if (rs1 == 1) {
//                System.out.println("inserted username");
//            } else {
//                System.out.println("something wrong!!!");
//            }
//            con.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }

    public void clientside() throws Exception {
        try {


            InetAddress address = InetAddress.getLocalHost();
            Socket s1 = null;
            s1 = new Socket(connect.myip, 4445); // You can use static final constant PORT_NUM
            System.out.println("Client Address : " + address);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRectangle = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRectangle);

            System.out.println("Image object:=" + image);
            Graphics g = image.getGraphics();
            g.setFont(g.getFont().deriveFont(30f));
            g.drawString(Inet4Address.getLocalHost().getHostAddress() + " " + InetAddress.getLocalHost().getHostName(), 200, 100);
            g.dispose();


            ImageIO.write(image, "JPG", s1.getOutputStream());

            System.out.println(" image send");

            s1.close();

            System.out.println("Client side run successfully");
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

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
                
                // sc.send_data();
            } else {
                System.out.println("not take snap------>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str1;
    }

//    public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {
//        final Connection con;
//        constants connect = new constants();
//
//       
//    
//        final NetworkClient sc = new NetworkClient();
//
//        java.util.Timer myTimer = new java.util.Timer();
//        TimerTask mytask = new TimerTask() {
//            @Override
//            public void run() {
//               }
//        };
//        System.out.println("timer start" + str1);
//
//        myTimer.schedule(mytask, str1, str1);
//        try {
//            Thread.sleep(str1);
//        } catch (InterruptedException exc) {
//            exc.printStackTrace();
//        }
////
//    }
}