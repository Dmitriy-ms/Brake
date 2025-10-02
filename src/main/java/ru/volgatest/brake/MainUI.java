package ru.volgatest.brake;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Обдув", createMainContent());
        Tab tab2 = new Tab("Нагрев", createMainContent()); // практически идентичен

        tabPane.getTabs().addAll(tab1, tab2);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 420, 260);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Контроль температуры");
        primaryStage.show();
    }

    private VBox createMainContent() {
        // === Группа радиокнопок ===
        Label sensorLabel = new Label("По датчику температуры:");

        ToggleGroup sensorGroup = new ToggleGroup();
        RadioButton t1 = new RadioButton("T1"); t1.setToggleGroup(sensorGroup);
        RadioButton t2 = new RadioButton("T2"); t2.setToggleGroup(sensorGroup);
        RadioButton t3 = new RadioButton("T3"); t3.setToggleGroup(sensorGroup);
        RadioButton t4 = new RadioButton("T4"); t4.setToggleGroup(sensorGroup);
        RadioButton t5 = new RadioButton("T5"); t5.setToggleGroup(sensorGroup);
        RadioButton t6 = new RadioButton("T6"); t6.setToggleGroup(sensorGroup);
        RadioButton t7 = new RadioButton("T7"); t7.setToggleGroup(sensorGroup);
        RadioButton t8 = new RadioButton("T8"); t8.setToggleGroup(sensorGroup);
        RadioButton ir = new RadioButton("IR"); ir.setToggleGroup(sensorGroup);
        ir.setSelected(true);

        GridPane radioGrid = new GridPane();
        radioGrid.setHgap(10);
        radioGrid.setVgap(5);
        radioGrid.add(t1, 0, 0); radioGrid.add(t3, 1, 0); radioGrid.add(t5, 2, 0);
        radioGrid.add(t7, 3, 0); radioGrid.add(ir, 4, 0);
        radioGrid.add(t2, 0, 1); radioGrid.add(t4, 1, 1); radioGrid.add(t6, 2, 1);
        radioGrid.add(t8, 3, 1);

        VBox radioBox = new VBox(5, sensorLabel, radioGrid);
        radioBox.setPadding(new Insets(5));
        radioBox.setStyle("-fx-border-color: gray; -fx-border-radius: 3; -fx-background-color: #bde0f0;");

        // === Поля ввода ===
        Label requiredLabel = new Label("Требуемый обдув");
        TextField requiredField = new TextField("0");
        Label requiredUnit = new Label("m/s");

        Label tempLabel = new Label("Температура");
        TextField tempField = new TextField("0");
        Label tempUnit = new Label("°C");

        HBox requiredBox = new HBox(5, requiredLabel, requiredField, requiredUnit);
        HBox tempBox = new HBox(5, tempLabel, tempField, tempUnit);
        VBox inputBox = new VBox(10, requiredBox, tempBox, new Label("Скорость воздуха\t0,1 m/s"));
        inputBox.setPadding(new Insets(10));
        inputBox.setStyle("-fx-background-color: #bde0f0;");

        // === Кнопки ===
        Button startBtn = new Button("СТАРТ");
        Button stopBtn = new Button("СТОП");
        Button coolBtn = new Button("ОХЛАЖД.");
        coolBtn.setDisable(true); // как на скрине

        VBox buttonBox = new VBox(10, startBtn, stopBtn, coolBtn);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setStyle("-fx-background-color: #bde0f0;");

        // === Компоновка ===
        HBox mainBox = new HBox(10, new VBox(10, radioBox, inputBox), buttonBox);
        mainBox.setPadding(new Insets(10));
        mainBox.setStyle("-fx-background-color: #bde0f0;");

        return new VBox(mainBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
