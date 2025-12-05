package PainSim;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage){
        stage.setTitle("Pain Simulator");
        stage.show();
        PaneOrganizer paneOrganizer = new PaneOrganizer();
        Scene scene = new Scene(paneOrganizer.getRoot(), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        paneOrganizer.setKeyPress();
        stage.setScene(scene);
    }
    public static void main(String[] args){
        launch(args);
    }
}
