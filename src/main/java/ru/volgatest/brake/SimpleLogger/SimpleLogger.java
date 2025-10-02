/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.SimpleLogger;

import javafx.beans.WeakInvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.volgatest.brake.MainPane.MainPanel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author VTARMN013
 */
public class SimpleLogger{
    static final int CASHED_MSG_NUM = 30;
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
    
    static Alert alert;
    
    public static SimpleStringProperty lastOpenedPopupMsg = new SimpleStringProperty();
    public static SimpleObjectProperty<SimpleLogMessage> lastMsg = new SimpleObjectProperty();
    static ObservableList<SimpleLogMessage> messages = FXCollections.observableArrayList();
    
    public static void onNewPlcMsg(ByteBuffer buf){
        if(buf.capacity() <10){
            System.out.println("PLC msg length less then 2");
            return;
        }
        buf.order(ByteOrder.LITTLE_ENDIAN);
        
        SimpleLogMessage msg = new SimpleLogMessage();
        msg.msgId = buf.getInt();
        msg.type = switch(buf.get()){
            case 0 -> MsgType.INFO;
            case 1 -> MsgType.WARNING;
            case 2 -> MsgType.FAULT;
            default -> MsgType.INFO;
        };        
        msg.isPopup = buf.get() == 1;            
        msg.text = StandardCharsets.UTF_8.decode(buf.slice(6,buf.capacity()-6)).toString();
        msg.timestamp = LocalDateTime.now();
        
        onNewPlcMsg(msg);
    }
    public static void onNewPlcMsg(SimpleLogMessage msg){
        //String fullMsg = LocalDateTime.now().format(formatter)+" "+msg;        
        if(messages.size() >= CASHED_MSG_NUM){
            messages.remove(messages.size() -1);
        }
        messages.add(0, msg);
        lastMsg.setValue(msg);
        
                
        if(msg.isPopup && alert == null){// || !alert.isShowing())
            lastOpenedPopupMsg.setValue(msg.text);
            
            Alert.AlertType alertType = switch(msg.type){
                case INFO -> Alert.AlertType.NONE;
                case WARNING -> Alert.AlertType.WARNING;
                case FAULT -> Alert.AlertType.ERROR;
            };
            alert = new Alert(alertType);
            alert.getDialogPane().getStylesheets().add(MainPanel.CSSPATH);
            alert.setTitle("Notification");
            alert.setHeaderText(msg.text);
            alert.setOnCloseRequest((e)->{
                if(alert != null)alert.close();
                alert = null;
            });
            alert.setOnHiding((e)->{
                if(alert != null)alert.close();
                alert = null;
            });
            ButtonType button = new ButtonType("Ok");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(button);        
            // option != null.
            alert.show();
        }
    }
    
    public static String  getLastPopup(){
        return lastOpenedPopupMsg.getValue();
    }
    
    public static void logText(String text, MsgType type, boolean isPopup){
        SimpleLogMessage msg = new SimpleLogMessage();
        msg.msgId = 0;
        msg.type = type;        
        msg.isPopup = isPopup;            
        msg.text = text;
        msg.timestamp = LocalDateTime.now();
        
        onNewPlcMsg(msg);
    }
    
    public static void showLogs(Stage stage){
        SimpleLogsWidget simpleLogsWidget = new SimpleLogsWidget();
        Stage popupStage = new Stage();  
        //popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.initModality(Modality.NONE);
        popupStage.initOwner(stage);
        Scene dialogScene = new Scene(simpleLogsWidget);
        //dialogScene.getStylesheets().add(MainWindow.CSSPATH);
        popupStage.setScene(dialogScene);
        popupStage.show();
    }
    
    public static Label messageToLabel(SimpleLogMessage msg){                        
        Label msgLb = new Label();
        
        String msgLbContent = msg.timestamp.format(dateTimeFormatter) +" "+ msg.type.name() +" id:"+msg.msgId+" "+msg.text;        
        String style = switch(msg.type){
            case INFO -> "-fx-font-size: 14; -fx-border-width: 1px; -fx-text-fill: BLACK; -fx-border-color:BLACK;";
            case WARNING ->"-fx-font-size: 14; -fx-border-width: 1px; -fx-text-fill: GOLDENROD; -fx-border-color:GOLDENROD;";
            case FAULT -> "-fx-font-size: 14; -fx-border-width: 1px; -fx-text-fill: RED; -fx-border-color:RED;";
        };     
        msgLb.setText(msgLbContent);
        msgLb.setStyle(style);
        msgLb.setMinSize(600, 30);
        msgLb.setPrefSize(1000, 30);
        
        return msgLb;
    }
            
    static class SimpleLogsWidget extends VBox{
        public static int PREF_WIDTH = 800;
        public static int PREF_HIGHT = 800;
        VBox stringBox = new VBox(3);
        ScrollPane itemScrollPane = new ScrollPane(stringBox);
            
        public SimpleLogsWidget(){
            
            stringBox.setPrefSize(2000, 2000);
            itemScrollPane.setPrefViewportHeight(1000);
            itemScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
            itemScrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);  
            itemScrollPane.setFitToHeight(true);
            itemScrollPane.setFitToWidth(true);
            itemScrollPane.setPrefHeight(2000);
            itemScrollPane.setPrefWidth(2000);  
            
            
            synchronizeItems();
            
            //WeakInvalidationListener il =new WeakInvalidationListener(i->{synchronizeItems();});
            messages.addListener(new WeakInvalidationListener(i->synchronizeItems()));
            
            this.getStylesheets().add(MainPanel.CSSPATH);
            this.setPrefSize(PREF_WIDTH, PREF_HIGHT);
            this.getChildren().addAll(itemScrollPane);
        }
        public void synchronizeItems(){
            stringBox.getChildren().clear();
            for(var msg:messages){
                stringBox.getChildren().add(messageToLabel(msg));
            }
        }  
    }    
}
