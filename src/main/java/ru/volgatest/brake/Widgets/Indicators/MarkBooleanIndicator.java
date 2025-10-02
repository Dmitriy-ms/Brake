/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.Indicators;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 *
 * @author VTARMN013
 */
public class MarkBooleanIndicator extends HBox{
    static public final int NAME_WIDTH = 120;      
    static public final int PREF_HIGHT = 25;

    static public final int NAME_WIDTH_SMALL = 120;      
    static public final int PREF_HIGHT_SMALL = 20;
    
    Label nameLb = new Label();
    //Circle valueMarker = new Circle(4);
    Pane marker = new Pane();
    
    boolean isColorGreen = true;
    public MarkBooleanIndicator(String name, boolean isSmall){
        super(3);
        nameLb.setText(name);
        nameLb.setMinSize(NAME_WIDTH, isSmall ? PREF_HIGHT_SMALL:PREF_HIGHT);
        nameLb.setMaxSize(NAME_WIDTH, isSmall ? PREF_HIGHT_SMALL:PREF_HIGHT);
        
        marker.setMinSize(isSmall ? PREF_HIGHT_SMALL:PREF_HIGHT, isSmall ? PREF_HIGHT_SMALL:PREF_HIGHT);
        marker.setMaxSize(isSmall ? PREF_HIGHT_SMALL:PREF_HIGHT, isSmall ? PREF_HIGHT_SMALL:PREF_HIGHT);
        marker.setStyle("-fx-border-color: LIGHTSTEELBLUE; -fx-background-color: derive(-fx-base, 15%);");
        //pane.getChildren().add(valueMarker);
        this.getChildren().addAll(nameLb, marker);
        this.setStyle("-fx-border-color: derive(-fx-base, 15%);"); 
        this.setMaxHeight(isSmall ? PREF_HIGHT_SMALL:PREF_HIGHT);
        nameLb.setFont(new Font(isSmall ? 10:12));
    }
    
    public MarkBooleanIndicator(String name, boolean isSmall, boolean isGreen){
        this(name, isSmall);
        this.isColorGreen = isGreen;
    }

    public void setValue(boolean val){
        if(val){
            if(isColorGreen){
                marker.setStyle("-fx-border-color: LIGHTSTEELBLUE; -fx-background-color: PALEGREEN;"); //LIGHTGREEN
            }else{
                marker.setStyle("-fx-border-color: LIGHTSTEELBLUE; -fx-background-color: RED;"); //LIGHTGREEN
            }
        }else{
            marker.setStyle("-fx-border-color: LIGHTSTEELBLUE; -fx-background-color: derive(-fx-base, 15%);"); 
            //valueMarker.setFill(Color.TRANSPARENT);
        }
    }

    public void setSize(int width, int height){
        this.nameLb.setMinSize(width,height);
        this.nameLb.setMaxSize(width,height);
    }
}