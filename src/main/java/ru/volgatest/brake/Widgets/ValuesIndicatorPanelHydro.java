///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ru.volgatest.brake.Widgets;
//
//
//import com.example.UI.temp.Variables;
//import com.example.Widgets.Indicators.*;
//import com.example.logic.PLCCommandController;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//
//
///**
// * @author VTARMN013
// */
//public class ValuesIndicatorPanelHydro extends IndicatorPanel {
//    public static String BACKGROUND_PATH = "/HydraulicCircuitBgrClr.png";
////    public static String BACKGROUND_PATH = "/service_stand.png";
//
//
//    //    public static String BACKGROUND_PATH = "/HydraulicCircuitClr.png";
//    //    public static String BACKGROUND_PATH = "/HydraulicCircuit.png";
//    //    public static String CSS_PATH = "/UI/indicators_panel.css";
//
//    static public final int PREF_WIDTH = 1920;
//    static public final int PREF_HIGHT = 1080;
//
//    RectangularBooleanIndicator rectangularBooleanIndicatorF1 = new RectangularBooleanIndicator(28, 28, 45);
//    RectangularBooleanIndicator rectangularBooleanIndicatorF2 = new RectangularBooleanIndicator(28, 28, 45);
//    RectangularBooleanIndicator rectangularBooleanIndicatorF3 = new RectangularBooleanIndicator(28, 28, 45);
//    RectangularBooleanIndicator rectangularBooleanIndicatorF4 = new RectangularBooleanIndicator(28, 28, 45);
//
//    RectangularBooleanIndicator rectangularBooleanIndicatorKA = new RectangularBooleanIndicator(11, 11, 45);
//    RectangularBooleanIndicator rectangularBooleanIndicatorKB = new RectangularBooleanIndicator(11, 11, 45);
//
//    RectangularBooleanIndicator rectangularBooleanIndicatorK1 = new RectangularBooleanIndicator(33, 17, 0);
//    RectangularBooleanIndicator rectangularBooleanIndicatorK2 = new RectangularBooleanIndicator(17, 33, 0);
//    RectangularBooleanIndicator rectangularBooleanIndicatorK3 = new RectangularBooleanIndicator(33, 17, 0);
//    RectangularBooleanIndicator rectangularBooleanIndicatorK4 = new RectangularBooleanIndicator(33, 17, 0);
//
//    RectangularBooleanIndicator rectangularBooleanIndicatorLLS_HP = new RectangularBooleanIndicator(15, 15, 0);
//    RectangularBooleanIndicator rectangularBooleanIndicatorLLS_LP = new RectangularBooleanIndicator(15, 15, 0);
//
//
//    CircleBooleanIndicator startIndicator = new CircleBooleanIndicator(40, 40, 45);
//
//    NumericalIndicator inputPT1Lb = new NumericalIndicator("PT1", "Bar");
//    NumericalIndicator inputPT2Lb = new NumericalIndicator("PT2", "Bar");
//    NumericalIndicator inputPT3Lb = new NumericalIndicator("PT3", "Bar");
//    NumericalIndicator inputPT4Lb = new NumericalIndicator("PT4", "Bar");
//
//    NumericalIndicator inputTT1Lb = new NumericalIndicator("TT1", "°С");
//    NumericalIndicator inputTT2Lb = new NumericalIndicator("TT2", "°C");
//    NumericalIndicator inputTT3Lb = new NumericalIndicator("TT3", "°C");
//    NumericalIndicator inputTT4Lb = new NumericalIndicator("TT4", "°C");
//    NumericalIndicator inputTT5Lb = new NumericalIndicator("TT5", "°C");
////    NumericalIndicator inputTT3Lb = new NumericalIndicator("TT3", "°С");
////    NumericalIndicator inputTT4Lb = new NumericalIndicator("TT4", "°С");
//
//    NumericalIndicator inputPCVLb = new NumericalIndicator("КПП", "%");
//
//
//    MarkBooleanIndicator inputTorqueMode = new MarkBooleanIndicator("Режим момента", false);
//
//    public VBox manualControlPanel;
//    /**
//     * Variables
//     */
//    CheckBox checkBoxK1, checkBoxK2, checkBoxK3, checkBoxK4, checkBoxKA, checkBoxKB, checkBoxM1, checkBoxM2;
//    LimitedFloatField PCV, TCV;
//    //region Values manualControlPanel
//    byte k1, k2, k3, k4, ka, kb, m1, m2;
//    float pcv, tcv;
//    //endregion
//
//    public ValuesIndicatorPanelHydro() {
//
//        super(false, BACKGROUND_PATH);
//
//
//        rectangularBooleanIndicatorF1.setValue(false);
//        rectangularBooleanIndicatorF2.setValue(false);
//        rectangularBooleanIndicatorF3.setValue(false);
//        rectangularBooleanIndicatorF4.setValue(false);
//        addIndicator(rectangularBooleanIndicatorF1, new Coordinate(0.211, 0.642));
//        addIndicator(rectangularBooleanIndicatorF2, new Coordinate(0.343, 0.642));
//        addIndicator(rectangularBooleanIndicatorF3, new Coordinate(0.806, 0.654));
//        addIndicator(rectangularBooleanIndicatorF4, new Coordinate(0.895, 0.654));
////
//
//        rectangularBooleanIndicatorKA.setValue(false);
//        rectangularBooleanIndicatorKB.setValue(false);
//        addIndicator(rectangularBooleanIndicatorKA, new Coordinate(0.505, 0.026));
//        addIndicator(rectangularBooleanIndicatorKB, new Coordinate(0.588, 0.026));
//
////        addToggleListener(rectangularBooleanIndicatorKA);
////        addToggleListener(rectangularBooleanIndicatorKB);
//
//        rectangularBooleanIndicatorK1.setValue(false);
//        rectangularBooleanIndicatorK2.setValue(false);
//        rectangularBooleanIndicatorK3.setValue(false);
//        rectangularBooleanIndicatorK4.setValue(false);
//        addIndicator(rectangularBooleanIndicatorK1, new Coordinate(0.313, 0.374));
//        addIndicator(rectangularBooleanIndicatorK2, new Coordinate(0.129, 0.349));
//        addIndicator(rectangularBooleanIndicatorK3, new Coordinate(0.434, 0.374));
//        addIndicator(rectangularBooleanIndicatorK4, new Coordinate(0.069, 0.529));
//
////        addToggleListener(rectangularBooleanIndicatorK1);
////        addToggleListener(rectangularBooleanIndicatorK2);
////        addToggleListener(rectangularBooleanIndicatorK3);
////        addToggleListener(rectangularBooleanIndicatorK4);
//
//        rectangularBooleanIndicatorLLS_HP.setValue(false);
//        rectangularBooleanIndicatorLLS_LP.setValue(false);
//        addIndicator(rectangularBooleanIndicatorLLS_HP, new Coordinate(0.561, 0.721));
//        addIndicator(rectangularBooleanIndicatorLLS_LP, new Coordinate(0.757, 0.721));
////
//        addIndicator(inputPT1Lb, new Coordinate(0.174, 0.206));
//        addIndicator(inputPT2Lb, new Coordinate(0.174, 0.078));
//        addIndicator(inputPT3Lb, new Coordinate(0.652, 0.266));
//        addIndicator(inputPT4Lb, new Coordinate(0.820, 0.336));
////
//        addIndicator(inputTT1Lb, new Coordinate(0.820, 0.389));
//        addIndicator(inputTT2Lb, new Coordinate(0.374, 0.892));
//        addIndicator(inputTT3Lb, new Coordinate(0.652, 0.391));
//        addIndicator(inputTT4Lb, new Coordinate(0.646, 0.021));
//
//        manualControlPanel = new VBox();
//        manualControlPanel.setVisible(false);
//        manualControlPanel.setMaxWidth(100);
//
//        manualControlPanel.setStyle("-fx-border-color: #373e43;" +
//                "-fx-border-radius: 1px;" +
//                "-fx-border-width: 2px;");
////        manualControlPanel.getStyleClass().add("manualControlPanel");
//
//        checkBoxK1 = new CheckBox("K1");
//        checkBoxK2 = new CheckBox("K2");
//        checkBoxK3 = new CheckBox("K3");
//        checkBoxK4 = new CheckBox("K4");
//        VBox.setMargin(checkBoxK4, new Insets(0, 0, 10, 0));
//        checkBoxK1.setSelected(rectangularBooleanIndicatorK1.onOff);
//        checkBoxK2.setSelected(rectangularBooleanIndicatorK2.onOff);
//        checkBoxK3.setSelected(rectangularBooleanIndicatorK3.onOff);
//        checkBoxK4.setSelected(rectangularBooleanIndicatorK4.onOff);
//
//
//        checkBoxKA = new CheckBox("KA");
//        checkBoxKB = new CheckBox("KB");
//        VBox.setMargin(checkBoxKB, new Insets(0, 0, 10, 0));
//        checkBoxKA.setSelected(rectangularBooleanIndicatorKA.onOff);
//        checkBoxKB.setSelected(rectangularBooleanIndicatorKB.onOff);
//
//        checkBoxM1 = new CheckBox("M1");
//        checkBoxM2 = new CheckBox("M2");
//
//        /*addCheckBoxListener(checkBoxK1);
//        addCheckBoxListener(checkBoxK2);
//        addCheckBoxListener(checkBoxK3);
//        addCheckBoxListener(checkBoxK4);
//
//        addCheckBoxListener(checkBoxKA);
//        addCheckBoxListener(checkBoxKB);
//
//        addCheckBoxListener(checkBoxM1);
//        addCheckBoxListener(checkBoxM2);*/
//
//        VBox checkBoxsVbox = new VBox(3);
////        checkBoxsVbox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//        checkBoxsVbox.setMaxWidth(70);
//        checkBoxsVbox.setAlignment(Pos.CENTER_LEFT);
//        checkBoxsVbox.setPadding(new Insets(5));
//
//        checkBoxsVbox.getChildren().addAll(
//                checkBoxK1,
//                checkBoxK2,
//                checkBoxK3,
//                checkBoxK4,
//
//                checkBoxKA,
//                checkBoxKB,
//
//                checkBoxM1,
//                checkBoxM2
//
////                PCV,
////                TCV
//        );
//
//        PCV = new LimitedFloatField("PCV", "%", 0, 100, 0);
//        TCV = new LimitedFloatField("TCV", "%", 0, 100, 0);
//        PCV.setAlignment(Pos.CENTER);
//
//        PCV.setNameWidth(30);
//        TCV.setNameWidth(30);
//
//        PCV.setFieldSize(50, 25);
//        TCV.setFieldSize(50, 25);
//        PCV.textField.setAlignment(Pos.CENTER_RIGHT);
//        TCV.textField.setAlignment(Pos.CENTER_RIGHT);
//        PCV.textField.setBorder(new Border(new BorderStroke(Color.web("#373e43"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//        TCV.textField.setBorder(new Border(new BorderStroke(Color.web("#373e43"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//        PCV.textField.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
//            PCV.textField.selectAll();
//            PCV.textField.requestFocus();
//        });
//        TCV.textField.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
//            TCV.textField.selectAll();
//            TCV.textField.requestFocus();
//        });
//        HBox.setMargin(PCV.textField, new Insets(0, 5, 0, 0));
//        HBox.setMargin(TCV.textField, new Insets(0, 5, 0, 0));
//
//        PCV.setUnitWidth(15);
//        TCV.setUnitWidth(15);
//
//
//        VBox limitsVbox = new VBox(5);
////        limitsVbox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
////        limitsVbox.setMaxWidth(65);
//        limitsVbox.setAlignment(Pos.CENTER);
//        limitsVbox.setPadding(new Insets(5));
//
//        limitsVbox.getChildren().addAll(
//                PCV,
//                TCV
//        );
//
//
//        Button startBtn = new Button("Пуск");
//        startBtn.setMinWidth(90);
//        // goto обработка нажатия кнопки
//
//        startBtn.setOnAction(event -> startBtnClicked());
//
//
////        startBtn.setOnAction(event -> {
////            plcCommandController.sendManualHydroControl(checkBoxK1,
////                    (byte)checkBoxK2.selectedProperty().getValue(),
////                    (byte)checkBoxK3.selectedProperty().getValue(),
////                    (byte)checkBoxK4.selectedProperty().getValue(),
////                    (byte)checkBoxKA.selectedProperty().getValue(),
////                    (byte)checkBoxKB.selectedProperty().getValue(),
////                    (byte)checkBoxM1.selectedProperty().getValue(),
////                    (byte)checkBoxM2.selectedProperty().getValue(),
////                    PCV.getValue(), TCV.getValue());
////        });
//
//        /*startBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
//            startBtn.setStyle("-fx-background-color: derive(#373e43, -30%); -fx-text-fill: white; ");
//            startBtn.setText("Стоп");
//            startBtn.setMinWidth(80);
//        });
//        startBtn.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
//            startBtn.setStyle("-fx-background-color: derive(#373e43, 10%); -fx-text-fill: white;");
//            startBtn.setText("Пуск");
//            startBtn.setMinWidth(90);
//        });*/
///*        startBtn.hoverProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue) {
//                startBtn.setStyle("-fx-background-color: derive(#373e43, 13%); -fx-text-fill: white; ");
//            }
//            if (!newValue) {
//                startBtn.setStyle("-fx-background-color: derive(#373e43, 10%); -fx-text-fill: white;");
//            }
//        });
////        startBtn.pressedProperty().addListener((observable, oldValue, newValue) -> {
////            if (newValue) {
////                startBtn.setStyle("-fx-background-color: derive(#373e43, -30%); -fx-text-fill: white; ");
////            }
////            if (!newValue) {
////                startBtn.setStyle("-fx-background-color: derive(#373e43, 30%); -fx-text-fill: white;");
////            }
////        });
//
//        startBtn.setStyle("-fx-background-color: derive(#373e43, 10%); -fx-text-fill: white;");*/
//        Button stopBtn = new Button("Стоп");
//        stopBtn.setMinWidth(90);
//        stopBtn.setOnAction(event -> PLCCommandController.sendStopManualHydroControl());
//
//        VBox btnVbox = new VBox(10, startBtn, stopBtn);
//        btnVbox.getStylesheets().add("/mnemoButtonStyle.css");
//        btnVbox.getStyleClass().add("btnVbox");
//        btnVbox.setAlignment(Pos.CENTER);
//        btnVbox.setPadding(new Insets(5));
////        btnVbox.setStyle("-fx-background-color: #373e43");
//
//        manualControlPanel.setAlignment(Pos.CENTER);
//        manualControlPanel.getChildren().addAll(
//                checkBoxsVbox,
//                limitsVbox,
//                btnVbox
//        );
//
//        manualControlPanel.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                startBtnClicked();
//            }
//        });
//        addIndicator(manualControlPanel, new Coordinate(-0.044, 0.637));
//
////        addIndicator(inputTT5Lb, new Coordinate(592, 400));
////        addIndicator(inputTT3Lb, new Coordinate(951, 308));
////        addIndicator(inputTT4Lb, new Coordinate(944, 13));
//
////        addIndicator(inputPCVLb, new Coordinate(752, 421));
//
////        addIndicator(pitchMin, new Coordinate(40, 300));
////        addIndicator(pitchMax, new Coordinate(40, 350));
//
//        //addIndicator(flowLb, new Coordinate(390, 700));
//        //addIndicator(globalPower, new Coordinate(50, 600));
//        //addIndicator(drivePowerOn, new Coordinate(50, 630));
//        //addIndicator(startIndicator, new Coordinate(50, 660));
//
////        startIndicator.setValue(true);
////        addIndicator(startIndicator, new Coordinate(300, 310));
//
////        rectangularBooleanIndicator.setValue(true);
////        addIndicator(rectangularBooleanIndicator,new Coordinate(1009, 522));
////        addIndicator(inputSpeedLb,new Coordinate(40, 100));
////        addIndicator(inputTorqueLb,new Coordinate(10, 200));
////        addIndicator(inputTorqueMode,new Coordinate(70, 700));
//
////        repositionIndicators();
//
//        this.setPrefSize(PREF_WIDTH, PREF_HIGHT);
//        this.setMaxSize(1722, 1040);
//        this.setMinSize(1598, 965);
////        this.widthProperty().addListener(System.out::println);
////        this.heightProperty().addListener(System.out::println);
//
//        this.setStyle("-fx-background-image: url('" + BACKGROUND_PATH + "');" +
//                "-fx-background-repeat: no-repeat;" +
//                "-fx-background-size: stretch;" +
//                "-fx-background-position: center;");
//
//
//
////        ImageView background = new ImageView(new Image(BACKGROUND_PATH, 1900, 1040, false, true));
////        this.getChildren().add(background);
////        this.getStyleClass().add("indicatorPanel");
////        this.getStylesheets().add(CSS_PATH);
//    }
//
//    public void startBtnClicked() {
//        k1 = (byte) (checkBoxK1.selectedProperty().getValue() ? 1 : 0);
//        k2 = (byte) (checkBoxK2.selectedProperty().getValue() ? 1 : 0);
//        k3 = (byte) (checkBoxK3.selectedProperty().getValue() ? 1 : 0);
//        k4 = (byte) (checkBoxK4.selectedProperty().getValue() ? 1 : 0);
//
//        ka = (byte) (checkBoxKA.selectedProperty().getValue() ? 1 : 0);
//        kb = (byte) (checkBoxKB.selectedProperty().getValue() ? 1 : 0);
//
//        m1 = (byte) (checkBoxM1.selectedProperty().getValue() ? 1 : 0);
//        m2 = (byte) (checkBoxM2.selectedProperty().getValue() ? 1 : 0);
//
//        pcv = PCV.getValue();
//        tcv = TCV.getValue();
//
//        PLCCommandController.sendManualHydroControl(k1, k2, k3, k4, ka, kb, m1, m2, pcv, tcv);
//    }
//
////    public void addCheckBoxListener(CheckBox checkBox) {
////        checkBox.setOnMouseClicked(event -> {
////            boolean isSelected = checkBox.selectedProperty().getValue();
////            checkBox.setSelected(isSelected);
////        });
//////        checkBox.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
//////            checkBox.setSelected(!checkBox.selectedProperty().getValue());
//////        });
////    }
////
////
////    public void addToggleListener(RectangularBooleanIndicator rectangularBooleanIndicator) {
////        rectangularBooleanIndicator.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) ->
////        {
////            if (event.getClickCount() == 2) {
////                System.out.println("double click");
////            }
////        });
////    }
//
//    public void updateValues(Variables variables) {
//        rectangularBooleanIndicatorF1.setValue(variables.DI_HS_HP_FILTER1);
//        rectangularBooleanIndicatorF2.setValue(variables.DI_HS_HP_FILTER2);
//        rectangularBooleanIndicatorF3.setValue(variables.DI_HS_LP_FILTER3);
//        rectangularBooleanIndicatorF4.setValue(variables.DI_HS_LP_FILTER4);
//
//        rectangularBooleanIndicatorKA.setValue(variables.DO_STEER_BRAKE_ON);
//        rectangularBooleanIndicatorKB.setValue(variables.DO_STEER_BRAKE_OFF);
//
//        rectangularBooleanIndicatorK1.setValue(variables.DO_HS_MP_OUT);
//        rectangularBooleanIndicatorK2.setValue(variables.DO_HS_MP_DRAIN);
//        rectangularBooleanIndicatorK3.setValue(variables.DO_HS_OUT_DRAIN);
//        rectangularBooleanIndicatorK4.setValue(variables.DO_HS_MP_HEAT);
//
//
//        rectangularBooleanIndicatorLLS_HP.setValue(variables.DI_HS_OIL_LEVEL1);
//        rectangularBooleanIndicatorLLS_LP.setValue(variables.DI_HS_OIL_LEVEL2);
//
//        inputPT1Lb.setValue(variables.AI_HS_MAIN_PRESS);
//        inputPT2Lb.setValue(variables.AI_HS_OUT_PRESS);
//        inputPT3Lb.setValue(variables.AI_HS_WATER_IN_PRESS);
//        inputPT4Lb.setValue(variables.AI_HS_WATER_OUT_PRESS);
//        inputTT1Lb.setValue(variables.RTD_OIL_DRAIN);
//        inputTT2Lb.setValue(variables.RTD_OIL_INTAKE);
//        inputTT3Lb.setValue(variables.RTD_WATER_IN);
//        inputTT4Lb.setValue(variables.RTD_WATER_OUT);
//    }
//
////    public void setSizeImgBgc(int width, int height) {
////        if (super.backgroundImage != null){
////            super.backgroundImage.setFitWidth(width);
////            super.backgroundImage.setFitHeight(height);
////        }
////    }
//
////    public void onVariablesNewState(UIvariables variables){
////        this.variables = variables;
////        //inputSpeedLb.setValue(variables.analog_input_1);
////        //inputSpeedLb.setValue(variables.FDI_INPUT_ENCODER_SPEED);
////    }
//}
