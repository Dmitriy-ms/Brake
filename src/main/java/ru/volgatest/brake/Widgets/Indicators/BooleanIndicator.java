/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.Indicators;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author VTARMN013
 */
public class BooleanIndicator extends HBox{
    static public final int VALUE_WIDTH = 60;        
    static public final int PREF_HIGHT = 30;

    Label nameLb = new Label();
    Label valueLb = new Label();

    public BooleanIndicator(String name){
        nameLb.setText(name);

        valueLb.setStyle("-fx-background-color: derive(-fx-base, 35%);");  

        this.getChildren().addAll(nameLb, valueLb);
    }

    public void setValue(boolean val){
        valueLb.setText(val ? "true":"false");
    }
}