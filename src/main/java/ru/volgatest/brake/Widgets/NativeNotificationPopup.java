/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;

//import UI.MainController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author VTARMN013
 */
public class NativeNotificationPopup {
    public static void showAndWait(String msg, AlertType type){
        Platform.runLater(() ->{
            Alert alert = new Alert(type);
            //alert.getDialogPane().getStylesheets().add(MainController.CSSPATH);
            alert.setTitle("Notification");
            alert.setHeaderText(msg);
            ButtonType ok = new ButtonType("Ok");        
            alert.setOnCloseRequest((e)->alert.close());
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ok);        
            // option != null.
            alert.showAndWait();
        });
    }
    public static void show(String msg, AlertType type){
        Platform.runLater(() ->{
            Alert alert = new Alert(type);
            //alert.getDialogPane().getStylesheets().add(MainController.CSSPATH);
            alert.setTitle("Notification");
            alert.setHeaderText(msg);
            ButtonType ok = new ButtonType("Ok");
            alert.setOnCloseRequest((e)->alert.close());
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ok);
            // option != null.
            alert.show();
        });
    }
}
