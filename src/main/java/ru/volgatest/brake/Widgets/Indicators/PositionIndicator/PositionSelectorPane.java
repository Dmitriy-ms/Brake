package ru.volgatest.brake.Widgets.Indicators.PositionIndicator;///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.example.Widgets.Indicators.PositionIndicator;
//
//import javafx.beans.InvalidationListener;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//import logic.GearboxLibrary.data.SynchStateDescriptor;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author VTARMN013
// */
//
//public class PositionSelectorPane  extends HBox{
//    static public final int RATIO_MARKER_WIDTH = 50;
//    static public final int DIRECTION_WIDTH = 25;
//    static public final int NAME_WIDTH = 80;
//
//    public PositionSelector clutchSelector;
//    public List<PositionSelector> synchSelectors = new ArrayList();
//
//    float gearRatio;
//    Label gearRatioLb = new Label();
//    Label forvardReverceMarker = new Label();
//    Label nameMarker = new Label();
//
//    boolean isAnalogMarkerVisible = false;
//
//    public PositionSelectorPane(int clutchNum, int synchNum, boolean lastGearNoNeutral, boolean showHeader){
//        super(2);
//
//        if(clutchNum == 2){
//            clutchSelector = new PositionSelector(showHeader?"CL ":null, "1","2");
//            this.getChildren().add(clutchSelector);
//        }else{
//
//        }
//        for(int i=0; i<synchNum; i++){
//            if(i == synchNum-1 && lastGearNoNeutral){
//                PositionSelector positionSelector = new PositionSelector(showHeader?String.valueOf(i+1):null, "-1","1");
//                //positionSelector.selectPosition(0);
//                synchSelectors.add(positionSelector);
//            }else{
//                PositionSelector positionSelector = new PositionSelector(showHeader?String.valueOf(i+1):null, "-1","0","1");
//                positionSelector.selectPosition("0");
//                synchSelectors.add(positionSelector);
//            }
//            this.getChildren().addAll(synchSelectors.get(i));
//        }
//        //this.setStyle("-fx-border-color: LIGHTSTEELBLUE;");
//    }
//
//    public void setSynchStateDescriptor(SynchStateDescriptor descriptor){
//        clutchSelector.selectPosition(descriptor.clutch);
//        for(int i=0; i<synchSelectors.size(); i++){
//            synchSelectors.get(i).selectPosition(String.valueOf(descriptor.synchStates[i]));
//        }
//        setRatio(descriptor.gearRatio);
//    }
//
//    public void addListener(InvalidationListener listener){
//        if(clutchSelector != null){
//            clutchSelector.currentPosition.addListener(listener);
//        }
//        for(var selector : synchSelectors){
//            selector.currentPosition.addListener(listener);
//        }
//    }
//
//    public void showClutchField(boolean show){
//        if(clutchSelector != null){
//            clutchSelector.setVisible(show);
//            clutchSelector.setManaged(show);
//        }
//    }
//
//    public void setSelectorAnalogValue(int index, float value){
//        if(index >= synchSelectors.size()){
//            return;
//        }
//        synchSelectors.get(index).setAnalogValue(value, "mm");
//    }
//    public void setClutchStateValue(int state){
//        if(clutchSelector != null){
//            clutchSelector.forceSelectPosition(String.valueOf(state));
//        }
//    }
//
//    public void setSelectorStateValue(int index, int state){
//        if(index >= synchSelectors.size()){
//            return;
//        }
//        synchSelectors.get(index).forceSelectPosition(String.valueOf(state));
//    }
//
//    public void setSelectable(boolean selectable){
//        if(clutchSelector != null){
//            clutchSelector.setSelectable(selectable);
//        }
//        synchSelectors.forEach(selector -> selector.setSelectable(selectable));
//    }
//
//    public void setRatioMarker(){
//        gearRatioLb.setPrefWidth(RATIO_MARKER_WIDTH);
//        gearRatioLb.setAlignment(Pos.CENTER);
//        gearRatioLb.setPrefHeight(PositionSelector.HIGHT*2);
//        gearRatioLb.setMinWidth(RATIO_MARKER_WIDTH);
//        gearRatioLb.setStyle("-fx-background-color: derive(-fx-base, 35%); -fx-border-color: derive(-fx-base, 10%); -fx-border-width: 1px; -fx-font-size: 16;");
//        //gearRatioLb.setMinSize(PositionSelector.ONE_CELL_WIDTH*4,PositionSelector.HIGHT*2 + 2);
//        //gearRatioLb.setMaxSize(PositionSelector.ONE_CELL_WIDTH*4,PositionSelector.HIGHT*2 + 2);
//        this.getChildren().addAll(gearRatioLb);
//    }
//    public void showDirectionMarker(){
//        forvardReverceMarker.setMaxWidth(DIRECTION_WIDTH);
//        forvardReverceMarker.setMinWidth(DIRECTION_WIDTH);
//        forvardReverceMarker.setPrefHeight(PositionSelector.HIGHT*2);
//        forvardReverceMarker.setAlignment(Pos.CENTER_LEFT);
//        this.getChildren().add(forvardReverceMarker);
//    }
//    public void showNameMarker(){
//        nameMarker.setMaxWidth(NAME_WIDTH);
//        nameMarker.setMinWidth(NAME_WIDTH);
//        nameMarker.setPrefHeight(PositionSelector.HIGHT*2);
//        nameMarker.setAlignment(Pos.CENTER);
//        nameMarker.setStyle("-fx-border-width: 1px; -fx-border-color: lightgrey; -fx-font-size: 16;");//-fx-border-color: LIGHTGREEN;
//        this.getChildren().add(nameMarker);
//    }
//    public void removeDirectionMarker(){
//        this.getChildren().remove(forvardReverceMarker);
//    }
//    public void setAnalogValueMarker(){
//        if(clutchSelector != null){
//            clutchSelector.setAnalogValueMarker();
//        }
//        synchSelectors.forEach(selector -> selector.setAnalogValueMarker());
//
//        gearRatioLb.setPrefHeight(PositionSelector.HIGHT*3);
//        forvardReverceMarker.setPrefHeight(PositionSelector.HIGHT*3);
//        nameMarker.setPrefHeight(PositionSelector.HIGHT*3);
//    }
//    public void setClutch(int clutchNum){
//        if(clutchSelector == null){
//            return;
//        }
//        clutchSelector.selectPosition(clutchNum);
//    }
//    public void setName(String name){
//        nameMarker.setText(name);
//    }
//
//    public void setRatio(float ratio){
//        gearRatio = ratio;
//        gearRatioLb.setText(ratio == 0? "0":String.valueOf(ratio));
//        if(ratio < 0){
//            forvardReverceMarker.setText("R");
//            forvardReverceMarker.setStyle("-fx-background-color: LIGHTBLUE; -fx-text-fill:black; -fx-font-size: 16;");
//            gearRatioLb.setStyle("-fx-background-color: derive(-fx-base, 35%); -fx-border-color: LIGHTBLUE; -fx-border-width: 1px; -fx-font-size: 16; -fx-text-fill:LIGHTBLUE;");
//        }else if(ratio == 0){
//            forvardReverceMarker.setText("N");
//            forvardReverceMarker.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-text-fill:black; -fx-font-size: 16;");
//            gearRatioLb.setStyle("-fx-background-color: derive(-fx-base, 35%); -fx-border-color: LIGHTSTEELBLUE; -fx-border-width: 1px; -fx-font-size: 16; -fx-text-fill:LIGHTSTEELBLUE;");
//        }else{
//            forvardReverceMarker.setText("F");
//            forvardReverceMarker.setStyle("-fx-background-color: LIGHTGREEN; -fx-text-fill:black; -fx-font-size: 16;");
//            gearRatioLb.setStyle("-fx-background-color: derive(-fx-base, 35%); -fx-border-color: LIGHTGREEN; -fx-border-width: 1px; -fx-font-size: 16; -fx-text-fill:LIGHTGREEN;");
//        }
//    }
//
//    public void markInvalid(){
//        gearRatioLb.setText("X");
//        forvardReverceMarker.setText("X");
//
//        gearRatioLb.setStyle("-fx-background-color: derive(-fx-base, 35%); -fx-border-color: RED; -fx-border-width: 1px; -fx-font-size: 16; -fx-text-fill:RED;");
//        forvardReverceMarker.setStyle("-fx-background-color: derive(-fx-base, 35%); -fx-border-color: RED; -fx-border-width: 1px; -fx-font-size: 16; -fx-text-fill:RED;");
//    }
//    public void markValid(){
//        setRatio(gearRatio);
//    }
//}
