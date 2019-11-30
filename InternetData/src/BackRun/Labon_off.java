package BackRun;

import connection.constants;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sunil
 */
public class Labon_off {

    String day1 = null;
    static String time_from, time_to, str, str1;
    constants connect = new constants();
    static Connection con = null;
    String internet;
    static String net;
    static String System1;
    static String[] mytimefrom = new String[20];
    static String[] mytimefrom1 = new String[20];
    static String[] mytimeto = new String[20];
    static String[] mytimeto1 = new String[20];
    static String[] reqnet = new String[20];
    static String[] mytimefrom2 = new String[20];
    static String[] mytimeto2 = new String[20];
    public static boolean valid;
    public static boolean flag;
    public static boolean flag1;
    public static boolean flag2=true;
    public static String count;
    public static int maincount;
    public static String datetostr;
    public static String uname;
    echoClient echo = new echoClient();
    

    public Labon_off() {
       
    }

    public static boolean isTimeBetweenTwoTime(String argStartTime,
            String argEndTime, String argCurrentTime) throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        //
        if (argStartTime.matches(reg) && argEndTime.matches(reg)
                && argCurrentTime.matches(reg)) {
 
            SimpleDateFormat formate = new SimpleDateFormat("HH:mm:ss");
            Date time_start = formate.parse(argStartTime);
            Time start = new Time(time_start.getTime());
            System.out.println("Time Start:" + argStartTime);

            // Current Time

            Date time_curent = formate.parse(argCurrentTime);
            Time current = new Time(time_curent.getTime());
            System.out.println("Time Current:" + argCurrentTime);

            // End Time
            Date time_stop = formate.parse(argEndTime);
            Time stop = new Time(time_stop.getTime());
            System.out.println("Time End:" + argEndTime);

             if (current.before(stop) && current.after(start)) 
             {
                        try {
                             // flag=true; 
                 final java.lang.Process p = Runtime.getRuntime().exec("ipconfig /renew");
                             //p.waitFor();
                       
                             System.out.println("Internet On...!--WAitttttt...----------------->");
                             System.out.println("RESULT, Time lies b/w");
                             System.out.println("Falg is in on :---->" + flag);
                           valid = true;
                        } catch (IOException ex) {
                            Logger.getLogger(Labon_off.class.getName()).log(Level.SEVERE, null, ex);
                        }
            } 
             
             else {
                     if(flag!=true)
                     {
                     try {
                         final java.lang.Process p = Runtime.getRuntime().exec("ipconfig /release");
                         System.out.println("Internet-------------------OFFFFFFFFFFFF");
                         //   System.out.println("TIME Over..SORYYYYYY");
                         System.out.println("Falg is in off ----->:---->" + flag);
                     } catch (IOException ex) {
                         Logger.getLogger(Labon_off.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     }
                      valid = false;
                 
                   }
             return valid;
        } else {
            throw new IllegalArgumentException(
                    "Not a valid time, expecting HH:MM:SS format");
        }
    }

    public void labmain() 
    {
        uname = System.getProperty("user.name");
        // final String time_from1, time_to1;
        System.out.println(uname);
        Date curdate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        datetostr = format.format(curdate);
        format = new SimpleDateFormat("E");

        datetostr = format.format(curdate);
     //  System.out.println(datetostr);
        Date obj11 = new Date();//get system time
        System1 = new Time(obj11.getTime()).toString();
        if(echo.finalflag==true)
        { 
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connection.constants.url, connection.constants.userName, connection.constants.Password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        
        try {
            String sqr1 = "select PERMISSION_NET,LAB_ID from student_work_detail where ENROLLMENT_NUMBER='" + uname + "'";
            Statement st = con.createStatement();
            ResultSet rs2 = st.executeQuery(sqr1);
            while (rs2.next()) {
                str = rs2.getString("LAB_ID");
                str1 = rs2.getString("PERMISSION_NET");

            }
            System.out.println(str + " " + str1);
            rs2.close();
            str1 = "YES";
            if (str1.equals("YES")) ///check permission
            {
                PreparedStatement ps = con.prepareStatement("select TIME_TO,TIME_FROM from schedule_for_lab where DAY=? && LAB_ID=?");
                ResultSet rs1;
                ps.setString(1, datetostr);
                ps.setString(2, str);
                rs1 = ps.executeQuery();
                System.out.println("Every Time update...");

                int j = 0;
                while (rs1.next()) {

                    time_from = rs1.getString("TIME_FROM");
                    time_to = rs1.getString("TIME_TO");
//            net = rs1.getString("REQUIRED_NET");
                    //  System.out.println("Database REQUIRED_NET:" + internet);
                    mytimefrom[j] = time_from;
                    mytimeto[j] = time_to;
                    j++;
                }

                rs1.close();
            SimpleDateFormat formate = new SimpleDateFormat("HH:mm:ss");
            Date time_curent = formate.parse(System1);
            Time current = new Time(time_curent.getTime());
            System.out.println("Time Current:" + System1);
      int x=0;
      for(int i=0;i<j;i++)
        {
            Date time_curent1 = formate.parse(mytimeto[i]);
            Time current1 = new Time(time_curent1.getTime());
           // System.out.println("Time Current:" + mytimeto[i]);

           if(current.after(current1))
           {
              continue; 
           }
           else
           {
               
               mytimefrom1[x]=mytimefrom[i];
               mytimeto1[x] =mytimeto[i];
              // mytimefrom2[k]=mytimefrom[i];
               //mytimeto2[k] =mytimeto[i];
              
               System.out.println("from:"+mytimefrom1[x]+" to:"+mytimeto1[x]);
               x++;
           }
         }
      
      
      
                Statement stm = con.createStatement();
                String string = "select count(*) from schedule_for_lab";
                ResultSet rs = stm.executeQuery(string);
                while (rs.next()) {
                    count = rs.getString("count(*)");

                }
                rs.close();

                maincount = Integer.parseInt(count);
                System.out.println("------->>>>DATABASE count" + maincount + "-----<<<");

                System1 = new Time(obj11.getTime()).toString();
                for (int k = 0; k < maincount; k++) {
                     System.out.println("from-->:"+mytimefrom1[k]+" to:"+mytimeto1[k]);
               
                    if ((mytimefrom1[k] != null && mytimeto1[k] != null)) 
                    {
                        
                       Labon_off.isTimeBetweenTwoTime(mytimefrom1[k], mytimeto1[k], System1);
                        // obj.getstarttime();
                        //obj.getstoptime();

                    } else {
                        // System.out.println("Internet off.....bcoz no match...");
                        //final java.lang.Process p = Runtime.getRuntime().exec("ipconfig /release");

                    }
                    if (valid == true) {
                        // System.out.println("wait is");
                        flag = true;
                        //  System.out.println("Falg is SETTTT----->:---->"+flag);
                        break;
                    }
                    if (valid == false) {
                        flag = false;
                        // System.out.println("Falg is SETTTT----->:---->"+flag);
                        continue;

                    }
                    System.out.println("LOPP HERE CONTIUES---->" + k);
                }
                //System.out.println("Net OFF--Due to No data match...");

                System.out.println("run after 20 seconds");
            } else {
                System.out.println("PErmission is not givennnnn.............!!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        else
        { int j=0;
                //maincount = Integer.parseInt(count);
                System.out.println("------->>>>DATABASE count" + maincount + "-----<<<");
        
            try
            {
                for (int k = 0; k < maincount; k++) {

                    if ((mytimefrom1[k] != null && mytimeto1[k] != null)) {
                      valid= Labon_off.isTimeBetweenTwoTime(mytimefrom1[k], mytimeto1[k], System1);
                     System.out.println("from-->:"+mytimefrom1[k]+" to:"+mytimeto1[k]);
                     
                        // obj.getstarttime();
                        //obj.getstoptime();

                    } else {
                         System.out.println("Internet off.....bcoz no match...");
                       // final java.lang.Process p = Runtime.getRuntime().exec("ipconfig /release");

                    }
                    if (valid == true) {
                        // System.out.println("wait is");
                        flag = true;
                        //  System.out.println("Falg is SETTTT----->:---->"+flag);
                        break;
                    }
                    if (valid == false) {
                        flag = false;
                        // System.out.println("Falg is SETTTT----->:---->"+flag);
                        continue;

                    }
                    System.out.println("LOPP HERE CONTIUES---->" + k);
                }
            }
                catch(Exception e)
                {
                  e.printStackTrace();  
                }
                }
        }
    }

   // public static void main(String[] args) throws Exception {
//


//        uname = System.getProperty("user.name");
//        // final String time_from1, time_to1;
//        final Labon_off obj = new Labon_off();
//        String daymy = obj.day();
//        Date curdate = new Date();
//
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//         datetostr = format.format(curdate);
//        format = new SimpleDateFormat("E");
//
//        datetostr = format.format(curdate);
//
//        Date obj11 = new Date();//get system time
//        //  System.out.println(time_from1);
//        //  System.out.println(time_to1);
//        System1 = new Time(obj11.getTime()).toString();

        //con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);

//        Timer myTimer = new Timer();
//        TimerTask mytask = new TimerTask() {
//            @Override
//            public void run() {
//                Labon_off obj = new Labon_off();
//                obj.labmain();
//            }
//        };
//        myTimer.schedule(mytask, 15000, 15000);



//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException exc) {
//            exc.printStackTrace();
//    }
        // } catch (Exception ex) {
        //  ex.printStackTrace();
        //}

  //  }

