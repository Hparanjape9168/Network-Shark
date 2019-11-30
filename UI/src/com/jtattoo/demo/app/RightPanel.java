package com.jtattoo.demo.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.ui.chatPane;
public class RightPanel extends JPanel 
{
    private Component parent = null;
    public JTabbedPane tabbedPane = null;
    private InternalFramePanel internalFramePanel = null;
    private JPanel widgetPanel = null;
    private DialogPanel dialogPanel = null;
    private JPanel textFieldPanel = null;
   // private ProgressBarPanel progressBarPanel = null;
    private TablePanel tablePanel = null;
    public admin_login login = null;
    public userlogin userl = null;
//    private illegal_pane iPanel = null;    //for illegal-process form 
//    private schedulefinal sPanel = null;   //update-delete-insert seadual
  //  private screentimer timePanel = null;  //screenshot timer
    private welcome welcome = null;  //screenshot timer
    private chatPane client =null;
   
    
   
    public RightPanel(Component aParent) {
        super(new BorderLayout());
        parent = aParent;
        init();
    }

    private void init() {
     internalFramePanel = new InternalFramePanel();
        try {
            widgetPanel = new WidgetWithRowSorterPanel();
        }
        catch (Throwable t) {
            widgetPanel = new WidgetPanel();
        }
   
        dialogPanel = new DialogPanel(parent);
        try {
            textFieldPanel = new TextFieldSpinnerPanel();
        }
        catch (Throwable t) {
//            textFieldPanel = new TextFieldPanel();
        }
     
     //   progressBarPanel = new ProgressBarPanel();
        tablePanel = new TablePanel();
        //iPanel = new illegal_pane();
       // sPanel =new schedulefinal();
       // timePanel =new screentimer();
        welcome=new welcome();
        login = new admin_login();
        client = new chatPane();  
        try
        {
            userl = new userlogin();
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(RightPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(RightPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JPanel bigButtonPanel = new JPanel(new BorderLayout());
        bigButtonPanel.setName("BigButton");
        bigButtonPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JButton bigButton = new JButton("Extra Large");
        bigButtonPanel.add(bigButton, BorderLayout.CENTER);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
        tabbedPane.add(internalFramePanel.getName(), internalFramePanel);
      //  tabbedPane.add(userl.getName(), userl);
      tabbedPane.add(welcome.getName(), welcome);
       
        tabbedPane.add(widgetPanel.getName(), widgetPanel);
       
        tabbedPane.add(client.getName(), client);
       // tabbedPane.add(dialogPanel.getName(), dialogPanel);
      
        //tabbedPane.add(dialogPanel.getName(), dialogPanel);
       // tabbedPane.add(tablePanel.getName(), tablePanel);
        //tabbedPane.add(iPanel.getName(), iPanel); 
       // tabbedPane.add(sPanel.getName(), sPanel);
      //  tabbedPane.add(timePanel.getName(), timePanel); 
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.setSelectedIndex(1);

        add(tabbedPane, BorderLayout.CENTER);
        ((IDemoApp)parent).setMainTabbedPane(tabbedPane);
    }

    public void updateLookAndFeel() {
       // progressBarPanel.updateLookAndFeel();
        tabbedPane.setBorder(null);
    }

}
