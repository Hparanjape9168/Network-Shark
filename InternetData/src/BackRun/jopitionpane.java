/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackRun;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class jopitionpane 
{
    Runtime runtime = Runtime.getRuntime();
    Process proc=null;
    Illegal_access_Main obj = new Illegal_access_Main();
    public jopitionpane() {
       
        
        final JFrame frame = new JFrame();
        
        final JPanel jp = new JPanel();
        final JDialog jd = new JDialog(frame, true);
        
        final JDialog dialog = new JDialog(jd, "Click a button",true);
        jd.setTitle("Warning");
        JButton button =new JButton("OK");
        JButton button1 = new JButton("CANCEL");
        
        String command = button.getText();
        final JComponent[] inputs = new JComponent[] {
	
		button,
                button1,
	        };
    //default button title       
         
   int n= JOptionPane.showConfirmDialog(jd, "YOU ARE TRY Access Unauthorized Application\n"+"Click YES For close Application\n"+"Click NO For Logoff\n","Warning", JOptionPane.YES_NO_OPTION);
   
   
     if(n==JOptionPane.YES_OPTION)
     {
         System.out.println("yse click ");
         
      
      }
     else if(n==JOptionPane.NO_OPTION)
     {
         System.out.println("cancel click");
          new CountDownTimerDialog(jd, true, 10);
     }
    else
     {
         final JOptionPane optionPane = new JOptionPane(
                                    "The only way to close this dialog is by\n"
                                    + "pressing one of the following buttons.\n"
                                    + "Do you understand..?",
                                    JOptionPane.QUESTION_MESSAGE,
                                    JOptionPane.YES_NO_OPTION);
                    dialog.setContentPane(optionPane);
                    dialog.setDefaultCloseOperation(
                        JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we)
                        {
                            //setLabel("Thwarted user attempt to close window.");
                        }
                    });
                    optionPane.addPropertyChangeListener(
                        new PropertyChangeListener() {
                            public void propertyChange(PropertyChangeEvent e) {
                                String prop = e.getPropertyName();

                                if (dialog.isVisible()
                                 && (e.getSource() == optionPane)
                                 && (JOptionPane.VALUE_PROPERTY.equals(prop))) {
                                    //If you were going to check something
                                    //before closing the window, you'd do
                                    //it here.
                                    dialog.setVisible(false);
                                }
                            }
                        });
                    dialog.pack();
                    dialog.setLocationRelativeTo(frame);
                    for(int i=obj.counter; 0>=i ; i--)
                    {
                        if(i!=0)
                        {
                            dialog.setVisible(true);
                        }
                    }
                    
                    
                    int value = ((Integer)optionPane.getValue()).intValue();
                    if (value == JOptionPane.YES_OPTION) 
                    {
                        // int n= JOptionPane.showConfirmDialog(jd, "you Access Unathorized APllication","Warning", JOptionPane.YES_NO_OPTION);
   
                        //new jopitionpane();
                        System.out.println("u press yes button");
                    } 
                    if(value == JOptionPane.NO_OPTION)
                    {
                         new CountDownTimerDialog(jd, true, 10);
                         System.out.println("no option selected");
                    }
         
                    System.out.println("extra option");
            }
     }

    public class CountDownTimerDialog extends JDialog {
        public int count;

        public CountDownTimerDialog(JDialog parent, boolean modal, int seconds) {
            super(parent, modal);
            count = seconds;
            final JLabel countLabel = new JLabel(String.valueOf(seconds), JLabel.CENTER);
            countLabel.setFont(new Font("impact", Font.PLAIN, 36));
            JLabel message = new JLabel("System Will Be ShutDown After CountDown Complte");
            message.setFont(new Font("verdana", Font.BOLD, 20));

            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
            wrapper.add(countLabel);
            wrapper.add(message, BorderLayout.SOUTH);
            add(wrapper);

            Timer timer = new Timer(1000, new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    if (count == 0)
                    {
                        dispose();
                        System.out.println("system is shutdown");
                        try {
                            proc = runtime.exec("shutdown.exe -l");
                        } catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                    } else {
                        countLabel.setText(String.valueOf(count));
                        count--;
                    }
                }
            });
            timer.setInitialDelay(0);
            timer.start();

            pack();
            setLocationRelativeTo(parent);
            setVisible(true);
            setSize(400, 250);
        }
        
       }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new jopitionpane();
            }
        });
    }
}
