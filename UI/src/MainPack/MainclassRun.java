
package MainPack;

import connection.constants;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainclassRun 
{
     public static String user,ip; 
     constants connect = new constants();
     Connection con=null;
     
     
     public void myfun() throws ClassNotFoundException, SQLException
     {
           InetAddress iapr;
        try {
            iapr = InetAddress.getLocalHost();
            ip = iapr.getHostAddress().toString();
        } 
        catch (UnknownHostException ex) 
        {
            ex.printStackTrace();
        }
            
            user = System.getProperty("user.name");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
            String qr ="update student_work_detail set IP_ADDRESS=? where ENROLLMENT_NUMBER='"+ user+"'";
            PreparedStatement insert = con.prepareStatement(qr);
            insert.setString(1,ip);
            int rs = insert.executeUpdate();
            System.out.println("Rows Affected:--"+rs);
     }
    public static void main(String[] args) 
    {
       
           MainclassRun obj = new MainclassRun();
        try 
        {
            obj.myfun();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(MainclassRun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainclassRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
