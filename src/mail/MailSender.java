/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import  util.MYDB;
//import static com.sun.org.glassfish.external.amx.AMXUtil.prop;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {
     public static boolean sendReclamationEmail(String mail,Date date, String typeReclamation, int refObject, String details) {
        // Set up the mail server properties
        Properties properties = new Properties();
        final String moncompteEmail = "naceur.akacha@esprit.tn";
        final String psw = "azqswx12";
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a Session with authentication
        Session ses = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw);
            }
        });

        try {
            // Create a MimeMessage
            Message message = new MimeMessage(ses);
            message.setFrom(new InternetAddress(moncompteEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail)); // Utilisez l'e-mail de l'utilisateur
            message.setSubject("Nouvelle Réclamation");

            // Email content with reclamation information
            String emailContent = "Nouvelle réclamation :\n";
            emailContent += "date de plainte : " + date + "\n";
            emailContent += "Type de réclamation : " + typeReclamation + "\n";
            emailContent += "Référence de l'objet : " + refObject + "\n";
            emailContent += "Détails de la réclamation : " + details;

            message.setText(emailContent);

            // Send the email
            Transport.send(message);
            System.out.println("Message sent successfully");
            return true; // Email sent successfully
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; // Email sending failed
        }
    }
}
