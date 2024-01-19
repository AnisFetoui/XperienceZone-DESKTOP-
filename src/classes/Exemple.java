/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;



import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



public class Exemple {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "ACf83307e85c6213d1db311066a02cad20";
    public static final String AUTH_TOKEN = "a3034dc52182d4006d24aba0cecb75f3";

    public static void sendTwilioSMS(String recipientPhoneNumber, String messageText) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message;
        message = Message.creator(
                new com.twilio.type.PhoneNumber(recipientPhoneNumber),
                new com.twilio.type.PhoneNumber("+14843242688"), // Replace with your Twilio phone number
                messageText)
                .create();

        System.out.println(message.getSid());
    }
}