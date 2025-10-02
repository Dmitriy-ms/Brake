/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 *
 * @author VTARMN013
 */
public class HeaderPane extends HBox{
    static final String CROSS_ICON_PATH = "/GUI/artefacts/common/small_cross.png";
    
    static final int BUTTON_HIGHT = 25;
    static public final int PANEL_HIGHT = BUTTON_HIGHT +6;
    
    //Image CROSS_IMAGE = new Image(CROSS_ICON_PATH);
    
    double x,y;
    public HeaderButton hideBtn = new HeaderButton("_");
    public HeaderButton expandBtn = new HeaderButton("\u25A1");
    public HeaderButton closeBtn = new HeaderButton("\u00D7");
    private HBox buttonBox = new HBox(3);
    
    Label name = new Label("VT manager");
    Region spacer = new Region();
    
    //HBox btnPane = new HBox();
    
    public HeaderPane(boolean expandButtonActive, boolean hideButtonActive, boolean closeButtonActive){     
        super();
        //name.setPadding(new Insets(2));
        /*
        ImageView imageView =new ImageView();
        imageView.setImage(CROSS_IMAGE);
        //imageView.resize(HIGHT, HIGHT);     
        imageView.setFitHeight(BUTTON_HIGHT - 5);
        imageView.setFitWidth(BUTTON_HIGHT - 5);
        */
                
        this.getStyleClass().add("menu-bar");
        //this.setAlignment(Pos.CENTER);
        this.setPrefHeight(PANEL_HIGHT);
        this.setMinHeight(PANEL_HIGHT);
        HBox.setMargin(buttonBox, new Insets(3));
        
        this.getChildren().addAll(spacer, buttonBox);
        
        if(hideButtonActive){
            hideBtn.setOnAction(eh -> {
                Stage stage = (Stage)hideBtn.getScene().getWindow();
                stage.setIconified(true);
                /*
                Stage stage = (Stage)this.getScene().getWindow();
                if(stage.isShowing()){
                    stage.hide();
                }
                else {
                    stage.show();
                }
                */
            });
            buttonBox.getChildren().add(hideBtn);
        }
        if(expandButtonActive){
            expandBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, eh -> {
                Stage stage = (Stage)expandBtn.getScene().getWindow();
                stage.setMaximized(!stage.maximizedProperty().getValue());
            });
            buttonBox.getChildren().add(expandBtn);

        }
        
        if(closeButtonActive){
            closeBtn.setOnAction(eh -> {
                Stage stage = (Stage)this.getScene().getWindow();
                stage.close();                
            });
            buttonBox.getChildren().add(closeBtn);
        }        
                
        spacer.setOnMousePressed(eh -> {
            x = eh.getSceneX();
            y = eh.getSceneY();
        });
        
        spacer.setOnMouseDragged(eh -> {
            Stage stage = (Stage)this.getScene().getWindow();
            stage.setX(eh.getScreenX() - x);
            stage.setY(eh.getScreenY() - y);
        });

        //spacer.getStyleClass().add("menu-bar");
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setStyle("-fx-background-color :transparent;");
        
        
        //spacer.setPrefHeight(BUTTON_HIGHT +4);
        //spacer.setMinHeight(BUTTON_HIGHT +4);
        //spacer.setMaxHeight(BUTTON_HIGHT +4);
        //spacer.setPrefWidth(Double.MAX_VALUE);
        
        //spacer.setAlignment(Pos.BASELINE_LEFT);
        
        HBox.setMargin(name, new Insets(4,0,0,4));
        

        //this.setMinHeight(BUTTON_HIGHT +15);
        //this.getChildren().addAll(btnPane);
    }
    
    public void setOnCloseRequest(Runnable r){
        closeBtn.setOnAction(eh -> r.run());
    }
    
    public HeaderPane(String name,boolean expandButtonActive, boolean hideButtonActive, boolean closeButtonActive){ 
        this(expandButtonActive, hideButtonActive, closeButtonActive);
        this.name.setText(name);
        this.getChildren().add(0,this.name);
    }
    
    public void setName(String nameStr){
        if(!this.getChildren().contains(this.name)){
            this.getChildren().add(0,this.name);
        }
        this.name.setText(nameStr);
    }
    
    public void addElement(Node node){
        this.getChildren().add(0,node);
    }
    
    public class HeaderButton extends Button{
        public HeaderButton(){
            this.getStyleClass().add("button");
            this.setMaxSize(BUTTON_HIGHT, BUTTON_HIGHT);
            this.setMinSize(BUTTON_HIGHT, BUTTON_HIGHT);
            
            //this.setStyle("-fx-text-fill :white;");//-fx-background-color :rgb(30, 116, 198);  -fx-font-size :14px;
            /*
            this.setOnMouseEntered(eh -> {
                this.setStyle("-fx-background-color :rgb(30, 116, 198);");
            });
            this.setOnMouseExited(eh -> {
                this.setStyle("-fx-background-color :transparent;");
            });
            */
        }
        public HeaderButton(String name){
            this();
            this.setText(name);
        }
    }
}
