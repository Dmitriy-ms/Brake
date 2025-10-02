package ru.volgatest.brake.MainPane.Widgets;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.volgatest.brake.Widgets.LimitedFloatField;

public class BlowingHeating extends MovingPane {
    final static int SPACING = 10;

    public BlowingHeating() {

        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-border-color: derive(-fx-base, -20%); -fx-border-width: 2px;");

        Tab tab1 = new Tab("Обдув", createMainContent("Blowing"));
        Tab tab2 = new Tab("Нагрев", createMainContent("Heating")); // практически идентичен

        tabPane.getTabs().addAll(tab1, tab2);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        this.getChildren().addAll(tabPane, new Label(""), new Label(""));

        this.setSpacing(20);
//        this.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, -30%);-fx-padding: 5px;");
        this.setLayoutX(472);
        this.setLayoutY(455);
    }


    private VBox createMainContent(String nameMenu) {
        // === Группа радиокнопок ===
        Label sensorLabel = new Label("По датчику температуры:");

        ToggleGroup sensorGroup = new ToggleGroup();
        RadioButton t1 = new RadioButton("T1");
        t1.setToggleGroup(sensorGroup);
        RadioButton t2 = new RadioButton("T2");
        t2.setToggleGroup(sensorGroup);
        RadioButton t3 = new RadioButton("T3");
        t3.setToggleGroup(sensorGroup);
        RadioButton t4 = new RadioButton("T4");
        t4.setToggleGroup(sensorGroup);
        RadioButton t5 = new RadioButton("T5");
        t5.setToggleGroup(sensorGroup);
        RadioButton t6 = new RadioButton("T6");
        t6.setToggleGroup(sensorGroup);
        RadioButton t7 = new RadioButton("T7");
        t7.setToggleGroup(sensorGroup);
        RadioButton t8 = new RadioButton("T8");
        t8.setToggleGroup(sensorGroup);
        RadioButton ir = new RadioButton("IR");
        ir.setToggleGroup(sensorGroup);
        ir.setSelected(true);

        GridPane radioGrid = new GridPane();
        radioGrid.setHgap(10);
        radioGrid.setVgap(5);
        radioGrid.add(t1, 0, 0);
        radioGrid.add(t3, 1, 0);
        radioGrid.add(t5, 2, 0);
        radioGrid.add(t7, 3, 0);
        radioGrid.add(ir, 4, 0);
        radioGrid.add(t2, 0, 1);
        radioGrid.add(t4, 1, 1);
        radioGrid.add(t6, 2, 1);
        radioGrid.add(t8, 3, 1);

        VBox radioBox = new VBox( sensorLabel, radioGrid);
        radioBox.setPadding(new Insets(5));
        radioBox.setMaxWidth(200);
        radioBox.setStyle("-fx-border-color: derive(-fx-base, -20%); -fx-border-width: 1px");

        // === Кнопки ===
        Button startBtn = new Button("Старт");
        startBtn.setMinSize(50, 20);
        Button stopBtn = new Button("Стоа");
        stopBtn.setMinSize(50, 20);
        Button coolBtn = new Button("Охлад.");
        coolBtn.setMinSize(50, 20);
        VBox buttonBox = new VBox(10);

        VBox inputBox = new VBox(5);
        if (nameMenu.equals("Blowing")) {
            LimitedFloatField requiredBlowingField = new LimitedFloatField("Требуемый обдув", "m/s", 0, 500, 0);
            requiredBlowingField.setNameWidth(150);
            LimitedFloatField tempField = new LimitedFloatField("Температура", "°C", 0, 500, 0);
            tempField.setNameWidth(150);
            LimitedFloatField airSpeedField = new LimitedFloatField("Скорость воздуха", "m/s", 0, 500, 0);
            airSpeedField.setNameWidth(150);
            airSpeedField.setDisable(true);
            inputBox = new VBox(5, requiredBlowingField, tempField, airSpeedField);
            buttonBox = new VBox(10, startBtn, stopBtn, coolBtn);
        } else {
            LimitedFloatField tempField = new LimitedFloatField("Температура", "°C", 0, 500, 0);
            tempField.setNameWidth(150);
            LimitedFloatField speedField = new LimitedFloatField("Требуемый обдув", "m/s", 0, 500, 0);
            speedField.setNameWidth(150);
            LimitedFloatField pressureField = new LimitedFloatField("Скорость воздуха", "m/s", 0, 500, 0);
            pressureField.setNameWidth(150);
            inputBox = new VBox(5, tempField, speedField, pressureField);
            buttonBox = new VBox(10, startBtn, stopBtn);

        }
        radioBox.setPadding(new Insets(5));
        inputBox.setPadding(new Insets(5));
        buttonBox.setPadding(new Insets(5));

        VBox radioAndInputBox = new VBox(10, radioBox, inputBox);
        radioAndInputBox.setPadding(new Insets(5));

        Separator separatorVertical = new Separator();
        separatorVertical.setOrientation(Orientation.VERTICAL);
        // === Компоновка ===
        HBox mainBox = new HBox(5, radioAndInputBox,separatorVertical, buttonBox);
//        mainBox.setPadding(new Insets(10));       ;

        return new VBox(mainBox);
    }




}
