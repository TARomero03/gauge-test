package com.companyname.automation.commontools;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * @see http://stackoverflow.com/a/12451673/230513
 */

public class AutoDismiss implements ActionListener, PropertyChangeListener {

    private  final int TIME_OUT = 1;
    private int count = TIME_OUT;
    private  Timer timer = new Timer(1000, this);
    private JDialog dialog = new JDialog();
    private  JOptionPane optPane = new JOptionPane();
    
    private String sMsg="";
    JFrame frame = null;
    
    

    public void createGUI(String sTitle, String sMessage) {
        frame = new JFrame(sTitle);
        sMsg = sMessage;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        timer.setCoalesce(false);
        
        optPane.setMessage(sMessage);
        optPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        optPane.setOptionType(JOptionPane.DEFAULT_OPTION);
  //      optPane.addPropertyChangeListener(this);
        dialog.add(optPane);
        dialog.pack();
        frame.add(new JLabel(frame.getTitle(), JLabel.CENTER));
        frame.setVisible(true);
        
        
        dialog.setTitle(sTitle);
        dialog.setLocationRelativeTo(frame);
        dialog.setSize(new Dimension(640,480));
        dialog.setVisible(true);
       
  //     timer.start();
    }

    public void setMessage(String sMessage)
    {
    	optPane.setMessage(sMessage);
    	
    }
    
    public String GetInput(String sMessage,String sInitial)
    {
    	return optPane.showInputDialog(sMessage, sInitial);
    }
    
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if (JOptionPane.VALUE_PROPERTY.equals(prop)) {
            thatsAllFolks();
        }
    }

    public void actionPerformed(ActionEvent e) {
        optPane.setMessage(sMsg);
   //       timer.restart();
    }

 
    public void thatsAllFolks() {
    	
   //     dialog.setVisible(false);
    	System.out.println("Close dialog");
        dialog.dispatchEvent(new WindowEvent(
            dialog, WindowEvent.WINDOW_CLOSING));
    	System.out.println("Closed dialog");
 /*   	System.out.println("Close frame");
      frame.dispatchEvent(new WindowEvent(
            frame, WindowEvent.WINDOW_CLOSING));
  	System.out.println("Closed frame");
*/  	
  	System.out.println("dispose dialog");
    dialog.dispose();
  	System.out.println("dispose frame");
       frame.dispose();
     	System.out.println("disposed all");
    

    }
}