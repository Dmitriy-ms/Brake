package ru.volgatest.brake.Widgets.autoModeSequence;///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.example.Widgets.autoModeSequence;
//
//import Widgets.LimitedFloatField;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import logic.AutoCycle.data.AutoTestDescriptor;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author VTARMN013
// */
//public class AutoSequencePane extends VBox{
//    LimitedFloatField maxPreassure;
//    LimitedFloatField minPreassure;
//
//
//    HBox buttonBox = new HBox(2);
//    Button add = new Button("+");
//    Button delete = new Button("-");
//
//
//    VBox linesBox = new VBox(2);
//    ScrollPane linesScrollPane = new ScrollPane(linesBox);
//
//
//    List<AutoSequenceLineView> lines = new ArrayList();
//
//    public AutoSequencePane(){
//        this(new AutoTestDescriptor());
//    }
//    public AutoSequencePane(AutoTestDescriptor autoSequence){
//        //maxPreassure = new LimitedFloatField("Максимальное давление","bar", 0, 50, autoSequence.maxPreassure);
//        //minPreassure = new LimitedFloatField("Минимальное давление","bar", 0, 50, autoSequence.minPreassure);
//
//
//        linesBox.setPrefSize(2000, 2000);
//        linesScrollPane.setPrefViewportHeight(1000);
//        linesScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
//        linesScrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
//        linesScrollPane.setFitToHeight(true);
//        linesScrollPane.setFitToWidth(true);
//        linesScrollPane.setPrefHeight(2000);
//        linesScrollPane.setPrefWidth(2000);
//
//
//        for(var line:autoSequence.sequence){
//            //AutoSequenceLineView view = new AutoSequenceLineView(line);
//            //lines.add(view);
//            //linesBox.getChildren().add(view);
//        }
//    }
//
//    public AutoTestDescriptor getSequence(){
//        AutoTestDescriptor seq = new AutoTestDescriptor();
//        for(var line:lines){
//            //seq.sequence.add(line.getLineValues());
//        }
//        return seq;
//    }
//}
