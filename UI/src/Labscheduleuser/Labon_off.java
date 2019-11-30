package Labscheduleuser;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Sunil
 */
public class Labon_off {

    String day1 = null;
    static String time_from, time_to,str,str1;
    constants connect = new constants();
    static Connection con = null;
    String internet;
    static String net;
    static String System1;
    static String[] mytimefrom = new String[20];
    static String[] mytimeto = new String[20];
    static String[] reqnet = new String[20];
    static boolean valid;
    static boolean flag;
    public static String count;
    public static int maincount;
    public static String datetostr;
    public static String uname ;

    public Labon_off() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String day() throws MalformedURLException, IOException {

        Date date = new Date();
        String day12 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        System.out.println("System Time:" + sdf.format(date));
        char[] str2 = new char[7];
        String str1 = date.toString();
        try {

            str1.getChars(0, 3, str2, 0);
            for (int i = 0; i <= 3; i++) {
                day12 = day12 + str2[i];
            }
            // System.out.println(str2);
            System.out.println("day is:" + day12);
            // System.out.println(str1);

        } catch (Exception ex) {
            System.out.println("Raised exception...");
        }
        return day12;
    }

    public static boolean isTimeBetweenTwoTime(String argStartTime,
            String argEndTime, String argCurrentTime) throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        //
        if (argStartTime.matches(reg) && argEndTime.matches(reg)
                && argCurrentTime.matches(reg)) {
            valid = false;
            // Start Time

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

//            if (current.compareTo(stop) < 0) {
//                //currentCalendar.add(Calendar.DATE, 1);
//                //currentTime = currentCalendar.getTime();
//                //System.out.println("current");
//            }
//
//            if (start.compareTo(stop) < 0) {
//                // startCalendar.add(Calendar.DATE, 1);
//                //  startTime = startCalendar.getTime();
//                //  System.out.println("hello");
//            }

            if (current.before(start)) 
            {

             try {
                    if (flag != true)
                    {
                       //final java.lang.Process p = Runtime.getRuntime().exec("ipconfig /release");
                        System.out.println("Internet Off------------------------------------");
                        //System.out.println(" Time is Lesser ");
                    }
                } 
             catch (Exception ex) 
             {
             }

                valid = false;
            } 
            else {

//                if (current.after(stop)) {
//                      endCalendar.add(Calendar.DATE, 1);
//                      endTime = endCalendar.getTime();
//                      System.out.println("world!!");
//                }
                
//                System.out.println("Comparing , Start Time /n " + start);
//                System.out.println("Comparing , End Time /n " + stop);
//                System.out.println("Comparing , Current Time /n " + current);

                if (current.before(stop)) 
                {
                    valid = true;
                    try {

                        // final java.lang.Process p = Runtime.getRuntime().exec("ipconfig /renew");
                        //p.waitFor();
                        System.out.println("Internet On...!--WAitttttt...----------------->");
                        System.out.println("RESULT, Time lies b/w");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    //valid = true;
                }
                else 
                {
                    valid = false;
                    try {
                        if (flag != true) 
                        {
                             // final java.lang.Process p = Runtime.getRuntime().exec("ipconfig /release");
                            System.out.println("Internet Off------------------------------------");
                         //   System.out.println("TIME Over..SORYYYYYY");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
            return valid;

        } else {
            throw new IllegalArgumentException(
                    "Not a valid time, expecting HH:MM:SS format");
        }

    }

    public static void main(String[] args) throws Exception {

        uname = System.getProperty("user.name");
        // final String time_from1, time_to1;
        final Labon_off obj = new Labon_off();
        String daymy = obj.day();
        Date curdate = new Date();


        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
         datetostr = format.format(curdate);
        format = new SimpleDateFormat("E");

        datetostr = format.format(curdate);

        Date obj11 = new Date();//get system time
        //  System.out.println(time_from1);
        //  System.out.println(time_to1);
        System1 = new Time(obj11.getTime()).toString();

        //con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
    
        Timer myTimer = new Timer();
        TimerTask mytask = new TimerTask() {
            @Override
            public void run() 
            {
                try {
                    Date obj11 = new Date();
                    //  System.out.println(time_from1);
                    //  System.out.println(time_to1);
        String sqr1 = "select PERMISSION_NET,LAB_ID from student_work_detail where ENROLLMENT_NUMBER='"+uname+"'";
        Statement st  = con.createStatement();
        
        ResultSet rs2 = st.executeQuery(sqr1);
       while(rs2.next())
       {
          str = rs2.getString("LAB_ID");
          str1 =rs2.getString("PERMISSION_NET");
       }
       rs2.close();
        if(str1.equals("YES")) ///check permission
        { 
        PreparedStatement ps = con.prepareStatement("select TIME_TO,TIME_FROM from schedule_for_lab where DAY=?");
        ResultSet rs1;
        ps.setString(1, datetostr);
        rs1 = ps.executeQuery();
        System.out.println("Every Time update...");


        int j = 0;
        while (rs1.next()) 
        {

            time_from = rs1.getString("TIME_FROM");
            time_to = rs1.getString("TIME_TO");
//            net = rs1.getString("REQUIRED_NET");

            //  System.out.println("Database REQUIRED_NET:" + internet);
            mytimefrom[j] = time_from;
            mytimeto[j] = time_to;
            reqnet[j] = net;
            // System.out.println("Database Time Start:" + mytimefrom[j]);
            //System.out.println("Databse time stop" + mytimeto[j]);
            // System.out.println("Required NET iS--"+reqnet[j]);
            j++;

        }

        rs1.close();

                    Statement stm = con.createStatement();
                    String string = "select count(*) from schedule_for_lab";
                    ResultSet rs = stm.executeQuery(string);
                    while (rs.next()) {
                        count = rs.getString("count(*)");

                    }
                    rs.close();

                    maincount = Integer.parseInt(count);
                    System.out.println("------->>>>DATA bASE count" + maincount + "-----<<<");

                    System1 = new Time(obj11.getTime()).toString();

                    for (int k = 0; k < maincount; k++) 
                    {

                        if ((mytimefrom[k] != null && mytimeto[k] != null)) 
                        {
                            Labon_off.isTimeBetweenTwoTime(mytimefrom[k], mytimeto[k], System1);
                            // obj.getstarttime();
                            //obj.getstoptime();

                        }
                        else
                        {
                            System.out.println("Internet off.....bcoz no match...");
                        }
                        if (valid == true) 
                        {
                            // System.out.println("wait is");
                            flag = true;
                            break;
                        }
                        if (valid == false)
                        {
                            flag = false;
                            continue;

                        }
                        System.out.println("LOPP HERE CONTIUES");
                    }
                    //System.out.println("Net OFF--Due to No data match...");
                    //final java.lang.Process p = Runtime.getRuntime().exec("ipconfig /release");
                        
                    System.out.println("run after 20 seconds");
                }
        else{
            System.out.println("PErmission is not givennnnn.............!!");
        }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        myTimer.schedule(mytask, 20000, 20000);



//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException exc) {
//            exc.printStackTrace();
//    }
        // } catch (Exception ex) {
        //  ex.printStackTrace();
        //}

    }
}
