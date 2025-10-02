package ru.volgatest.brake.InsideWindows.Limitations;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.volgatest.brake.Widgets.LimitedFloatField;

public class Limits extends VBox {
    Stage stage = new Stage();
    Label title = new Label("Ограничения");
    LimitedFloatField rotationSpeed = new LimitedFloatField("Скорость вращения:", "RPM", 0, 10000, 0);
    LimitedFloatField torque = new LimitedFloatField("Момент силы:", "N.m", 0, 10000, 0);
    LimitedFloatField hydraulicPressure = new LimitedFloatField("Гидравлическое давление:", "bar", 0, 10000, 0);
    LimitedFloatField pneumaticPressure = new LimitedFloatField("Пневматическое давление:", "bar", 0, 10000, 0);
    LimitedFloatField temp = new LimitedFloatField("Температура:", "°C", 0, 10000, 0);

    Button okBtn = new Button("OK");
    Button cancelBtn = new Button("Отмена");

    public Limits() {
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 350px; -fx-alignment: center;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttonBox = new HBox(10,spacer, okBtn, cancelBtn);
        buttonBox.setMaxWidth(Double.MAX_VALUE);

        cancelBtn.setOnMouseClicked(event -> stage.close());

        this.setPadding(new Insets(5));
        this.setSpacing(10);
        this.getChildren().addAll(title, rotationSpeed, torque, hydraulicPressure, pneumaticPressure, temp,buttonBox);
        this.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(this, 400, 270);
        scene.getStylesheets().add("/main.css");
        stage.setTitle("Ограничения");
        stage.setScene(scene);
        stage.show();
    }
}
