package ru.volgatest.brake;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.volgatest.brake.MainPane.MainPanel;
import ru.volgatest.brake.ObjectsLibrary.Library;
import ru.volgatest.brake.SimpleLogger.MsgType;
import ru.volgatest.brake.SimpleLogger.SimpleLogger;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Library.loadAll();
        } catch (Exception e) {
            SimpleLogger.logText("Error loading library: " + e.getMessage(), MsgType.FAULT, true);
        }


        Scene scene = new Scene(new MainPanel(), 1900, 1000);
        scene.getStylesheets().add("/main.css");
        primaryStage.setScene(scene);
//        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}