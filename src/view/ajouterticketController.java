/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Categories;
import classes.Ticket;
import classes.User;
import service.TicketService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceUser;
//import view.user.ConnexionUserController;

/**
 * FXML Controller class
 *
 * @author the_old_is_back
 */
public class ajouterticketController implements Initializable {

    @FXML
    private AnchorPane anchorme;
    @FXML
    private TextField num_ticket;
    @FXML
    private TextField categorie;
    @FXML
    private TextField prix;
    Ticket T;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
                    ServiceUser su = new ServiceUser();
                    User aold = su.readById(ConnexionUserController.id_modif);

    @FXML
    private void annulerdescription(ActionEvent event) throws IOException {
          anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("ticketview.fxml"));
                           Parent root =load.load();
                           ticketviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage s= new Stage();
                           s=(Stage)((Node)event.getSource()).getScene().getWindow();
                           s.setScene(ss);
                           s.show();
    }

    
    
    
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
 
         TicketService c = new TicketService();
                 int ip= Integer.parseInt(num_ticket.getText());;
                 Float t=Float.parseFloat(prix.getText());
                 int ab=0;
                 
               try{

           }catch(NumberFormatException e){
          Alert a = new Alert(Alert.AlertType.INFORMATION, "num ticket cant be string");
                a.show();
                ab=1;
           }
                            
                try{

  }catch(NumberFormatException e){
          Alert a = new Alert(Alert.AlertType.INFORMATION, "prix cant be string");
                a.show();
                ab=1;
    }  
               if( ab==0)
               {
                                 
                                                  
          c.ajouterticket(new Ticket(1,ip, T.getImage(), t,Categories.valueOf(categorie.getText()) ,aold.getId_user(),T.getId_event()));
                Alert a = new Alert(Alert.AlertType.INFORMATION, "ticket  ajouter avec  success");
                a.show();
                            anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("ticketview.fxml"));
                           Parent root =load.load();
                          ticketviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage s= new Stage();
                           s=(Stage)((Node)event.getSource()).getScene().getWindow();
                           s.setScene(ss);
                           s.show();
                        
    }   }
   
    
    
    
    
    
    
    private static boolean checkString(String str) {
    char ch;
    boolean capitalFlag = false;
    boolean lowerCaseFlag = false;
    boolean numberFlag = false;
    for(int i=0;i < str.length();i++) {
        ch = str.charAt(i);
        if( Character.isDigit(ch)) {
            numberFlag = true;
        }
        else if (Character.isUpperCase(ch)) {
            capitalFlag = true;
        } else if (Character.isLowerCase(ch)) {
            lowerCaseFlag = true;
        }
        if(numberFlag && capitalFlag && lowerCaseFlag)
            return true;
    }
    return false;
}
    
         public void setData(Ticket newbac) {
        this.T = newbac;

      

    }
}


