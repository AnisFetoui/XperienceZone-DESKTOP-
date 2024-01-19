
package pi.FX;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("admin/Home.fxml"))));
        primaryStage.setTitle("Dashboard Admin");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();


    }
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
