package ru.volgatest.brake.Widgets.autoModeSequence;///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.example.Widgets.autoModeSequence;
//
//import Widgets.LimitedFloatField;
//import Widgets.LimitedIntegerField;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.ComboBox;
//import javafx.scene.layout.HBox;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author VTARMN013
// */
//public class AutoSequenceLineView extends HBox{
//    static public final int HIGHT = 20;
//    static public final int WIDTH = 120;
//
//    static public final int SYNCH_NUM = 5;
//
//
//    LimitedFloatField time;
//    LimitedFloatField inputSpeed;
//    LimitedFloatField outputTorque;
//
//    LimitedIntegerField clutchOnePos;
//    LimitedIntegerField clutchTwoPos;
//
//    CheckBox clutchOne = new CheckBox();
//    CheckBox clutchTwo = new CheckBox();
//
//    List<ComboBox<Integer>> synchesList = new ArrayList();
//
//    /*
//    LimitedIntegerField synch_1_pos;
//    LimitedIntegerField synch_2_pos;
//    LimitedIntegerField synch_3_pos;
//    LimitedIntegerField synch_4_pos;
//    LimitedIntegerField synch_5_pos;
//    */
//
//    public AutoSequenceLineView(){
//        this(new float[10]);
//    }
//
//    public AutoSequenceLineView(float[] values){
//        time = new LimitedFloatField("time",0, Float.MAX_VALUE, values[0]);
//        inputSpeed = new LimitedFloatField("inputSpeed",-3000, 3000, values[1]);
//        outputTorque = new LimitedFloatField("outputTorque",-400, 400, values[2]);
//
//        clutchOne.selectedProperty().set(values[3] != 0);
//        clutchTwo.selectedProperty().set(values[4] != 0);
//
//        for(int i=0; i<SYNCH_NUM; i++){
//            ComboBox<Integer> synchCombo = new ComboBox<>();
//            synchCombo.getItems().addAll(-1,0,1);
//            synchCombo.setValue((int)values[5 + i]);
//            synchesList.add(synchCombo);
//        }
//        prepareWidgets();
//    }
//
//    private void prepareWidgets(){
//        time.setFieldSize(WIDTH, HIGHT);
//        inputSpeed.setFieldSize(WIDTH, HIGHT);
//        outputTorque.setFieldSize(WIDTH, HIGHT);
//        this.getChildren().addAll(time,inputSpeed,outputTorque);
//
//        for(var cmb : synchesList){
//            cmb.setMinSize(HIGHT*2, HIGHT);
//            this.getChildren().add(cmb);
//        }
//        this.setStyle("-fx-border-color: derive(-fx-base, 15%);");
//    }
//
//    public float[] getLineValues(){
//        float[] line = new float[10];
//        line[0] = time.getValue();
//        line[1] = inputSpeed.getValue();
//        line[2] = outputTorque.getValue();
//        line[3] = clutchOne.isSelected()? 1 : 0;
//        line[4] = clutchOne.isSelected()? 1 : 0;
//        for(int i=0; i<SYNCH_NUM; i++){
//            line[5 + i] = synchesList.get(i).getValue();
//        }
//
//        return line;
//    }
//}
