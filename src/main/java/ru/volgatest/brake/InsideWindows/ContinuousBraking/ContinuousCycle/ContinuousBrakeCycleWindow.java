package ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousCycle;

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
import ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousBrakingModel;
import ru.volgatest.brake.Widgets.LimitedFloatField;
import ru.volgatest.brake.Widgets.LimitedIntegerField;

public class ContinuousBrakeCycleWindow extends VBox {
    Stage stage = new Stage();

    ContinuousBrakingModel continuousBrakingModel;

    Label title = new Label("Испытание в режиме непрерывного торможения");


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

    LimitedIntegerField tMin;
    LimitedIntegerField tMax;
    LimitedFloatField slope;
    LimitedFloatField distance;
    LimitedFloatField speed;
    LimitedFloatField airFlowRate;


    public ContinuousBrakeCycleWindow(ContinuousBrakingModel continuousBrakingModel) {
//        this.continuousBrakingModel = continuousBrakingModel;

        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 350px; -fx-min-height: 70px; -fx-alignment: center;");
        title.setTextAlignment(TextAlignment.CENTER);

        tMin = new LimitedIntegerField("Tmin", "°C", 0, 500, continuousBrakingModel.tMin);
        tMin.value.addListener(value -> continuousBrakingModel.tMin = tMin.getValue());

        tMax = new LimitedIntegerField("Tmax", "°C", 0, 500, continuousBrakingModel.tMax);
        tMax.value.addListener(value -> continuousBrakingModel.tMax = tMax.getValue());

        slope = new LimitedFloatField("Уклон", "%", 0, 500, continuousBrakingModel.slope);
        slope.value.addListener(value -> continuousBrakingModel.slope = slope.getValue());
        distance = new LimitedFloatField("Расстояние ", "m", 0, 500, continuousBrakingModel.distance);
        distance.value.addListener(value -> continuousBrakingModel.distance = distance.getValue());
        speed = new LimitedFloatField("Скорость", "km/h", 0, 500, continuousBrakingModel.speed);
        speed.value.addListener(value -> continuousBrakingModel.speed = speed.getValue());
        airFlowRate = new LimitedFloatField("Скорость потока воздуха", "m/s", 0, 500, continuousBrakingModel.airFlowRate);
        airFlowRate.value.addListener(value -> continuousBrakingModel.airFlowRate = airFlowRate.getValue());


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttonsHbox = new HBox(10, spacer, okBtn, cancelBtn);
        buttonsHbox.setMaxWidth(Double.MAX_VALUE);

        buttonsController();

        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(title, tMin, tMax, slope, distance, speed, airFlowRate, buttonsHbox);

        Scene scene = new Scene(this, 370, 450);
        scene.getStylesheets().add("/main.css");
        stage.setScene(scene);
        stage.setTitle(continuousBrakingModel.name);
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется
    }

    void buttonsController() {
        okBtn.setOnAction(event -> {

            stage.close();
        });
        cancelBtn.setOnAction(event -> {
            stage.close();
        });
    }


}
