
package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class hell {
    
    JFrame frm =new JFrame();
    JPanel parent =new JPanel();
    JPanel child =new JPanel();   
    public hell()
    {
       parent.setBorder(BorderFactory.createTitledBorder("parent"));
       child.setBorder(BorderFactory.createTitledBorder("parent"));
         JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 270));
      // child.add(new JButton("hello"));
       child.add(chartPanel);
     //  parent.add(child);
      // frm.add(parent);
     //  frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // frm.setVisible(true);
    }
    private static CategoryDataset createDataset() {
// row keys...
String series1 = "First";
String series2 = "Second";
// column keys...
String category1 = "Category 1";
String category2 = "Category 2";
String category3 = "Category 3";
// create the dataset...
DefaultCategoryDataset dataset = new DefaultCategoryDataset();
dataset.addValue(1.0, series1, category1);
dataset.addValue(4.0, series1, category2);
dataset.addValue(3.0, series1, category3);
dataset.addValue(5.0, series2, category1);
dataset.addValue(7.0, series2, category2);
dataset.addValue(6.0, series2, category3);
return dataset;
}
      public JPanel getpane()
   {
      return child; 
   }
private static JFreeChart createChart(CategoryDataset dataset) {
// create the chart...
JFreeChart chart = ChartFactory.createBarChart(
"Bar Chart Demo 1", // chart title
"Category", // domain axis label
" Value", // range axis label
dataset, // data
PlotOrientation.VERTICAL, // orientation
true, // include legend
true, // tooltips?
false // URLs?
);
// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
// set the background color for the chart...
chart.setBackgroundPaint(Color.white);
// get a reference to the plot for further customisation...
CategoryPlot plot = (CategoryPlot) chart.getPlot();
plot.setBackgroundPaint(Color.lightGray);
plot.setDomainGridlinePaint(Color.white);
plot.setDomainGridlinesVisible(true);
plot.setRangeGridlinePaint(Color.white);
// set the range axis to display integers only...
final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
// disable bar outlines...
BarRenderer renderer = (BarRenderer) plot.getRenderer();
renderer.setDrawBarOutline(false);
// set up gradient paints for series...
GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
0.0f, 0.0f, new Color(0, 0, 64));
GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
0.0f, 0.0f, new Color(0, 64, 0));
renderer.setSeriesPaint(0, gp0);
renderer.setSeriesPaint(1, gp1);
CategoryAxis domainAxis = plot.getDomainAxis();
domainAxis.setCategoryLabelPositions(
CategoryLabelPositions.createUpRotationLabelPositions(
Math.PI / 6.0));
// OPTIONAL CUSTOMISATION COMPLETED.
return chart;
}
 
//    public static void main(String[] args) {
//        new hell();
//    }
}
