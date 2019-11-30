package com.jtattoo.demo.app;

import com.jtattoo.demo.images.ImageHelper;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.jfree.chart.demo.hell;

public class InternalFramePanel1 extends JPanel {
    
    public static Dimension HGAP2 = new Dimension(2,1);
    public static Dimension VGAP2 = new Dimension(1,2);
    
    public static Dimension HGAP5 = new Dimension(5,1);
    public static Dimension VGAP5 = new Dimension(1,5);
    
    public static Dimension HGAP10 = new Dimension(10,1);
    public static Dimension VGAP10 = new Dimension(1,10);
    
    public static Dimension HGAP15 = new Dimension(15,1);
    public static Dimension VGAP15 = new Dimension(1,15);
    
    public static Dimension HGAP20 = new Dimension(20,1);
    public static Dimension VGAP20 = new Dimension(1,20);
    
    public static Dimension HGAP25 = new Dimension(25,1);
    public static Dimension VGAP25 = new Dimension(1,25);
    
    public static Dimension HGAP30 = new Dimension(30,1);
    public static Dimension VGAP30 = new Dimension(1,30);
    
    private JDesktopPane desktopPane = null;
    
    private int windowCount = 0;
    
    private ImageIcon icon1, icon2, icon3, icon4;
    private ImageIcon smIcon1, smIcon2, smIcon3, smIcon4;
    
    public Integer FIRST_FRAME_LAYER = new Integer(1);
    public Integer DEMO_FRAME_LAYER  = new Integer(2);
    public Integer PALETTE_LAYER     = new Integer(3);
    
    public int FRAME0_X        = 15;
    public int FRAME0_Y        = 280;
    
    public int FRAME0_WIDTH    = 320;
    public int FRAME0_HEIGHT   = 230;
    
    public int FRAME_WIDTH     = 225;
    public int FRAME_HEIGHT    = 150;
    
    public int PALETTE_X      = 375;
    public int PALETTE_Y      = 20;
    
    public int PALETTE_WIDTH  = 300;
    public int PALETTE_HEIGHT = 230;
    
    JCheckBox windowResizable   = null;
    JCheckBox windowClosable    = null;
    JCheckBox windowIconifiable = null;
    JCheckBox windowMaximizable = null;
   //  example obj1=new example();           //for image display
   //  hell obj2=new hell();                //display graph
    JTextField windowTitleField = null;
    JLabel windowTitleLabel = null;
    JInternalFrame jif = new JInternalFrame();
    public InternalFramePanel1() {
        super(new BorderLayout());
        init();
    }
    
    private void init() {
        setName("Database");
        initControls();
    }
    
    private void initControls() {
        icon1 = ImageHelper.loadImage("logo.png");
        icon2 = ImageHelper.loadImage("moon.gif");
        icon3 = ImageHelper.loadImage("sun.gif");
        icon4 = ImageHelper.loadImage("cab.gif");
        
        smIcon1 = ImageHelper.loadImage("fish_small.gif");
        smIcon2 = ImageHelper.loadImage("moon_small.gif");
        smIcon3 = ImageHelper.loadImage("sun_small.gif");
        smIcon4 = ImageHelper.loadImage("cab_small.gif");
        
        desktopPane = new JDesktopPane();
      
        add(desktopPane, BorderLayout.CENTER);
        
        // Create the "frame maker" palette
        createInternalFramePalette();
        
       
    }
    
    /**
     * Create an internal frame and add a scrollable imageicon to it
     */
    public JInternalFrame createInternalFrame(Icon icon, Integer layer, int width, int height) {
        JInternalFrame jif = new JInternalFrame();
        
        if (!windowTitleField.getText().equals("Internal-Frame")) {
            jif.setTitle(windowTitleField.getText());
        }
        else {
            jif = new JInternalFrame("Illegal  DataBase");
        }
             illegal_database obj=new illegal_database();         //database display 
    
        // set properties
        jif.setClosable(windowClosable.isSelected());
        jif.setMaximizable(windowMaximizable.isSelected());
        jif.setIconifiable(windowIconifiable.isSelected());
        jif.setResizable(windowResizable.isSelected());
        jif.setBounds(20 * (windowCount % 10), 20 * (windowCount % 10), width, height);
       // jif.setContentPane(new ImageScroller(icon1, 0, windowCount));
        jif.add(obj.getMainPanel());
        windowCount++;
        
        desktopPane.add(jif, layer);
        
        // Set this internal frame to be selected
        try {
            jif.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e2) {
        }
        
        jif.show();
        
        return jif;
    }
     public JInternalFrame createInternalFrame1(Icon icon, Integer layer, int width, int height) {
        JInternalFrame jif = new JInternalFrame();
        
        if (!windowTitleField.getText().equals("Internal-Frame")) {
            jif.setTitle(windowTitleField.getText());
        }
        else {
            jif = new JInternalFrame("Process DataBase");
        }
 process_database process=new process_database();
        
        // set properties
        jif.setClosable(windowClosable.isSelected());
        jif.setMaximizable(windowMaximizable.isSelected());
        jif.setIconifiable(windowIconifiable.isSelected());
        jif.setResizable(windowResizable.isSelected());
        jif.setBounds(20 * (windowCount % 10), 20 * (windowCount % 10), width, height);
       // jif.setContentPane(new ImageScroller(icon1, 0, windowCount));
        jif.add(process.getMainPanel1());
        windowCount++;
        
        desktopPane.add(jif, layer);
        
        // Set this internal frame to be selected
        try {
            jif.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e2) {
        }
        
        jif.show();
        
        return jif;
    }
      public JInternalFrame createInternalFrame3(Icon icon, Integer layer, int width, int height) {
        JInternalFrame jif = new JInternalFrame();
        
        if (!windowTitleField.getText().equals("Internal-Frame")) {
            jif.setTitle(windowTitleField.getText());
        }
        else {
            jif = new JInternalFrame("Schedule DataBase");
        }
     schedual_database schedual =new schedual_database();
        
        // set properties
        jif.setClosable(windowClosable.isSelected());
        jif.setMaximizable(windowMaximizable.isSelected());
        jif.setIconifiable(windowIconifiable.isSelected());
        jif.setResizable(windowResizable.isSelected());
        jif.setBounds(20 * (windowCount % 10), 20 * (windowCount % 10), width, height);
       // jif.setContentPane(new ImageScroller(icon1, 0, windowCount));
        jif.add(schedual.getMainPanel3());
        windowCount++;
        
        desktopPane.add(jif, layer);
        
        // Set this internal frame to be selected
        try {
            jif.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e2) {
        }
        
        jif.show();
        
        return jif;
    }
     public JInternalFrame createInternalFrame4(Icon icon, Integer layer, int width, int height) {
        JInternalFrame jif = new JInternalFrame();
        
        if (!windowTitleField.getText().equals("Internal-Frame")) {
            jif.setTitle(windowTitleField.getText());
        }
        else {
            jif = new JInternalFrame("Login DataBase");
        }
     login_database login =new login_database();
        
        // set properties
        jif.setClosable(windowClosable.isSelected());
        jif.setMaximizable(windowMaximizable.isSelected());
        jif.setIconifiable(windowIconifiable.isSelected());
        jif.setResizable(windowResizable.isSelected());
        jif.setBounds(20 * (windowCount % 10), 20 * (windowCount % 10), width, height);
       // jif.setContentPane(new ImageScroller(icon1, 0, windowCount));
        jif.add(login.getMainPanel4());
        windowCount++;
        
        desktopPane.add(jif, layer);
        
        // Set this internal frame to be selected
        try {
            jif.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e2) {
        }
        
        jif.show();
        
        return jif;
    }
      public JInternalFrame createInternalFrame5(Icon icon, Integer layer, int width, int height) {
        JInternalFrame jif = new JInternalFrame();
        
        if (!windowTitleField.getText().equals("Internal-Frame")) {
            jif.setTitle(windowTitleField.getText());
        }
        else {
            jif = new JInternalFrame("Student DataBase");
        }
     student_database student =new student_database();
        
        // set properties
        jif.setClosable(windowClosable.isSelected());
        jif.setMaximizable(windowMaximizable.isSelected());
        jif.setIconifiable(windowIconifiable.isSelected());
        jif.setResizable(windowResizable.isSelected());
        jif.setBounds(20 * (windowCount % 10), 20 * (windowCount % 10), width, height);
       // jif.setContentPane(new ImageScroller(icon1, 0, windowCount));
        jif.add(student.getMainPanel15());
        windowCount++;
        
        desktopPane.add(jif, layer);
        
        // Set this internal frame to be selected
        try {
            jif.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e2) {
        }
        
        jif.show();
        
        return jif;
    }
    public JInternalFrame createInternalFrame6(Icon icon, Integer layer, int width, int height) {
        JInternalFrame jif = new JInternalFrame();
        
        if (!windowTitleField.getText().equals("Internal-Frame")) {
            jif.setTitle(windowTitleField.getText());
        }
        else {
            jif = new JInternalFrame("Faculty DataBase");
        }
     faculty_database faculty =new faculty_database();
        
        // set properties
        jif.setClosable(windowClosable.isSelected());
        jif.setMaximizable(windowMaximizable.isSelected());
        jif.setIconifiable(windowIconifiable.isSelected());
        jif.setResizable(windowResizable.isSelected());
        jif.setBounds(20 * (windowCount % 10), 20 * (windowCount % 10), width, height);
       // jif.setContentPane(new ImageScroller(icon1, 0, windowCount));
        jif.add(faculty.getMainPanel6());
        windowCount++;
        
        desktopPane.add(jif, layer);
        
        // Set this internal frame to be selected
        try {
            jif.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e2) {
        }
        
        jif.show();
        
        return jif;
    }
    public JInternalFrame createInternalFrame7(Icon icon, Integer layer, int width, int height) {
        JInternalFrame jif = new JInternalFrame();
        
        if (!windowTitleField.getText().equals("Internal-Frame")) {
            jif.setTitle(windowTitleField.getText());
        }
        else {
            jif = new JInternalFrame("Pendrive Access Detail");
        }
     Pendrive_Database pendrive = new Pendrive_Database();
        // set properties
        jif.setClosable(windowClosable.isSelected());
        jif.setMaximizable(windowMaximizable.isSelected());
        jif.setIconifiable(windowIconifiable.isSelected());
        jif.setResizable(windowResizable.isSelected());
        jif.setBounds(20 * (windowCount % 10), 20 * (windowCount % 10), width, height);
       // jif.setContentPane(new ImageScroller(icon1, 0, windowCount));
        jif.add(pendrive.getMainPanel1());
        windowCount++;
        
        desktopPane.add(jif, layer);
        
        // Set this internal frame to be selected
        try {
            jif.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e2) {
        }
        
        jif.show();
        
        return jif;
    }
    
    public JInternalFrame createInternalFramePalette() {
        JInternalFrame palette = new JInternalFrame("Palette");
        palette.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        palette.getContentPane().setLayout(new BorderLayout());
        palette.setBounds(PALETTE_X, PALETTE_Y, PALETTE_WIDTH, PALETTE_HEIGHT);
        palette.setResizable(true);
        palette.setIconifiable(true);
        desktopPane.add(palette, PALETTE_LAYER);
        
        // *************************************
        // * Create create frame maker buttons *
        // *************************************
        JButton b1 = new JButton("Illegal Activity");
        JButton b2 = new JButton("Process");
        JButton b3 = new JButton("Schedual_of_lab");
        JButton b4 = new JButton("Login");
        JButton b5 = new JButton("Employee_work_detail");
        JButton b6 = new JButton("Faculty_work_detail");
        JButton b7 = new JButton("Pendrive Detail");
       
        // JButton b4 = new JButton(smIcon4);
        
        // add frame maker actions
      //  b1.addActionListener(new ShowFrameAction(this, icon1));
       b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               JInternalFrame frame1 = createInternalFrame(icon1, FIRST_FRAME_LAYER, 1, 1);
               frame1.setBounds(FRAME0_X, FRAME0_Y, FRAME0_WIDTH, FRAME0_HEIGHT);
               frame1.setFrameIcon(icon1);
            }
        });
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              JInternalFrame frame1 = createInternalFrame1(icon1, FIRST_FRAME_LAYER, 1, 1);
               frame1.setBounds(FRAME0_X, FRAME0_Y, FRAME0_WIDTH, FRAME0_HEIGHT);
               frame1.setFrameIcon(icon1);
            }
        });
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              JInternalFrame frame1 = createInternalFrame3(icon1, FIRST_FRAME_LAYER, 1, 1);
               frame1.setBounds(FRAME0_X, FRAME0_Y, FRAME0_WIDTH, FRAME0_HEIGHT);
               frame1.setFrameIcon(icon1);
            }
        });
       b4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame frame1 = createInternalFrame4(icon1, FIRST_FRAME_LAYER, 1, 1);
               frame1.setBounds(FRAME0_X, FRAME0_Y, FRAME0_WIDTH, FRAME0_HEIGHT);
               frame1.setFrameIcon(icon1);
            }
        }); 
       b5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame frame1 = createInternalFrame5(icon1, FIRST_FRAME_LAYER, 1, 1);
               frame1.setBounds(FRAME0_X, FRAME0_Y, FRAME0_WIDTH, FRAME0_HEIGHT);
               frame1.setFrameIcon(icon1);
            }
        }); 
       b6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               JInternalFrame frame1 = createInternalFrame6(icon1, FIRST_FRAME_LAYER, 1, 1);
               frame1.setBounds(FRAME0_X, FRAME0_Y, FRAME0_WIDTH, FRAME0_HEIGHT);
               frame1.setFrameIcon(icon1);
            }
        });
       b7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               JInternalFrame frame1 = createInternalFrame7(icon1, FIRST_FRAME_LAYER, 1, 1);
               frame1.setBounds(FRAME0_X, FRAME0_Y, FRAME0_WIDTH, FRAME0_HEIGHT);
               frame1.setFrameIcon(icon1);
            }
        });
       
       
       // b3.addActionListener(new ShowFrameAction(this, icon3));
       // b4.addActionListener(new ShowFrameAction(this, icon4));
        
        // add frame maker buttons to panel
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        JPanel buttons1 = new JPanel();
        buttons1.setLayout(new BoxLayout(buttons1, BoxLayout.X_AXIS));
        
        JPanel buttons2 = new JPanel();
        buttons2.setLayout(new BoxLayout(buttons2, BoxLayout.X_AXIS));
        
        JPanel buttons3 = new JPanel();
        buttons3.setLayout(new BoxLayout(buttons3, BoxLayout.X_AXIS));
       
        JPanel buttons4 = new JPanel();
        buttons4.setLayout(new BoxLayout(buttons4, BoxLayout.X_AXIS));
       
        buttons1.add(b1);
        buttons1.add(Box.createRigidArea(HGAP15));
        buttons1.add(b2);
        
        buttons2.add(b3);
        buttons2.add(Box.createRigidArea(HGAP15));
        buttons2.add(b4);
        
        buttons3.add(b5);
        buttons3.add(Box.createRigidArea(HGAP15));
        buttons3.add(b6);
        buttons4.add(Box.createRigidArea(HGAP15));
        buttons4.add(b7);
        p.add(Box.createRigidArea(VGAP10));
        p.add(buttons1);
        p.add(Box.createRigidArea(VGAP15));
        p.add(buttons2);
        p.add(Box.createRigidArea(VGAP10));
        
       // p.add(Box.createRigidArea(VGAP15));
        p.add(buttons3);
        p.add(Box.createRigidArea(VGAP10));
        p.add(buttons4);
        p.add(Box.createRigidArea(VGAP10));
        
        
        palette.getContentPane().add(p, BorderLayout.NORTH);
        
        // ************************************
        // * Create frame property checkboxes *
        // ************************************
        p = new JPanel() {
            Insets insets = new Insets(10,15,10,5);
            public Insets getInsets() {
                return insets;
            }
        };
        p.setLayout(new GridLayout(2,2));
        
        windowResizable   = new JCheckBox("Resizeable", true);
        windowClosable    = new JCheckBox("Closeable", true);
        windowIconifiable = new JCheckBox("Iconifiable", true);
        windowMaximizable = new JCheckBox("Maximizable", true);
        
        p.add(windowResizable);
        p.add(windowClosable);
        p.add(windowIconifiable);
        p.add(windowMaximizable);
        
        palette.getContentPane().add(p, BorderLayout.CENTER);
        
        
        // ************************************
        // *   Create Frame title textfield   *
        // ************************************
        p = new JPanel() {
            Insets insets = new Insets(0,0,10,0);
            public Insets getInsets() {
                return insets;
            }
        };
        
        windowTitleField = new JTextField("Database");
        windowTitleLabel = new JLabel("Title");
        windowTitleField.setVisible(false);
        windowTitleLabel.setVisible(false);
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(Box.createRigidArea(HGAP5));
        p.add(windowTitleLabel, BorderLayout.WEST);
        p.add(Box.createRigidArea(HGAP5));
        p.add(windowTitleField, BorderLayout.CENTER);
        p.add(Box.createRigidArea(HGAP5));
        
        palette.getContentPane().add(p, BorderLayout.SOUTH);
        
        palette.show();
        
        return palette;
    }
    
    public int getFrameWidth() {
        return FRAME_WIDTH;
    }
    
    public int getFrameHeight() {
        return FRAME_HEIGHT;
    }
    
    public Integer getDemoFrameLayer() {
        return DEMO_FRAME_LAYER;
    }
    
    class ShowFrameAction extends AbstractAction {
        InternalFramePanel ifPanel;
        Icon icon;
        
        public ShowFrameAction(InternalFramePanel ifPanel, Icon icon) {
            this.ifPanel = ifPanel;
            this.icon = icon;
           // ifPanel.add(obj1.getMainPanel());
        }

       
        public void actionPerformed(ActionEvent e) {
           // ifPanel.createInternalFrame(icon, getDemoFrameLayer(), getFrameWidth(), getFrameHeight());
        }
    }
    
    class ImageScroller extends JScrollPane {
        public ImageScroller(Icon icon, int layer, int count) {
            super();
            JPanel p = new JPanel();
            p.setBackground(Color.white);
            p.setLayout(new BorderLayout() );
            p.add(new JLabel(icon), BorderLayout.CENTER);
            getViewport().add(p);
        }
        
        public Dimension getMinimumSize() {
            return new Dimension(25, 25);
        }
        
    }
    
}
