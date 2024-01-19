/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.user.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import classes.SessionManager;
import classes.User;
import service.ExcelSender;
import service.ServiceUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author ANIS
 */
public class GestionAdminController implements Initializable {
    @FXML
    private TableView<User> tv_users;
    @FXML
    private TableColumn<User, String> col_username;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_role;
    @FXML
    private TableColumn<User, String> col_img;
      @FXML
    private Button btnSort;
      @FXML
    private Button btnFiltre;
    
    @FXML
    private ChoiceBox cb_sortButton;
    @FXML
    private ChoiceBox cb_btnFiltre;
    
    @FXML
    private Button btnDeconnecter;
    @FXML
    private Button btnAjouter;
    
    @FXML
    private Button btnRech;
    @FXML
    private TextField tf_adm_rech;
    
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModif;
    @FXML
    private TableColumn<User, Integer> col_age;
    @FXML
    private Button btnActualiser;
    public static int id_modif ;  
    public static int anous ;  
    @FXML
    private TableColumn<User, String> col_mdp;
    @FXML
    private TableColumn<User, String> col_sexe;
    @FXML
    private TableColumn<User, Integer> ColumnId;
    /**
     * Initializes the controller class.
     */

    @FXML
    private ComboBox<String> searchAttributeComboBox;
    @FXML
    private Button btnExcel;
     public void afficherUsers()
    {
         ServiceUser su = new ServiceUser();

        List<User> lu = su.afficher();
        ObservableList<User> userList = FXCollections.observableArrayList(lu);
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_sexe.setCellValueFactory(new PropertyValueFactory<>("Sexe"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("image"));
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id_user"));

        tv_users.setItems(userList);
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherUsers();
          ObservableList<String> list = FXCollections.observableArrayList("username","email","age");
           cb_sortButton.setItems(list);
           
           ObservableList<String> list1 = FXCollections.observableArrayList("male","female","admin","client");
           cb_btnFiltre.setItems(list1);
           
            ObservableList<String> searchAttributes = FXCollections.observableArrayList("mail", "username");
            searchAttributeComboBox.setItems(searchAttributes);
            searchAttributeComboBox.setValue("username");
       
        // TODO
            tf_adm_rech.textProperty().addListener((observable, oldValue, newValue) -> {
                     searchauto();
    });
    }  
  
    @FXML
    private void btnActualiserAction(ActionEvent event) {

       afficherUsers();
         
    }

    @FXML
    private void btnDeconnecterAction(ActionEvent event) {
        SessionManager.getInstance().setCurrentUser(null);
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("AllAdmin.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
    }
    
        @FXML
    private void btnModifAction(ActionEvent event) {
            
        
        int SelectedRowIndex = tv_users.getSelectionModel().getSelectedIndex();
        
        int ColumnIndex = tv_users.getColumns().indexOf(ColumnId);

        Alert A = new Alert(Alert.AlertType.CONFIRMATION);
        if (SelectedRowIndex == -1) {
            A.setAlertType(Alert.AlertType.WARNING);
            A.setContentText("Selectionnez une colonne ! ");
            A.show();
        } else {
            String IdCell = tv_users.getColumns().get(ColumnIndex).getCellData(SelectedRowIndex).toString();
            id_modif = Integer.parseInt(IdCell);
        
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("UserModif.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
        }
    }

     @FXML
    private void btnSuppAction(ActionEvent event) {

        int SelectedRowIndex = tv_users.getSelectionModel().getSelectedIndex();
        
        int ColumnIndex = tv_users.getColumns().indexOf(ColumnId);
        
        
        Alert A = new Alert(Alert.AlertType.CONFIRMATION);
        if (SelectedRowIndex == -1) {
            A.setAlertType(Alert.AlertType.WARNING);
            A.setContentText("Selectionnez une colonne ! ");
            A.show();
        } else {
            String IdCell = tv_users.getColumns().get(ColumnIndex).getCellData(SelectedRowIndex).toString();
            int id_supp = Integer.parseInt(IdCell);
            ServiceUser su = new ServiceUser();
            A.setAlertType(Alert.AlertType.CONFIRMATION);
            User u = su.readById(id_supp);
            

          //  A.setContentText("Voulez vous supprimer l'utilisateur dont l'ID : " + IdCell + " ?");
          A.setContentText("Voulez vous supprimer\nL'utilisateur  : " + u.getUsername() +", avec l'email : "+ u.getMail()+" ?");
            Optional<ButtonType> result = A.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                su.supprimer(id_supp);
                A.setAlertType(Alert.AlertType.INFORMATION);
                A.setContentText("Utilisateur Supprimé ! ");
                A.show();
                afficherUsers();
            }
        }
    }
    
    @FXML
    private void btnAjouterAction(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("AddUser.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

@FXML
private void searchauto() {
            
            String searchAttribute = searchAttributeComboBox.getValue();
            String searchKeyword = tf_adm_rech.getText();
            ServiceUser su = new ServiceUser();

      if (searchKeyword.isEmpty()) {
      afficherUsers();
         
    }
   
    List<User> searchResults = su.searchUsersByEmailStartingWithLetter(searchAttribute,searchKeyword);
    ObservableList<User> observableResults = FXCollections.observableArrayList(searchResults);

    col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
    col_email.setCellValueFactory(new PropertyValueFactory<>("mail"));
    col_mdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
    col_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
    col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
    col_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
    col_img.setCellValueFactory(new PropertyValueFactory<>("image"));
    ColumnId.setCellValueFactory(new PropertyValueFactory<>("id_user"));

    tv_users.setItems(observableResults);
       
}
@FXML
private void sortData(ActionEvent event) {
    Object selectedItem = cb_sortButton.getSelectionModel().getSelectedItem();

    if (selectedItem != null && selectedItem instanceof String) {
        String selectedSortOption = (String) selectedItem;

        switch (selectedSortOption) {
            case "username":
                col_username.setSortType(TableColumn.SortType.ASCENDING);
                tv_users.getSortOrder().setAll(col_username);
                break;
            case "age":
                col_age.setSortType(TableColumn.SortType.ASCENDING);
                tv_users.getSortOrder().setAll(col_age);
                break;
            case "email":
                col_email.setSortType(TableColumn.SortType.ASCENDING);
                tv_users.getSortOrder().setAll(col_email);
                break;
            default:
                afficherUsers();
                break;
        }
    } else {
        
        afficherUsers();
    }
}
        private void updateTableView(List<User> data) {
    ObservableList<User> observableData = FXCollections.observableArrayList(data);
    tv_users.setItems(observableData);
    anous=tv_users.getItems().size();;
}
        
@FXML
private void filtreData(ActionEvent event) {
    ServiceUser su = new ServiceUser();

    List<User> lu = su.afficher();
    ObservableList<User> userList = FXCollections.observableArrayList(lu);

    Object selectedItem = cb_btnFiltre.getSelectionModel().getSelectedItem();

    if (selectedItem != null && selectedItem instanceof String) {
        String selectedFilterOption = (String) selectedItem;

        switch (selectedFilterOption) {
            case "female":
                {
                    List<User> filteredData = userList.stream()
                            .filter(user -> "female".equals(user.getSexe()))
                            .collect(Collectors.toList());
                    updateTableView(filteredData);
                    break;
                }
            case "male":
                {
                    List<User> filteredData = userList.stream()
                            .filter(user -> "male".equals(user.getSexe()))
                            .collect(Collectors.toList());
                    updateTableView(filteredData);
                    break;
                }
            case "admin":
                {
                    List<User> filteredData = userList.stream()
                            .filter(user -> "[\"ROLE_ADMIN\"]".equals(user.getRole()))
                            .collect(Collectors.toList());
                    updateTableView(filteredData);
                    break;
                }
          /*  case "manager":
                {
                    List<User> filteredData = userList.stream()
                            .filter(user -> "MANAGER".equals(user.getRole()))
                            .collect(Collectors.toList());
                    updateTableView(filteredData);
                    break;
                }*/
            case "client":
                {
                    List<User> filteredData = userList.stream()
                            .filter(user -> "[\"ROLE_CLIENT\"]".equals(user.getRole()))
                            .collect(Collectors.toList());
                    updateTableView(filteredData);
                    break;
                }
            default:
                afficherUsers();
                break;
        }
    } else {
        afficherUsers();
    }
}

@FXML
private void export(ActionEvent event) {
              ExcelSender d = new ExcelSender();
        d.generateExcel(tv_users);
}


}
