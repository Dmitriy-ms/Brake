/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.tables;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author VTARMN013
 */
public class SequenceNameItemHolder<T> extends HBox implements SequenceItemView{
    public static final int HIGHT = 30;
    static public final int GB_NAME_LABEL_MIN_WIDTH = 50;
    static public final int GB_NAME_LABEL_WIDTH = 700;
    
    public Label nameLabel = new Label();
    boolean isSelected;
    int tableIndex;
    T value;

    public SequenceNameItemHolder(T value, String name){
        this.value = value;
        nameLabel.setText(name);
        
        nameLabel.setMaxWidth(GB_NAME_LABEL_WIDTH);
        nameLabel.setMinWidth(GB_NAME_LABEL_MIN_WIDTH);
        nameLabel.setPrefHeight(HIGHT);
        nameLabel.setAlignment(Pos.CENTER_LEFT);
        
        nameLabel.setStyle("-fx-font-size: 14;");
        
        this.getChildren().add(nameLabel);
    }

    public T getValue(){
        return value;    
    }
    
    @Override
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
        //this.setStyle(isSelected?"-fx-border-color: BLUE;":"-fx-border-color: LIGHTSLATEGREY;");   
    }

    @Override
    public int getIndex() {
        return tableIndex;
    }

    @Override
    public void setIndex(int index) {
        this.tableIndex = index;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public Pane getPane() {
        return this;
    }

    @Override
    public float getHight() {
        return HIGHT;
    }
}
