/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Med Aziz
 */
public class WeatherController implements Initializable {

    @FXML
    private Label tempuratureLabel;
    private final String apiKey = "0b433ac544700c94e6945a0a9384ebd5";
     //String apiUrl ="";
    @FXML
    private TextField villeselct;
    @FXML
    private DatePicker dateselect;
    @FXML
    private ImageView image;
    @FXML
    private Label month;
    @FXML
    private Label day;
    @FXML
    private Label etat;
    @FXML
    private Label ville;
    @FXML
    private ImageView load;
    String date = "2023-10-22";
    String cityName = "Ariana"; 
    String apiUrl ="";
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
         
      try {
    apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&dt=" + date + "&appid=" + apiKey;
       } catch (Exception e) {
    System.out.println("Invalid URL: " + e.getMessage());
    
}
    }
        // TODO
        

    @FXML
    private void retrieveWeather(MouseEvent event){
                LocalDate selectedDatedebut = dateselect.getValue();
                String monthName = selectedDatedebut.getMonth().getDisplayName(TextStyle.SHORT, Locale.FRENCH);
                String daynum = String.valueOf(selectedDatedebut.getDayOfMonth());
                date = selectedDatedebut.toString();
                cityName = villeselct.getText();
                apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&dt=" + date + "&appid=" + apiKey; 
        
            try {
               


            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

             String responseStr = response.toString();
            int tempIndex = responseStr.indexOf("\"temp\":");
            if (tempIndex != -1) {
                int endTempIndex = responseStr.indexOf(",", tempIndex);
                if (endTempIndex != -1) {
                    String temperatureStr = responseStr.substring(tempIndex + 7, endTempIndex);
                    double temperature = Double.parseDouble(temperatureStr);
                    
                    tempuratureLabel.setText(kelvinToCelsius(temperature)+"°C");
                    ville.setText("Tunisie,"+cityName);
                    month.setText(monthName);
                    day.setText(daynum);
                    if(kelvinToCelsius(temperature)>24.0){
                    etat.setText("Ensoleillé Claire");
                    Image photo = new Image("/images/02d.png");
                    image.setImage(photo);
                    }
                    else if(kelvinToCelsius(temperature)<24.0 && kelvinToCelsius(temperature)>19.0)
                    {etat.setText("Nuageux");
                    Image photo = new Image("/images/03.png");
                    image.setImage(photo);
                    }
                    else{
                    etat.setText("Pluvieux");
                    Image photo = new Image("/images/10d.png");
                    image.setImage(photo);
                    }
                    
                    
                    

                } else {
                    System.out.println("Temperature data not found in the response.");
                }
            } else {
                System.out.println("Temperature data not found in the response.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving weather data.");
        }
    }
           
           
 
    



private double getWeatherTemperature(String cityName, String date) throws MalformedURLException, IOException {
    try {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        String responseStr = response.toString();
        int tempIndex = responseStr.indexOf("\"temp\":");
        if (tempIndex != -1) {
            int endTempIndex = responseStr.indexOf(",", tempIndex);
            if (endTempIndex != -1) {
                String temperatureStr = responseStr.substring(tempIndex + 7, endTempIndex);
                double temperature = Double.parseDouble(temperatureStr);

                return temperature;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return -1.0; // Return a default value if temperature retrieval fails
}
private double kelvinToCelsius(double kelvin) {
    double celsius = kelvin - 273.15;
    return Math.round(celsius);
}

  
}