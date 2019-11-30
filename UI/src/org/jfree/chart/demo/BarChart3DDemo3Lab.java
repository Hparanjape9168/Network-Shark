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
 * BarChart3DDemo3.java
 * --------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: BarChart3DDemo3.java,v 1.9 2004/04/26 19:11:53 taqua Exp $
 *
 * Changes
 * -------
 * 24-Nov-2003 : Version 1 (DG);
 *
 */
package org.jfree.chart.demo;

import connection.constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This demonstration shows a 3D bar chart with item labels displayed.
 *
 */
public class BarChart3DDemo3Lab extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public BarChart3DDemo3Lab(final String title) throws ClassNotFoundException, SQLException {

        super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

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
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private CategoryDataset createDataset() throws ClassNotFoundException, SQLException {
        Statement st;
        Connection con = null;
        constants connect = new constants();
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(connect.url, connect.userName, connect.Password);

        String qr = "SELECT * FROM student_work_detail";

        st = con.createStatement();
        ResultSet rs = st.executeQuery(qr);
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int i = 0;
        while (rs.next()) {
            dataset.addValue(rs.getDouble("WEEK_1"), "WEEK_1", rs.getString("LAB_ID"));
            dataset.addValue(rs.getDouble("WEEK_2"), "WEEK_2", rs.getString("LAB_ID"));
            dataset.addValue(rs.getDouble("WEEK_3"), "WEEK_3", rs.getString("LAB_ID"));
            dataset.addValue(rs.getDouble("WEEK_4"), "WEEK_4", rs.getString("LAB_ID"));
            dataset.addValue(rs.getDouble("WEEK_5"), "WEEK_5", rs.getString("LAB_ID"));



        }


//        dataset.addValue(25.0, "Series 1", "Category 1");   
//        dataset.addValue(34.0, "Series 1", "Category 2");   
//        dataset.addValue(19.0, "Series 2", "Category 1");   
//        dataset.addValue(29.0, "Series 2", "Category 2");   
//        dataset.addValue(41.0, "Series 3", "Category 1");   
//        dataset.addValue(33.0, "Series 3", "Category 2");  
//        dataset.addValue(10.0, "Series 4", "Category 3");  
        return dataset;

    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createBarChart3D(
                "Internet Data Uses", // chart title
                "LAB_ID", // domain axis label
                "MB USE", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
                );

        final CategoryPlot plot = chart.getCategoryPlot();
        final CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0));

        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setItemLabelsVisible(true);
        final BarRenderer r = (BarRenderer) renderer;
        r.setMaxBarWidth(0.05);

        return chart;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(final String[] args) throws ClassNotFoundException, SQLException {

        //final BarChart3DDemo3 demo = new BarChart3DDemo3("3D Bar Chart Demo 3");
        final BarChart3DDemo3Lab demo1 = new BarChart3DDemo3Lab("Network Usage For Pcs");
        demo1.pack();
        // demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        RefineryUtilities.centerFrameOnScreen(demo1);

        // demo.setVisible(true);
        demo1.setVisible(true);

    }
}
