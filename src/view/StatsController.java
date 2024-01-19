/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import view.reclamation.*;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO GAMING
 */
public class StatsController implements Initializable {

    @FXML
    private Text statTXT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public void setStatisticsText(String text) {
        statTXT.setText(text);
    }
     
        @FXML
    private StackedBarChart<String, Number> stackedBarChart;

    public void updateChart(Map<Integer, Integer> statistics) {
        stackedBarChart.getData().clear(); // Effacez les données précédentes

        for (Map.Entry<Integer, Integer> entry : statistics.entrySet()) {
            int typeRec = entry.getKey();
            int count = entry.getValue();

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(convertirTypeReclamationInverse(typeRec));
            series.getData().add(new XYChart.Data<>("Réclamations", count));

            stackedBarChart.getData().add(series);
        }
    }
   public String convertirTypeReclamationInverse(int typeRec) {
    
    String[] types = {"Réclamation liés aux produits", "Réclamation liés aux évènements", "Réclamation liés aux activités"};

    if (typeRec >= 1 && typeRec <= types.length) {
        return types[typeRec - 1]; 
    } else {
        return "Type de réclamation non valide"; 
    }
}
   
   
       @FXML
private void retourHome_rec(ActionEvent event) throws IOException {
    
    Scene scene = ((Node) event.getSource()).getScene();
    Stage stage = (Stage) scene.getWindow();
    stage.close();
}
}
