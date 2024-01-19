/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 
 */
public class TrayIconDemo {
    
    
    public void notifme (String message)
    {
         if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
             try {
                 td.displayTray(message);
             } catch (AWTException ex) {
                 Logger.getLogger(TrayIconDemo.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
            System.err.println("System tray not supported!");
        }
    }
        public void displayTray (String message) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
       
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("new event "+message+" become available go and check :p", "Event notification", MessageType.INFO);
    }
}
