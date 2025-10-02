package ru.volgatest.brake;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Test101Window extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Свойства 'Test101'");

        // Заголовок
        Label title = new Label("Испытание в режиме\nпрерывистого торможения");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.BLUE);
        title.setAlignment(Pos.CENTER);
        title.setTextAlignment(TextAlignment.CENTER);

        // Верхний блок с чекбоксом и кнопками-переключателями
        CheckBox tempControlCheck = new CheckBox("Контроль температуры каждый цикл");
        tempControlCheck.setSelected(true);
        tempControlCheck.setStyle("-fx-background-color: #90EE90; -fx-font-size: 16px; -fx-padding: 8 0 8 0;");

        ToggleGroup preGroup = new ToggleGroup();
        ToggleButton preHeatBtn = new ToggleButton("Преднагрев");
        preHeatBtn.setToggleGroup(preGroup);
        preHeatBtn.setStyle("-fx-background-color: #FF7F7F; -fx-font-size: 16px;");
        ToggleButton preCoolBtn = new ToggleButton("Предохлаждение");
        preCoolBtn.setToggleGroup(preGroup);
        preCoolBtn.setStyle("-fx-background-color: #B0FFFF; -fx-font-size: 16px;");

        HBox preBox = new HBox(10, preHeatBtn, preCoolBtn);
        preBox.setAlignment(Pos.CENTER);

        // Tmin/Tmax
        Label tminLabel = new Label("Tmin");
        TextField tminField = new TextField("50");
        tminField.setPrefWidth(40);
        Label tminDeg = new Label("°C");

        Label tmaxLabel = new Label("Tmax");
        TextField tmaxField = new TextField("55");
        tmaxField.setPrefWidth(40);
        Label tmaxDeg = new Label("°C");

        HBox tminBox = new HBox(5, tminLabel, tminField, tminDeg);
        tminBox.setAlignment(Pos.CENTER_LEFT);
        HBox tmaxBox = new HBox(5, tmaxLabel, tmaxField, tmaxDeg);
        tmaxBox.setAlignment(Pos.CENTER_LEFT);

        HBox tBox = new HBox(20, tminBox, tmaxBox);
        tBox.setAlignment(Pos.CENTER);

        VBox tempBox = new VBox(10, tempControlCheck, preBox, tBox);
        tempBox.setPadding(new Insets(10));
        tempBox.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        // Основные параметры (скорости и поток воздуха)
        GridPane paramGrid = new GridPane();
        paramGrid.setHgap(10);
        paramGrid.setVgap(10);
        paramGrid.setPadding(new Insets(10));

        Label speedStartLabel = new Label("Начальная скорость");
        TextField speedStartField = new TextField("20");
        Label speedStartUnit = new Label("km/h");

        Label speedEndLabel = new Label("Конечная скорость");
        TextField speedEndField = new TextField("5");
        Label speedEndUnit = new Label("km/h");

        Label airFlowLabel = new Label("Скорость потока воздуха");
        TextField airFlowField = new TextField("10");
        Label airFlowUnit = new Label("m/s");

        paramGrid.add(speedStartLabel, 0, 0);
        paramGrid.add(speedStartField, 1, 0);
        paramGrid.add(speedStartUnit, 2, 0);

        paramGrid.add(speedEndLabel, 0, 1);
        paramGrid.add(speedEndField, 1, 1);
        paramGrid.add(speedEndUnit, 2, 1);

        paramGrid.add(airFlowLabel, 0, 2);
        paramGrid.add(airFlowField, 1, 2);
        paramGrid.add(airFlowUnit, 2, 2);

        paramGrid.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        // Длительность цикла и количество циклов
        GridPane cycleGrid = new GridPane();
        cycleGrid.setHgap(10);
        cycleGrid.setVgap(10);
        cycleGrid.setPadding(new Insets(10));

        Label durationLabel = new Label("Длительность цикла");
        TextField durationField = new TextField("5");
        Label durationUnit = new Label("s");

        Label countLabel = new Label("Кол-во циклов");
        TextField countField = new TextField("10");

        cycleGrid.add(durationLabel, 0, 0);
        cycleGrid.add(durationField, 1, 0);
        cycleGrid.add(durationUnit, 2, 0);

        cycleGrid.add(countLabel, 0, 1);
        cycleGrid.add(countField, 1, 1);

        cycleGrid.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        // Кнопки
        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Отменить");
        okBtn.setPrefWidth(100);
        cancelBtn.setPrefWidth(100);

        HBox btnBox = new HBox(20, okBtn, cancelBtn);
        btnBox.setAlignment(Pos.CENTER);

        // Главная компоновка
        VBox root = new VBox(10, title, tempBox, paramGrid, cycleGrid, btnBox);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 430, 520);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

