/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Evenement;
import service.EventService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author the_old_is_back
 */
public class modifiereventController implements Initializable {

    @FXML
    private AnchorPane anchorme;
    @FXML
    private TextField nom_event;
    @FXML
    private TextField descript;
    @FXML
    private TextField date_event;
    @FXML
    private TextField heure_event;
    @FXML
    private TextField lieu_event;
    Evenement thisbac;
    @FXML
    private DatePicker date1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void annulerbac(ActionEvent event) throws IOException {
           anchorme.setVisible(false);

        FXMLLoader load = new FXMLLoader(getClass().getResource("evenementview.fxml"));
        Parent root = load.load();
        evenementviewController c2 = load.getController();
        Scene ss = new Scene(root);
        Stage s = new Stage();
        s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(ss);
        s.show();
    }

    @FXML
    private void modifierevent(ActionEvent event) throws IOException {
    
         EventService c = new EventService();
                 int ip;
                 int ab=0;

               

               if( ab==0)
               {

            
                
                        
                   
                        
                        if(  (checkString(descript.getText())) )
                        {
                            if (nom_event.getText().equals(""))
                            {
                               Alert a = new Alert(Alert.AlertType.INFORMATION, "nom cant be null");
                a.show(); 
                            }
                            else
                            {
                                

        // Parse the string into a LocalDate

        // Convert LocalDate to java.sql.Date
        // Parse the string into a LocalDate
            
        // Convert LocalDate to java.sql.Date
                           Date sqlDate= new Date(date1.getValue().getYear(), date1.getValue().getMonthValue(), date1.getValue().getDayOfMonth());
    
          c.modifierevent(new Evenement(thisbac.getId_event(),nom_event.getText(), descript.getText(),sqlDate,heure_event.getText() ,lieu_event.getText(),0,"",""));
                Alert a = new Alert(Alert.AlertType.INFORMATION, "evenement  modifier avec  success");
                a.show();
                            anchorme.setVisible(false);
        
                           FXMLLoader load = new FXMLLoader(getClass().getResource("evenementview.fxml"));
                           Parent root =load.load();
                          evenementviewController c2=  load.getController();
                           Scene ss= new Scene(root);
                           Stage s= new Stage();
                           s=(Stage)((Node)event.getSource()).getScene().getWindow();
                           s.setScene(ss);
                           s.show();
                            }}
                        else
                        {
                             Alert a = new Alert(Alert.AlertType.INFORMATION, "referance must have atleast one uppercase , one lowercase , one number ");
                              a.show();
                        }
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
    
    
        public void setData(Evenement newbac) {
        this.thisbac = newbac;

        nom_event.setText(newbac.getNom_event());
        descript.setText(newbac.getDescript());
        Date d = new Date(newbac.getDate_event().getYear(), newbac.getDate_event().getMonth(), newbac.getDate_event().getDay());
      LocalDate e = d.toLocalDate();
        date1.setValue(e);
        heure_event.setText("" + newbac.getHeure_event());
        lieu_event.setText("" + newbac.getLieu_event());

    }
}


