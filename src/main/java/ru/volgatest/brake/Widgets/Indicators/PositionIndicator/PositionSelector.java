/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.Indicators.PositionIndicator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */

public class PositionSelector extends VBox{
    public static int ONE_CELL_WIDTH = 30;
    public static int HIGHT = 30;
    
    public static Font backgroundFont = new Font(7);
    public static Font pinFont = new Font(9);
    
    public SimpleIntegerProperty currentPosition = new SimpleIntegerProperty();
    
    
    int clickedPosiiton;
    boolean isSelectable = true;
    
    HBox holderBox = new HBox();
            
    Label pinLb = new Label();
    HBox pin = new HBox(pinLb);
    
    Label analogValueLb;
    
    List<String> positionsNames;
    //List<Label> markers = new ArrayList<>();
    List<Holder> holders = new ArrayList<>();
    
    int ID;
    
    public PositionSelector(String indicatorName, String... positionsNames){
        //this.isSelectable = isSelectable;
        this.positionsNames = Arrays.asList(positionsNames);       

        
        holderBox.setStyle("-fx-background-color: derive(-fx-base, 35%); -fx-border-color: derive(-fx-base, 10%);");
        holderBox.setMinSize(this.positionsNames.size() * HIGHT+2, HIGHT+1);
        holderBox.setMaxSize(this.positionsNames.size() * HIGHT+2, HIGHT+1);
        
        
        for(int i = 0; i<this.positionsNames.size(); i++){
            Holder holder = new Holder(i, this.positionsNames.get(i));
            holders.add(holder);
            holderBox.getChildren().add(holder);
        }
        
        int INITIAL_POS = 0;
        
        pinLb.setFont(pinFont);
        pinLb.setStyle("-fx-background-color: transparent;");
        pinLb.setText(this.positionsNames.get(INITIAL_POS));
        pinLb.setAlignment(Pos.CENTER);
        pin.setMinSize(HIGHT, HIGHT);
        pin.setMaxSize(HIGHT, HIGHT);
        pin.getStyleClass().add("button"); 
        //pin.getChildren().add(pinLb);
        
        
        var holder = holders.get(INITIAL_POS);
        holder.getChildren().add(pin);
        
        
        if(indicatorName != null){
            Label name = new Label(indicatorName);
            name.setFont(new Font(14));
            name.setAlignment(Pos.CENTER);
            name.setMinSize(this.positionsNames.size() * HIGHT+2, HIGHT+1);
            name.setMaxSize(this.positionsNames.size() * HIGHT+2, HIGHT+1);
            name.setStyle("-fx-background-color: derive(-fx-base, 35%);-fx-border-color: derive(-fx-base, 10%);");
            this.getChildren().add(name);
        }
        
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(holderBox);
    }
    
    
    

    public void selectPosition(int pos){
        if(!isSelectable){
            return;
        }
        PositionSelector.this.forceSelectPosition(pos);
    }
    public void selectPosition(String posName){
        if(!isSelectable){
            return;
        }
        forceSelectPosition(posName);
    }
    
    public void forceSelectPosition(String posName){
        int index = positionsNames.indexOf(posName); 
        /*
        if(index<0){ //|| index>=positionsNames.size()
            return;
        }
*/
        forceSelectPosition(index);
    }
    public void forceSelectPosition(int pos){
        holders.forEach(holder -> holder.getChildren().remove(pin)); 
        if(pos>=0 && pos<holders.size()){
            pinLb.setText(this.positionsNames.get(pos));

            Holder newHolder = holders.get(pos);
            newHolder.getChildren().add(pin);            
        }
        currentPosition.set(pos);
    }    
    
    public void setAnalogValueMarker(){
        analogValueLb = new Label("-|-");
        analogValueLb.setMinSize(this.positionsNames.size() * HIGHT+2, HIGHT+1);
        analogValueLb.setMaxSize(this.positionsNames.size() * HIGHT+2, HIGHT+1);
        analogValueLb.setFont(new Font(12));
        analogValueLb.setAlignment(Pos.CENTER);
        analogValueLb.setStyle("-fx-background-color: derive(-fx-base, 35%);-fx-border-color: derive(-fx-base, 10%);");
        this.getChildren().add(analogValueLb);
    }
    
    public int getPosition(){
        return currentPosition.getValue();
    }
    
    public String getPositionName(){
        return positionsNames.get(currentPosition.getValue());
    }
    
    public void setIntId(int id){
        ID = id;
    }
    
    public int getIntId(){
        return ID;
    }   
    
    public void setAnalogValue(float value, String unit){
        analogValueLb.setText(String.format("%.2f", value)+" "+unit);
    }
    
    public void block(boolean blocked){
        this.setDisable(blocked);
    }
    public void setSelectable(boolean selectable){
        this.isSelectable = selectable;
    }
    
    class Holder extends StackPane{
        final int position;
        final String name;
        public Holder(int position, String name){
            this.position = position;
            this.name = name;
            
            this.setStyle("-fx-background-color: derive(-fx-base, 35%);-fx-border-color: derive(-fx-base, 10%);");
            this.setMinSize(HIGHT, HIGHT);
            this.setMaxSize(HIGHT, HIGHT);
            this.setAlignment(Pos.CENTER);
            this.setOnMouseClicked(eh -> {
                selectPosition(position);                
            });
            Label backgroundLb = new Label(name);
            backgroundLb.setFont(backgroundFont);
            backgroundLb.setStyle("-fx-background-color: transparent;");      
            this.getChildren().add(backgroundLb);
        }
        public void block(boolean blocked){
            this.setDisable(blocked);
        }
    }
}
