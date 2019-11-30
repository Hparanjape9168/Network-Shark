package DataDownload;

import connection.constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

public class CapturePacket 
{
    
int d; 

    public static void main(String[] args) throws Exception 
    {
    //int i;
        Statement st = null;
          JPanel panel = new JPanel();
                            JOptionPane.showMessageDialog(panel, "Create New Schedule Successfully..", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                
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
            int i,j;

            @Override
            public void nextPacket(PcapPacket packet, String user) 
            {
                Connection con=null;
                constants connect = new constants();
                try 
                {
                    Class.forName("com.mysql.jdbc.Driver");
                   con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);

                } 
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                
            
                //System.out.println("in next ");
                if (packet != null) {
                    
                    count += packet.getCaptureHeader().caplen();
                   // System.out.println("-->"+count);
                    if (count >1048576) {
                        try {
                            i++;
                            
                           // j=(i*10)/1024;
                             System.out.println(i+ "MB" + "\t total:" + total_traffic);
                            String query = "update student_work_detail set DATA_USE=? WHERE ENROLLMENT_NUMBER='Hp';";
                            PreparedStatement insert = con.prepareStatement(query);
                            insert.setInt(1, i);   
                            int rs1 = insert.executeUpdate();
                            System.out.println("rows are affected"+rs1+ "store in database"+i );
                             
                            count = count - 1048576;
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }

                    }
                     
                }
            }
        ;
        };        
        
                            
      
        pcap.loop(-1, jpacketHandler, " ");
        pcap.close();


    }
}
