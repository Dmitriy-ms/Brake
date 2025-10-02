/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author VTARMN013
 */
public class AutentificationPopup{
    static public final String CSSPATH = "/GUI/main.css";
    static public final String PASSWORD = "KAMAZ";    
    static public final String MESSAGE = "Введите пароль доступа:";    
    
    public static int WIDTH = 400;
    public static int HIGHT = 150;
    
    static String name = null;
    static boolean autentificationPassed = false;
    
    static public boolean show(Stage stage){
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED);
        //popupStage.setTitle("Ввод имени");
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(stage);
        
        Label text = new Label(MESSAGE);
        text.setFont(new Font(14));
        VBox.setMargin(text, new Insets(10,0,10,10));
                
        TextField textField = new TextField("");        
        
        textField.setPrefSize(WIDTH-100, 30);
        VBox.setMargin(textField, new Insets(5));
                
        Button accept = new Button("OK");
        accept.setOnAction((f)->{            
            String pass = textField.getText();
            if(PASSWORD.equals(pass)){
                autentificationPassed = true;
                popupStage.close();
            }else{
                NativeNotificationPopup.showAndWait("Пароль не верен", Alert.AlertType.WARNING);
            }
        });
        Button reject = new Button("RETURN");
        reject.setOnAction((f)->{
            autentificationPassed = false;
            popupStage.close();
        });
        
        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(accept, reject);
        VBox.setMargin(buttonBox, new Insets(5));
        
        HeaderPane headerPane = new HeaderPane("Password",false,false,true);
        headerPane.closeBtn.setOnAction(eh -> {
            autentificationPassed = false;
            popupStage.close();
        });
        
        VBox box = new VBox(5);
        box.getChildren().addAll(headerPane, text, textField, buttonBox);
        
        box.getStylesheets().add(CSSPATH);
        box.setStyle("-fx-border-color:  black; -fx-border-width: 2px;");
        box.setPrefSize(WIDTH, HIGHT);
        
        Scene dialogScene = new Scene(box);
        popupStage.setScene(dialogScene);
        popupStage.showAndWait();
        
        return autentificationPassed;
    }
}
