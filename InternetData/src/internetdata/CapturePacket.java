package internetdata;

import connection.constants;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.JProtocol;

public class CapturePacket {

    int d;
    static int week1, week2, week3, week4, week5;
    static int w1, w2, w3, w4, w5;
    static Connection con = null;
    final constants connect = new constants();
    static String uname = System.getProperty("user.name");

    public void Netdownload() {

        System.setProperty("java.library.path", "C:/libs/jnetpcap-1.4.b0004-1");

        Field fieldSysPath;
        try {
            fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);


            //int i;
            Calendar now = Calendar.getInstance();
            Statement st = null;

            // Connection con1=null;
            // final constants connect = new constants();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);

            String q = "select * from student_work_detail WHERE ENROLLMENT_NUMBER='" + uname + "';";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(q);

            while (rs.next()) {
                week1 = rs.getInt("WEEK_1");
                week2 = rs.getInt("WEEK_2");
                week3 = rs.getInt("WEEK_3");
                week4 = rs.getInt("WEEK_4");
                week5 = rs.getInt("WEEK_5");

            }
            rs.close();
            // con1.close();
            List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with
            // NICs
            StringBuilder errbuf = new StringBuilder(); // For any error msgs
            System.out.println("main program");

            int r = Pcap.findAllDevs(alldevs, errbuf);
            if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
                System.err.printf("Can't read list of devices, error is %s", errbuf
                        .toString());
                return;
            }
            PcapIf device = (PcapIf) alldevs.get(0); // We know we have at least 1 device


            String ad = device.getHardwareAddress().toString();
            System.out.println("\nCurrently open adapter MAC:" + ad);

            int snaplen = 64 * 1024; // Capture all packets, no truncation
            int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
            int timeout = 10 * 1000; //10*1000; // No timeout, non-interactive traffic
            final Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout,
                    errbuf);

            if (pcap == null) {
                System.err.printf("Error while opening device for capture: "
                        + errbuf.toString());
                return;
            }

            System.out.println("after connect");
            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
                long total_traffic = 0, count = 0;
                int i, j;

                @Override
                public void nextPacket(PcapPacket packet, String user) {


                    Calendar now = Calendar.getInstance();
                    try {
//                   Class.forName("com.mysql.jdbc.Driver");
//                   con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    //System.out.println("in next ");
                    if (packet != null) {

                        count += packet.getCaptureHeader().caplen();
                        // System.out.println("-->"+count);

                        if (count > 1048576) {
                            try {
                                i++;


                                // j=(i*10)/1024;
                                System.out.println(i + "MB" + "\t total:" + total_traffic);

                                String query1 = "update student_work_detail set DATA_USE=? WHERE ENROLLMENT_NUMBER='" + uname + "';";
                                PreparedStatement insert1 = con.prepareStatement(query1);
                                insert1.setInt(1, i);
                                int rs555 = insert1.executeUpdate();
                                System.out.println("rows are affected" + rs555 + "store in database" + i);


                                if (now.get(Calendar.WEEK_OF_MONTH) == 1) {
                                    w1 = week1;
                                    String query = "update student_work_detail set WEEK_1=? WHERE ENROLLMENT_NUMBER='" + uname + "';";
                                    PreparedStatement insert = con.prepareStatement(query);
                                    insert.setInt(1, i + w1);
                                    int rs1 = insert.executeUpdate();
                                    System.out.println("rows are affected" + rs1 + "store in database" + i);
                                }

                                if (now.get(Calendar.WEEK_OF_MONTH) == 2) {
                                    w2 = week2;
                                    String query = "update student_work_detail set WEEK_2=? WHERE ENROLLMENT_NUMBER='" + uname + "';";
                                    PreparedStatement insert = con.prepareStatement(query);
                                    insert.setInt(1, i + w2);
                                    int rs1 = insert.executeUpdate();
                                    System.out.println("rows are affected" + rs1 + "store in database" + i);

                                }
                                if (now.get(Calendar.WEEK_OF_MONTH) == 3) {
                                    w3 = week3;
                                    String query = "update student_work_detail set WEEK_3=? WHERE ENROLLMENT_NUMBER='" + uname + "';";
                                    PreparedStatement insert = con.prepareStatement(query);
                                    insert.setInt(1, i + w3);
                                    int rs1 = insert.executeUpdate();
                                    System.out.println("rows are affected" + rs1 + "store in database" + i);

                                }
                                if (now.get(Calendar.WEEK_OF_MONTH) == 4) {
                                    w4 = week4;
                                    String query = "update student_work_detail set WEEK_4=? WHERE ENROLLMENT_NUMBER='" + uname + "';";
                                    PreparedStatement insert = con.prepareStatement(query);
                                    insert.setInt(1, i + w4);
                                    int rs1 = insert.executeUpdate();
                                    System.out.println("rows are affected" + rs1 + "store in database" + i + w4 + "Week is going:=" + now.get(Calendar.WEEK_OF_MONTH));


                                }
                                if (now.get(Calendar.WEEK_OF_MONTH) == 5) {
                                    w5 = week5;
                                    String query = "update student_work_detail set WEEK_5=? WHERE ENROLLMENT_NUMBER='" + uname + "';";
                                    PreparedStatement insert = con.prepareStatement(query);
                                    insert.setInt(1, i + w5);
                                    int rs1 = insert.executeUpdate();
                                    System.out.println("rows are affected" + rs1 + "store in database" + i + w5 + "Week is going:=" + now.get(Calendar.WEEK_OF_MONTH));

                                }

                                count = count - 1048576;
                             //           JOptionPane.INFORMATION_MESSAGE);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }

                    }
                }
            ;
            };        
               
            pcap.loop(-1, jpacketHandler, " ");
            pcap.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
//    public static void main(String[] args) throws Exception 
//     {
//        
//        CapturePacket obj = new CapturePacket();
//        obj.Netdownload();
//     }  
}
