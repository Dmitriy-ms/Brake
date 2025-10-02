package ru.volgatest.brake.InsideWindows.SubCycle;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.volgatest.brake.Widgets.LimitedFloatField;
import ru.volgatest.brake.Widgets.LimitedIntegerField;

public class SubCycleWindow extends VBox {
    Stage stage = new Stage();

    SubCycleModel subCycleModel;

    Label title = new Label("Испытание в режиме прерывистого торможения");


    Label temperatureCheck = new Label("Контроль температуры каждый цикл");
    HBox temperatureBox = new HBox(10, temperatureCheck);

    Label preheating = new Label("Преднагрев");
    HBox preheatingBox = new HBox(10, preheating);

    Label precooling = new Label("Предохлаждение");
    HBox precoolingBox = new HBox(10, precooling);


    Button okBtn = new Button("OK");
    Button cancelBtn = new Button("Отмена");


//    LimitedIntegerField tMin = new LimitedIntegerField("Tmin", "°C", 0, 500, 0);
//    LimitedIntegerField tMax = new LimitedIntegerField("Tmax", "°C", 0, 500, 0);
//
//    LimitedFloatField initialSpeed = new LimitedFloatField("Начальная скорость", "km/h", 0, 500, 0);
//    LimitedFloatField finalSpeed = new LimitedFloatField("Конечная скорость", "km/h", 0, 500, 0);
//    LimitedFloatField airFlowSpeed = new LimitedFloatField("Скорость потока воздуха", "m/s", 0, 500, 0);
//
//    LimitedIntegerField cycleDuration = new LimitedIntegerField("Длительность цикла", "s", 0, 500, 0);
//    LimitedIntegerField cycleCount = new LimitedIntegerField("Количество циклов", "", 0, 500, 0);

    public SubCycleWindow(SubCycleModel subCycleModel) {
        this.subCycleModel = subCycleModel;

        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 350px; -fx-min-height: 70px; -fx-alignment: center;");
        title.setTextAlignment(TextAlignment.CENTER);

        temperatureCheck.setStyle("-fx-text-fill: black;");
        preheating.setStyle("-fx-text-fill: black;");
        precooling.setStyle("-fx-text-fill: black;");
        HBox heatAndCoolHBox = new HBox(10, preheatingBox, precoolingBox);
        refreshView();

        LimitedIntegerField tMin = new LimitedIntegerField("Tmin", "°C", 0, 500, subCycleModel.tMin);
        tMin.textField.textProperty().addListener(value -> subCycleModel.tMin = tMin.getValue());

        LimitedIntegerField tMax = new LimitedIntegerField("Tmax", "°C", 0, 500, subCycleModel.tMax);
        tMax.textField.textProperty().addListener(value -> subCycleModel.tMax = tMax.getValue());

        LimitedFloatField initialSpeed = new LimitedFloatField("Начальная скорость", "km/h", 0, 500, (float) subCycleModel.initialSpeed);
        initialSpeed.textField.textProperty().addListener(value -> subCycleModel.initialSpeed = initialSpeed.getValue());
        LimitedFloatField finalSpeed = new LimitedFloatField("Конечная скорость", "km/h", 0, 500, (float) subCycleModel.finalSpeed);
        finalSpeed.textField.textProperty().addListener(value -> subCycleModel.finalSpeed = finalSpeed.getValue());
        LimitedFloatField airFlowSpeed = new LimitedFloatField("Скорость потока воздуха", "m/s", 0, 500, (float) subCycleModel.airFlowSpeed);
        airFlowSpeed.textField.textProperty().addListener(value -> subCycleModel.airFlowSpeed = airFlowSpeed.getValue());

        LimitedIntegerField cycleDuration = new LimitedIntegerField("Длительность цикла", "s", 0, 500, subCycleModel.cycleDuration);
        cycleDuration.textField.textProperty().addListener(value -> subCycleModel.cycleDuration = cycleDuration.getValue());
        LimitedIntegerField cycleRepeat = new LimitedIntegerField("Количество циклов", "", 0, 500, subCycleModel.repeat);
        cycleRepeat.textField.textProperty().addListener(value -> subCycleModel.repeat = cycleRepeat.getValue());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttonsHbox = new HBox(10, spacer, okBtn, cancelBtn);
        buttonsHbox.setMaxWidth(Double.MAX_VALUE);

        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(title, temperatureBox, heatAndCoolHBox, tMin, tMax, initialSpeed, finalSpeed, airFlowSpeed, cycleDuration, cycleRepeat, buttonsHbox);

        SubCycleController subCycleController = new SubCycleController(this);

        Scene scene = new Scene(this, 370, 450);
        scene.getStylesheets().add("/main.css");
        stage.setScene(scene);
        stage.setTitle(subCycleModel.name);
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется
    }

    public void refreshView() {
        if (!subCycleModel.isTemperatureCheckedEachCycle) {
            temperatureCheck.setText("Контроль температуры перед началом испытания");
            temperatureBox.setStyle("-fx-min-width: 310px;-fx-max-width: 305px;-fx-border-color: derive(-fx-base, -20%); -fx-border-width: 1px; -fx-background-color: yellow; ; -fx-alignment: center; -fx-padding: 10px;");
        } else {
            temperatureCheck.setText("Контроль температуры каждый цикл");
            temperatureBox.setStyle("-fx-min-width: 310px;-fx-max-width: 305px;-fx-border-color: derive(-fx-base, -20%); -fx-border-width: 1px; -fx-background-color: #8ce88c;-fx-alignment: center; -fx-padding: 10px;");
        }
        if (!subCycleModel.isPreheatingEnabled) {
            preheating.setText("Нет преднагрев");
            preheatingBox.setStyle("-fx-min-width: 150px;-fx-max-width: 150px;-fx-border-color: derive(-fx-base, -20%); -fx-border-width: 1px; -fx-background-color: lightgray; -fx-alignment: center; -fx-padding: 10px;");
        } else {
            preheating.setText("Преднагрев");
            preheatingBox.setStyle("-fx-min-width: 150px;-fx-max-width: 150px;-fx-border-color: derive(-fx-base, -20%); -fx-border-width: 1px; -fx-background-color: #f46c6c;-fx-alignment: center; -fx-padding: 10px;");
        }
        if (!subCycleModel.isPrecoolingEnabled) {
            precooling.setText("Нет предохлаждение");
            precoolingBox.setStyle("-fx-min-width: 150px;-fx-max-width: 150px;-fx-border-color: derive(-fx-base, -20%); -fx-border-width: 1px; -fx-background-color: lightgray; -fx-alignment: center; -fx-padding: 10px;");
        } else {
            precooling.setText("Предохлаждение");
            precoolingBox.setStyle("-fx-min-width: 150px;-fx-max-width: 150px;-fx-border-color: derive(-fx-base, -20%); -fx-border-width: 1px; -fx-background-color: #77c3f4;-fx-alignment: center; -fx-padding: 10px;");
        }
    }
}
