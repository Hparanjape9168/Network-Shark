 
package BackRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Date;


public class illegal_access 
{
    
    String line;
    String[] list1=new String[1000];
    static int i=0;
    String ip,myhostname,date2;

    public String[] receive() throws RemoteException
    {
     try
        {
            Process proc = Runtime.getRuntime().exec("tasklist");
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
            while ((line = input.readLine()) != null)
            {
                list1[i]=line;
                i++;
            }
            input.close();       
        }
		catch (RemoteException ex) 
        {
            System.out.println("Remoteexception");
        }
        catch (IOException ex) 
        {
            System.out.println("ioexception");
        } 
        String[] list2=new String[i];
        for(int j=0;j<i;j++)
        {
           list2[j]=list1[j];
        }
		i=0;
       return(list2);  
      }
      public String send_date() throws RemoteException 
    {
        Date date = new Date();
        String date2= date.toString();
        return date2;
    }
      public String ip_address() throws RemoteException
    {
      //  String ip=null;
        
        try
        {
        ip=InetAddress.getLocalHost().toString();
       
        }
        catch(UnknownHostException ex1)
        {
          ex1.printStackTrace();       
        }
        return ip;
        
    }
       public String hostname() throws RemoteException
    {
         
        //String myhostname=null;
        try
        {
       
        com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
         myhostname = NTSystem.getName();
        }
        catch(Exception ex1)
        {
          ex1.printStackTrace();       
        }
        return myhostname;
    }

}
