/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtattoo.demo.app;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class example
{
    private JPanel mainPanel = new JPanel();
 
    public example()
    {
      
JPanel panel = new JPanel(); 
panel.setSize(1000,1000);
panel.setBackground(Color.CYAN); 
ImageIcon icon = new ImageIcon("C:\\Users\\Sunil\\Downloads\\divilus.jpg"); 
//ImageIcon icon1 = new ImageIcon("C:\\Users\\Sunil\\Downloads\\s-1.jpg");
ImageIcon icon2 = new ImageIcon("C:\\Users\\Sunil\\Downloads\\s-3.jpg");
JButton btn =new JButton("submit");
JLabel label = new JLabel(); 
JLabel label1 = new JLabel(); 
JLabel label2 = new JLabel();
label.setIcon(icon); 
//label1.setIcon(icon1); 
label2.setIcon(icon2); 
panel.add(btn);
panel.add(label);
panel.add(label1);
panel.add(label2);
//setVisible(true); 
        
        JPanel sidePanel = new JPanel();
        JLabel sideLabel = new JLabel("Side Label");
        sidePanel.add(sideLabel);
        
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new BorderLayout());
        //viewPanel.add(table, BorderLayout.CENTER);
        viewPanel.add(sidePanel, BorderLayout.EAST);
        
        
        JScrollPane scroll = new JScrollPane(panel);
       // scroll.setColumnHeaderView(table.getTableHeader());
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scroll, BorderLayout.CENTER);
        
    }
 
    public JPanel getMainPanel()
    {
        return mainPanel;
    }
 
    private static void createAndShowUI()
    {
        JFrame frame = new JFrame("TableAndStuff");
        frame.getContentPane().add(new example().getMainPanel());
       // frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowUI();
            }
        });
    }
}

