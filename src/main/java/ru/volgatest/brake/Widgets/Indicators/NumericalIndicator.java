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
public class NumericalIndicator extends HBox{
    static public final int NAME_WIDTH = 50;
    static public final int VALUE_WIDTH = 60;
    static public final int UNIT_WIDTH = 30;

    static public final int PREF_HIGHT = 25;    
    

    public Label nameLb = new Label();
    Label valueLb = new Label();
    Label unitLb = new Label();

    double yellowLevelMin;
    double yellowLevelMax;
    double redLevelMin;
    double redLevelMax;

    public NumericalIndicator(String name, String unit){
        super(2);
        this.unitLb.setText(unit);
        this.nameLb.setText(name);

        nameLb.setMinSize(NAME_WIDTH, PREF_HIGHT);
        nameLb.setMaxSize(NAME_WIDTH, PREF_HIGHT);
        nameLb.setStyle("-fx-font-size: 16px; -fx-alignment: center;");


        valueLb.setMinSize(VALUE_WIDTH, PREF_HIGHT);
        valueLb.setMaxSize(VALUE_WIDTH, PREF_HIGHT);
        valueLb.setStyle("-fx-border-color: LIGHTSTEELBLUE; -fx-text-fill: #3474cc; -fx-font-size: 16px;");

        unitLb.setMinSize(UNIT_WIDTH, PREF_HIGHT);
        unitLb.setMaxSize(UNIT_WIDTH, PREF_HIGHT);
        unitLb.setStyle("-fx-font-size: 16px;");


        //this.setStyle("-fx-background-color: derive(-fx-base, 35%);");  
        this.getChildren().addAll(nameLb, valueLb, unitLb);
        this.setStyle("-fx-border-color: derive(-fx-base, 15%);");  
    }

    public void setValue(int val){
        valueLb.setText(String.valueOf(val));
    }

    public void setValue(float val){
        //s = s.substring(0, Math.min(s.length(), 10));
        
        valueLb.setText(String.format("%.3f", val));
    }

    public void setValue(double val){
        valueLb.setText(String.format("%.3f", val));
    }

    public void setSize(int width, int height){
        this.nameLb.setMinSize(width,height);
        this.nameLb.setMaxSize(width,height);
    }
}
