package ru.volgatest.brake.MainPane.Widgets;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import ru.volgatest.brake.PLC.PLCCommandController;
import ru.volgatest.brake.Widgets.LimitedFloatField;

import java.nio.ByteBuffer;
import java.util.Arrays;


public class DynamicLoading extends MovingPane {

    final static int SPACING = 10;

//    Разгон/Торможение	byte	двигатель выключен = 0 разгон = 1 торможение = 2
//    Скорость движения	float	км/ч
//    Момент инерции	float
//    Давление тормозного цилиндра	float
//    Скорость нарастания давления	float
//    Скорость потока воздуха	float

    public Label titleRotationAndBraking = new Label("Разгон/Торможение");

    public Label rotationLb = new Label("Разгон");
    public Label brakingLb = new Label("Торможение");
    public boolean isRotation;
    public boolean isBraking;



    public LimitedFloatField speedMovingField = new LimitedFloatField("Скорость движения", "km/h", 0, 500, 0);
    public LimitedFloatField inertiaMomentField = new LimitedFloatField("Момент инерции", "kg*m^2", 0, 500, 0);
    public LimitedFloatField pressureBrakeCylinderField = new LimitedFloatField("Давление тормозного цилиндра", "bar", 0, 500, 0);
    public LimitedFloatField speedIncreasingPressureField = new LimitedFloatField("Скорость нарастания давления", "bar/s", 0, 500, 0);
    public LimitedFloatField airflowSpeedField = new LimitedFloatField("Скорость потока воздуха", "m/s", 0, 500, 0);



    HBox hBoxRotationAndBraking = new HBox(SPACING);

//    public Label titleRotation = new Label("Вращение");

//    public LimitedIntegerField speedField = new LimitedIntegerField("Скорость", "rpm", 0, 500, 0);
//    public LimitedIntegerField boostField = new LimitedIntegerField("Ускорение", "rpm/s", 0, 500, 0);
//    public LimitedFloatField settingSpeedField = new LimitedFloatField("Задание скорости", "rpm", 0, 500, 0);

    public Button startBtn = new Button("Старт");
    public Button stopBtn = new Button("Стоп");
    public Button freewheelBtn = new Button("Выбег");

//    public Label titleBraking = new Label("Торможение");

//    public LimitedIntegerField pressure = new LimitedIntegerField("Давление", "bar", 0, 500, 0);
//    public LimitedIntegerField speedIncreasing = new LimitedIntegerField("Скорость нарастания", "bar/s", 0, 500, 0);
//    public LimitedFloatField brakingTimeField = new LimitedFloatField("Время торможения", "s", 0, 500, 0);
//    public LimitedFloatField brakeSpeedField = new LimitedFloatField("Скорость торможения", "rpm", 0, 500, 0);
//    public LimitedFloatField brakingDistanceField = new LimitedFloatField("Расстояние торможения", "m", 0, 500, 0);
//
//    public Button startBraking = new Button("Старт");
//    public Button stopBraking = new Button("Стоп");


    HBox hBoxRotation;
    VBox rootRotation;

    String styleOn = "-fx-background-color: rgba(0,128,0,0.99); -fx-border-color: derive(-fx-base, -35%); -fx-border-width: 1px; -fx-padding: 3px; -fx-min-width: 130px; -fx-alignment: center;-fx-font-size: 12px; -fx-font-weight: bold;";
    String styleOff = "-fx-background-color: derive(-fx-base, 35%); -fx-border-color: derive(-fx-base, -35%); -fx-border-width: 1px; -fx-padding: 3px; -fx-min-width: 130px; -fx-alignment: center;-fx-font-size: 12px; -fx-font-weight: bold;";

    public DynamicLoading() {


        //region test
        isRotation = true;
        isBraking = false;
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hBoxRotationAndBraking = new HBox(SPACING, rotationLb,spacer, brakingLb);
        hBoxRotationAndBraking.setStyle("-fx-border-width: 1 1 1 1; -fx-border-color: derive(-fx-base, -20%); -fx-padding: 5px;");
//        rotationLb.setStyle("-fx-background-color: rgba(0,128,0,0.99); -fx-border-color: derive(-fx-base, -35%); -fx-border-width: 1px; -fx-padding: 3px; -fx-min-width: 130px; -fx-alignment: center;-fx-font-size: 12px;");
//        brakingLb.setStyle("-fx-background-color: derive(-fx-base, 35%);-fx-border-color: derive(-fx-base, -35%); -fx-border-width: 1px; -fx-padding: 3px;-fx-min-width: 130px;-fx-alignment: center;-fx-font-size: 12px;");
        rotationLb.setStyle(styleOn);
        brakingLb.setStyle(styleOff);

        VBox rightCol = new VBox(SPACING, hBoxRotationAndBraking, speedMovingField, inertiaMomentField, pressureBrakeCylinderField, speedIncreasingPressureField, airflowSpeedField);
        rightCol.setPadding(new Insets(5));


        //endregion


//        settingSpeedField.textField.setEditable(false);
//        brakingTimeField.textField.setEditable(false);
//        brakeSpeedField.textField.setEditable(false);
//        brakingDistanceField.textField.setEditable(false);

        this.setTitle("Динамическое нагружение");

//        titleRotation.setMaxWidth(Double.MAX_VALUE);
//        VBox.setVgrow(titleRotation, Priority.ALWAYS);
        titleRotationAndBraking.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(titleRotationAndBraking, Priority.ALWAYS);

//        titleBraking.setMaxWidth(Double.MAX_VALUE);
//        VBox.setVgrow(titleBraking, Priority.ALWAYS);

        Separator separatorVertical = new Separator();
        separatorVertical.setOrientation(Orientation.VERTICAL);

//        VBox rightCol = new VBox(SPACING, speedField, boostField, settingSpeedField);
//        rightCol.setPadding(new Insets(5));
        VBox leftCol = new VBox(5, startBtn, stopBtn, freewheelBtn);
        leftCol.setPadding(new Insets(5));

        hBoxRotation = new HBox(rightCol, separatorVertical, leftCol);
//        rootRotation = new VBox(titleRotation, hBoxRotation);
        rootRotation = new VBox(titleRotationAndBraking, hBoxRotation);

        this.getChildren().add(rootRotation);

        Separator separatorVerticalBr = new Separator();
        separatorVerticalBr.setOrientation(Orientation.VERTICAL);

//        VBox rightColBraking = new VBox(SPACING, pressure, speedIncreasing, brakingTimeField, brakeSpeedField, brakingDistanceField);
//        rightColBraking.setPadding(new Insets(5));

//        VBox leftColBraking = new VBox(5, startBraking, stopBraking);
//        leftColBraking.setPadding(new Insets(5));

//        hBoxBraking = new HBox(rightColBraking, separatorVerticalBr, leftColBraking);
//        rootBraking = new VBox(titleBraking, hBoxBraking);

//        this.getChildren().add(rootBraking);

        hBoxRotation.setStyle("-fx-border-width: 3px; -fx-border-color: derive(-fx-base, -20%);");
//        hBoxBraking.setStyle("-fx-border-width: 3px; -fx-border-color: derive(-fx-base, -20%);");

        setSizeNode();
        setStyleNode();




//        speedField.widthProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("Old value: " + oldValue);
//            System.out.println("New value: " + newValue);
//        });
        this.setSpacing(10);
//        this.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, -30%);-fx-padding: 5px;");

        this.setLayoutX(472);
        this.setLayoutY(18);

        controller();

    }

    private void setSizeNode() {
        int nameWidth = 200;
        int buttonHeight = 20;
        int buttonWidth = 50;

//        speedField.setNameWidth(nameWidth);
//        boostField.setNameWidth(nameWidth);
//        settingSpeedField.setNameWidth(nameWidth);

//        pressure.setNameWidth(nameWidth);
//        speedIncreasing.setNameWidth(nameWidth);
//        brakingTimeField.setNameWidth(nameWidth);
//        brakeSpeedField.setNameWidth(nameWidth);
//        brakingDistanceField.setNameWidth(nameWidth);


//        startBraking.setMinSize(buttonWidth, buttonHeight);
//        stopBraking.setMinSize(buttonWidth, buttonHeight);

        speedMovingField.setNameWidth(nameWidth);
        inertiaMomentField.setNameWidth(nameWidth);
        pressureBrakeCylinderField.setNameWidth(nameWidth);
        speedIncreasingPressureField.setNameWidth(nameWidth);
        airflowSpeedField.setNameWidth(nameWidth);

        startBtn.setMinSize(buttonWidth, buttonHeight);
        stopBtn.setMinSize(buttonWidth, buttonHeight);
        freewheelBtn.setMinSize(buttonWidth, buttonHeight);

    }

    private void setStyleNode() {

//        speedField.setStyle("-fx-border-width: 2px; -fx-border-color: red;");


        this.title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;-fx-alignment: center; ");

        titleRotationAndBraking.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: derive(-fx-base, -20%);-fx-alignment: center; -fx-padding: 5 0 5 0;");
//        titleRotation.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: derive(-fx-base, -20%);-fx-alignment: center; -fx-padding: 5 0 5 0;");
//        titleBraking.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;-fx-background-color: derive(-fx-base, -20%); -fx-alignment: center; -fx-padding: 5 0 5 0;");
//
//        hBoxRotation.setStyle(" -fx-border-width: 0 1 1 1; -fx-border-color: derive(-fx-base, -20%);");
//        hBoxBraking.setStyle(" -fx-border-width: 0 1 1 1; -fx-border-color: derive(-fx-base, -20%);");


    }

    // Обработчик событий и логике
    public void controller() {

        //Клик по лейблу и выбор режима
        rotationLb.setOnMouseClicked(event -> { //Клик по
//            rotationLb.setStyle("-fx-background-color: red;-fx-border-color: blue; -fx-border-width: 2px;");
//            rotationLb.setStyle("-fx-background-color: rgba(0,128,0,0.99); -fx-border-color: derive(-fx-base, -35%); -fx-border-width: 1px; -fx-padding: 3px;-fx-min-width: 100px;-fx-alignment: center;-fx-font-size: 12px;");
//            brakingLb.setStyle("-fx-background-color: derive(-fx-base, 35%);-fx-border-color: derive(-fx-base, -35%); -fx-border-width: 1px; -fx-padding: 3px;-fx-min-width: 100px;-fx-alignment: center;-fx-font-size: 12px;");
//
            rotationLb.setStyle(styleOn);
            brakingLb.setStyle(styleOff);

//            brakingLb.setStyle("-fx-background-color: derive(-fx-base, 35%);-fx-border-color: derive(-fx-base, 35%); -fx-border-width: 1px;");
            isRotation = true;
            isBraking = false;
            System.out.println("Rotation : "+isRotation);
            System.out.println("Braking : "+isBraking);
        });

        //Клик по лейблу и выбор режима
        brakingLb.setOnMouseClicked(event -> {
            brakingLb.setStyle(styleOn);
            rotationLb.setStyle(styleOff);
//            brakingLb.setStyle("-fx-background-color: rgba(0,128,0,0.99); -fx-border-color: derive(-fx-base, -35%); -fx-border-width: 1px; -fx-padding: 3px;-fx-min-width: 100px;-fx-alignment: center;-fx-font-size: 12px;");
//            rotationLb.setStyle("-fx-background-color: derive(-fx-base, 35%);-fx-border-color: derive(-fx-base, -35%); -fx-border-width: 1px; -fx-padding: 3px;-fx-min-width: 100px;-fx-alignment: center; -fx-font-size: 12px;");
//            brakingLb.setStyle("-fx-background-color: red;-fx-border-color: blue; -fx-border-width: 2px;");
//            rotationLb.setStyle("-fx-background-color: derive(-fx-base, 35%);-fx-border-color: derive(-fx-base, 35%); -fx-border-width: 1px;");
            isBraking =  true;
            isRotation = false;
            System.out.println("Braking : "+isBraking);
            System.out.println("Rotation : "+isRotation);
        });


        startBtn.setOnAction(event -> {
            ByteBuffer buffer = ByteBuffer.allocate(21);
            if(isRotation){
                buffer.put((byte) 1);
                System.out.println("Rotation " + (byte) 1);
            }else if(isBraking){
                buffer.put((byte) 2);
                System.out.println("Braking " + (byte) 2);
            }else{
                buffer.put((byte) 0);
                System.out.println("Engine stop " + (byte) 0);
            }
            buffer.putFloat(speedMovingField.value.getValue());
            buffer.putFloat(inertiaMomentField.value.getValue());
            buffer.putFloat(pressureBrakeCylinderField.value.getValue());
            buffer.putFloat(speedIncreasingPressureField.value.getValue());
            buffer.putFloat(airflowSpeedField.value.getValue());
            PLCCommandController.manualMode(buffer);
            System.out.println(Arrays.toString(buffer.array()));
            System.out.println("Start Ok");
        });

        stopBtn.setOnAction(event -> {
            PLCCommandController.stopManualMode();
            System.out.println("Stop manual mode - OK");
        });


    }

}


//        Separator separatorHorizontal = new Separator();
//        separatorHorizontal.setOrientation(Orientation.HORIZONTAL);
//
//        Separator separatorVertical = new Separator();
//        separatorVertical.setOrientation(Orientation.VERTICAL);