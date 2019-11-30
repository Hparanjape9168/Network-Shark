package com.jtattoo.demo.app;
import com.jtattoo.demo.app.NewClass;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.*;
import Set_ip_adress.Adminipaddress;
import Labschedule.schedulefinal;
import com.serversocket.ServerPanel;
import com.socket.chatserver;
public class RightPanel extends JPanel {
    private Component parent = null;
    private JTabbedPane tabbedPane = null;
    private InternalFramePanel1 internalFramePanel = null;
    private InternalFramePanel internalFramePanel1 = null;
    private ServerPanel chatserver =null;
    private internalFramePanel2 internalFramePanel2 = null;
    private  Adminipaddress adip=null;
    private  setuserlab labUser=null;
    
    private illegal_pane iPanel = null;    //for illegal-process form 
    private schedulefinal sPanel = null;   //update-delete-insert seadual
    private screentimer timePanel = null;
    private img_display imgdis=null;//screenshot timer
    private welcome welcome = null;  //screenshot timer
    private report_generate_pane gen = null;        //report generate page timer
    private pendriveaccess permission=null;
    private chatserver chatclient =null;
    public RightPanel(Component aParent) {
        super(new BorderLayout());
        parent = aParent;
        init();
    }

    private void init() {
      internalFramePanel = new InternalFramePanel1();
      internalFramePanel1 = new InternalFramePanel();
      internalFramePanel2=new internalFramePanel2();;
        try {
          //  widgetPanel = new WidgetWithRowSorterPanel();
        }
        catch (Throwable t) {
          //  widgetPanel = new WidgetPanel();
        }
   
//        dialogPanel = new DialogPanel(parent);
        try {
          //  textFieldPanel = new TextFieldSpinnerPanel();
        }
        catch (Throwable t) {
        //    textFieldPanel = new TextFieldPanel();
        }
     
//      progressBarPanel = new ProgressBarPanel();
        //tablePanel = new TablePanel();
        iPanel = new illegal_pane();
        sPanel =new schedulefinal();
        timePanel =new screentimer();
        welcome=new welcome();
      //  adip=new Adminipaddress();
        chatserver =new ServerPanel();
      //image = new test_images();
        gen = new report_generate_pane();
        chatclient= new chatserver();
        imgdis=new img_display();
        labUser= new setuserlab();
        permission= new pendriveaccess();
        JPanel bigButtonPanel = new JPanel(new BorderLayout());
        bigButtonPanel.setName("BigButton");
        bigButtonPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JButton bigButton = new JButton("Extra Large");
        bigButtonPanel.add(bigButton, BorderLayout.CENTER);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
        tabbedPane.add(internalFramePanel.getName(), internalFramePanel);
        tabbedPane.add(welcome.getName(), welcome);
        tabbedPane.add(gen.getName(), gen);

        tabbedPane.add(internalFramePanel1.getName(), internalFramePanel1);
        tabbedPane.add(iPanel.getName(), iPanel); 
        tabbedPane.add(sPanel.getName(), sPanel);
        tabbedPane.add(timePanel.getName(), timePanel);
        tabbedPane.add(permission.getName(), permission);
        tabbedPane.add(labUser.getName(), labUser);
       // tabbedPane.add(chatserver.getName(), chatserver);
        tabbedPane.add(chatclient.getName(), chatclient);
      
        tabbedPane.add(imgdis.getName(), imgdis);
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.setSelectedIndex(1);

        add(tabbedPane, BorderLayout.CENTER);
        ((IDemoApp)parent).setMainTabbedPane(tabbedPane);
    }

    public void updateLookAndFeel() {
//        progressBarPanel.updateLookAndFeel();
        tabbedPane.setBorder(null);
    }

}
