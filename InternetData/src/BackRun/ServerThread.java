
package BackRun;

import Nettest.echoServer;
import connection.constants;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ServerThread extends Thread {

    constants connect = new constants();
    Connection con = null;
    static String fileNameToSaveTo;
    static public String path, username, ipaddress;
    echoServer obj = new echoServer();
    public static String str;
//    String user_name=obj.username;
//    String ip_addr =obj.ipaddress;
   
   
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
                 str=br.readLine();
                //obj.myrun(str);
                System.out.print(str);
                ps.println(str);
               
           
        
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    }
