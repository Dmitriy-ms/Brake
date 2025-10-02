package ru.volgatest.brake;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Section;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MedusaGaugeTest extends Application {
    @Override
    public void start(Stage stage) {
        Gauge gauge = new Gauge();
        gauge.setMinValue(0);
        gauge.setMaxValue(100);
        gauge.setValue(10);               // начальное значение
        gauge.setUnit("°C");
        gauge.setTitle("Температура");
        gauge.setAnimated(true);          // включить анимацию
        gauge.setAutoScale(true);

        Button button = new Button("Поднять до 50°C");
        button.setOnAction(e -> gauge.setValue(50));  // ← плавное обновление

        Button button1 = new Button("Поднять до 100°C");
        button1.setOnAction(e -> gauge.setValue(100));

        Button button2 = new Button("Опустить до 0°C");
        button2.setOnAction(e -> gauge.setValue(0));

        Section redZone = new Section(80, 100, Color.RED);
        gauge.getSections().add(redZone);
        gauge.setSectionsVisible(true); // <-- Важно!

        VBox root = new VBox(20, gauge, button, button1, button2);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 300, 350);
        scene.getStylesheets().add("/main.css");
        stage.setScene(scene);
        stage.setTitle("Gauge + Button");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
