///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ru.volgatest.brake.Widgets;
//
//
//import com.example.UI.temp.Variables;
//import com.example.Widgets.Indicators.MarkBooleanIndicator;
//import com.example.Widgets.Indicators.NumericalIndicator;
//import javafx.scene.layout.VBox;
//
///**
// * @author VTARMN013
// */
//public class ValuesIndicatorPanel extends IndicatorPanel {
//    public static String BACKGROUND_PATH = "/service_stand.png";
//    //    public static String CSS_PATH = "/UI/indicators_panel.css";
//    static public final int PREF_WIDTH = 1920;
//    static public final int PREF_HIGHT = 1080;
//
////    RectangularBooleanIndicator rectangularBooleanIndicator = new RectangularBooleanIndicator(23, 23, 45);
//
////    NumericalIndicator inputSpeedLb = new NumericalIndicator("Входная скорость", "rpm");
////    NumericalIndicator inputTorqueLb = new NumericalIndicator("Входной момент", "Nm");
////    MarkBooleanIndicator inputTorqueMode = new MarkBooleanIndicator("Режим момента", true);
//
//
//    public MarkBooleanIndicator circuitBreakerTripControl = new MarkBooleanIndicator("Контроль срабатывания автоматических выключателей", false);
//    public MarkBooleanIndicator powerNetworkParamControl = new MarkBooleanIndicator("Контроль параметров питающей сети", false);
//    public MarkBooleanIndicator controlPowerSupplyCheck = new MarkBooleanIndicator("Контроль источника питания системы управления", false);
//    public MarkBooleanIndicator emergencyStopRelayCheck = new MarkBooleanIndicator("Контроль реле аварийного останова", false);
//    public MarkBooleanIndicator perimeterSafetyRelayCheck = new MarkBooleanIndicator("Контроль реле безопасности периметра", false);
//    public MarkBooleanIndicator cabinetEmergencyStopPressed = new MarkBooleanIndicator("Нажата кнопка аварийного останова на шкафу управления", false);
//    public MarkBooleanIndicator gatePanelEmergencyStopPressed = new MarkBooleanIndicator("Нажата кнопка аварийного останова на пульте ворот", false);
//    public MarkBooleanIndicator doorPanelEmergencyStopPressed = new MarkBooleanIndicator("Нажата кнопка аварийного останова на пульте двери", false);
//    public MarkBooleanIndicator operatorPanelEmergencyStopPressed = new MarkBooleanIndicator("Нажата кнопка аварийного останова на пульте оператора", false);
//    public MarkBooleanIndicator steeringRackServoDrive = new MarkBooleanIndicator("ПЧ серводвигателя привода рулевой рейки", false);
//    public MarkBooleanIndicator leftKnuckleStepperDriver = new MarkBooleanIndicator("Драйвер шагового электродвигателя ротора левой стойки поворотного кулака", false);
//    public MarkBooleanIndicator rightKnuckleStepperDriver = new MarkBooleanIndicator("Драйвер шагового электродвигателя ротора правой стойки поворотного кулака", false);
//    public MarkBooleanIndicator hydraulicPumpSoftStarter = new MarkBooleanIndicator("УПП электродвигателя насоса гидростанции", false);
//    public NumericalIndicator   ambientTempSensor = new NumericalIndicator("Датчик температуры окружающей среды (воздуха)", "");
//
//
//    /**
//     * Variables
//     */
//
//    public ValuesIndicatorPanel() {
//
//        super(true, BACKGROUND_PATH);
//        int LB_WIDTH = 450;
//        int LB_HEIGHT = 25;
//        circuitBreakerTripControl.setSize(LB_WIDTH, LB_HEIGHT);
//        powerNetworkParamControl.setSize(LB_WIDTH, LB_HEIGHT);
//        controlPowerSupplyCheck.setSize(LB_WIDTH, LB_HEIGHT);
//        emergencyStopRelayCheck.setSize(LB_WIDTH, LB_HEIGHT);
//        perimeterSafetyRelayCheck.setSize(LB_WIDTH, LB_HEIGHT);
//        cabinetEmergencyStopPressed.setSize(LB_WIDTH, LB_HEIGHT);
//        gatePanelEmergencyStopPressed.setSize(LB_WIDTH, LB_HEIGHT);
//        doorPanelEmergencyStopPressed.setSize(LB_WIDTH, LB_HEIGHT);
//        operatorPanelEmergencyStopPressed.setSize(LB_WIDTH, LB_HEIGHT);
//        steeringRackServoDrive.setSize(LB_WIDTH, LB_HEIGHT);
//        leftKnuckleStepperDriver.setSize(LB_WIDTH, LB_HEIGHT);
//        rightKnuckleStepperDriver.setSize(LB_WIDTH, LB_HEIGHT);
//        hydraulicPumpSoftStarter.setSize(LB_WIDTH, LB_HEIGHT);
//        ambientTempSensor.setSize(LB_WIDTH, LB_HEIGHT);
//        ambientTempSensor.nameLb.setStyle("-fx-font-size:12px");
//
////        addIndicator(pitchMin, new Coordinate(40, 300));
////        addIndicator(pitchMax, new Coordinate(40, 350));
//
//        //addIndicator(flowLb, new Coordinate(390, 700));
//        //addIndicator(globalPower, new Coordinate(50, 600));
//        //addIndicator(drivePowerOn, new Coordinate(50, 630));
//        //addIndicator(startIndicator, new Coordinate(50, 660));
//        VBox rootVBox = new VBox(20);
////        rectangularBooleanIndicator.setValue(true);
////        addIndicator(rectangularBooleanIndicator,new Coordinate(0.3, 0.3));
////        addIndicator(inputSpeedLb,new Coordinate(0.4, 0.4));
////        addIndicator(inputTorqueLb,new Coordinate(0.5, 0.5));
////        addIndicator(inputTorqueMode,new Coordinate(0.6, 0.6));
//
//        rootVBox.getChildren().addAll(
//                ambientTempSensor,
//                circuitBreakerTripControl,
//                powerNetworkParamControl,
//                controlPowerSupplyCheck,
//                emergencyStopRelayCheck,
//                perimeterSafetyRelayCheck,
//                cabinetEmergencyStopPressed,
//                gatePanelEmergencyStopPressed,
//                doorPanelEmergencyStopPressed,
//                operatorPanelEmergencyStopPressed,
//                steeringRackServoDrive,
//                leftKnuckleStepperDriver,
//                rightKnuckleStepperDriver,
//                hydraulicPumpSoftStarter
//        );
//
//        addIndicator(rootVBox, new Coordinate(-0.521, -0.098));
//
//
//        this.setPrefSize(PREF_WIDTH, PREF_HIGHT);
//        this.setMaxSize(1208, 767);
//        this.setMinSize(1208, 767);
//        this.getStyleClass().add("indicatorPanel");
//
//        this.setStyle("-fx-background-image: url('" + BACKGROUND_PATH + "');" +
//                "-fx-background-position: right !important;" +
//                "-fx-background-repeat:no-repeat;" +
//                "-fx-background-size: stretch;");
////        this.getStylesheets().add(CSS_PATH);
//    }
//
//    public void updateValues(Variables variables){
//        circuitBreakerTripControl.setValue(variables.DI_SWITCH_CTRL);
//        powerNetworkParamControl.setValue(variables.DI_POWER_OK);
//        controlPowerSupplyCheck.setValue(variables.DI_MAIN_DCPOWER_OK);
//        emergencyStopRelayCheck.setValue(variables.DI_EMERGENCY_OK);
//        perimeterSafetyRelayCheck.setValue(variables.DI_PERIMETER_OK);
//        cabinetEmergencyStopPressed.setValue(variables.DI_NOTAUS_ELCAB);
//        gatePanelEmergencyStopPressed.setValue(variables.DI_NOTAUS_GATE);
//        doorPanelEmergencyStopPressed.setValue(variables.DI_NOTAUS_DOOR);
//        operatorPanelEmergencyStopPressed.setValue(variables.DI_NOTAUS_OPERATOR);
//        steeringRackServoDrive.setValue(variables.DI_STEERDRIVE_READY);
//        leftKnuckleStepperDriver.setValue(variables.DI_STEPDRIVE_LEFT_FAULT);
//        rightKnuckleStepperDriver.setValue(variables.DI_STEPDRIVE_RIGHT_FAULT);
//        hydraulicPumpSoftStarter.setValue(variables.DI_HS_SS_READY);
//        ambientTempSensor.setValue(variables.RTD_AIR);
//    }
//
//
//
////    public void onVariablesNewState(UIvariables variables){
////        this.variables = variables;
////        //inputSpeedLb.setValue(variables.analog_input_1);
////        //inputSpeedLb.setValue(variables.FDI_INPUT_ENCODER_SPEED);
////    }
//}
