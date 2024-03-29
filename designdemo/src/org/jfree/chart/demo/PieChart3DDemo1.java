/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * --------------------
 * PieChart3DDemo1.java
 * --------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: PieChart3DDemo1.java,v 1.8 2004/04/26 19:12:00 taqua Exp $
 *
 * Changes
 * -------
 * 19-Jun-2002 : Version 1 (DG);
 * 31-Jul-2002 : Updated with changes to Pie3DPlot class (DG);
 * 11-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 23-Dec-2003 : Renamed Pie3DChartDemo1 --> PieChart3DDemo1 (DG);
 *
 */

package org.jfree.chart.demo;

import connection.constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

/**
 * A simple demonstration application showing how to create a pie chart using data from a
 * {@link DefaultPieDataset}.
 *
 */
public class PieChart3DDemo1 extends ApplicationFrame {
 final PieDataset dataset = createSampleDataset();
        
        // create the chart...
        final JFreeChart chart = createChart(dataset);
        
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        JPanel forpi = new JPanel();
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public PieChart3DDemo1(final String title) throws ClassNotFoundException, SQLException {

        super(title);

        // create a dataset...
       
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
        forpi.add(chartPanel);
    }
     public JPanel getchart()
    {
        return forpi;
    }
    /**
     * Creates a sample dataset for the demo.
     * 
     * @return A sample dataset.
     */
    private PieDataset createSampleDataset() throws ClassNotFoundException, SQLException
    {
        
        Statement st;
      Connection con=null;
      constants connect = new constants();
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);
       double[] values1 = new double[4];
       String qr= "SELECT SUM(DATA_USE) FROM student_work_detail where LAB_ID='101'";
      st = con.createStatement();
      ResultSet rs = st.executeQuery(qr);
      
       while(rs.next())
            {
                values1[0] = rs.getDouble("SUM(DATA_USE)");
            }
      String qr1= "SELECT SUM(DATA_USE) FROM student_work_detail where LAB_ID='102'";
      st = con.createStatement();
      ResultSet rs1 = st.executeQuery(qr1);
      
       while(rs1.next())
            {
                values1[1] = rs1.getDouble("SUM(DATA_USE)");
            }
      
      String qr2= "SELECT SUM(DATA_USE) FROM student_work_detail where LAB_ID='103'";
      st = con.createStatement();
      ResultSet rs2 = st.executeQuery(qr2);
      
       while(rs2.next())
            {
                values1[2] = rs2.getDouble("SUM(DATA_USE)");
            }
       
      String qr3= "SELECT SUM(DATA_USE) FROM student_work_detail where LAB_ID='104'";
      st = con.createStatement();
      ResultSet rs3 = st.executeQuery(qr3);
      
       while(rs3.next())
            {
                values1[3] = rs3.getDouble("SUM(DATA_USE)");
            }
     
        
      
        final DefaultPieDataset data = new DefaultPieDataset();
//        result.setValue("Java", new Double(43.2));
//        result.setValue("Visual Basic", new Double(10.0));
//        result.setValue("C/C++", new Double(17.5));
//        result.setValue("PHP", new Double(32.5));
//        result.setValue("Perl", new Double(1.0));
        data.setValue("LAB_101", new Double(values1[0]));
        data.setValue("LAB_102", new Double(values1[1]));
        data.setValue("LAB_103", new Double(values1[2]));
        data.setValue("LAB_104", new Double(values1[3]));
        return data;
        
    }
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
            "Internet Usage Today In MB",  // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;
        
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) throws ClassNotFoundException, SQLException {

        final PieChart3DDemo1 demo = new PieChart3DDemo1("Internet Usage Today In MB");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
