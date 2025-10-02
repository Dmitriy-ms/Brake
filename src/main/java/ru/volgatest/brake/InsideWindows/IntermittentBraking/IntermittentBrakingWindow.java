package ru.volgatest.brake.InsideWindows.IntermittentBraking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.volgatest.brake.InsideWindows.BrakingMechanisms.BrakingMechanismWidget;
import ru.volgatest.brake.InsideWindows.Cycle.CycleTable;
import ru.volgatest.brake.Widgets.LimitedFloatField;
import ru.volgatest.brake.Widgets.LimitedIntegerField;

//Прерывистое торможение
public class IntermittentBrakingWindow extends VBox {

    Stage stage;

    Label title = new Label("Характеристики испытания");

    Label titleTesting = new Label("Испытание в режиме прерывистого торможения");

    Label testingLb = new Label("Прерывистое торможение");
    ComboBox<String> testingBox;
    Button libraryTestingBtn = new Button("Библиотека испытаний");

    Label tempSensorLb = new Label("Датчик температуры");
    ComboBox<String> tempSensorBox;

    LimitedFloatField preheatPressureField = new LimitedFloatField("Давление при преднагреве", "bar", 0, 500, 0);
    LimitedIntegerField rotationSpeedField = new LimitedIntegerField("Скорость вращения при преднагреве", "RPM", 0, 500, 0);

    Button OKBtn = new Button("OK");
    Button canBtn = new Button("Отмена");

    public IntermittentBrakingWindow() {

        stage = new Stage();

        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 300px; -fx-alignment: center;");
        titleTesting.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 450px; -fx-alignment: center;");

        ObservableList<String> testing = FXCollections.observableArrayList("Тест_1", "Тест_2", "Тест_3", "Тест_4");
        testingBox = new ComboBox<String>(testing);
        testingBox.setValue(testing.get(0)); // устанавливаем выбранный элемент по умолчанию

        HBox hBoxTesting = new HBox(10, testingLb, testingBox, libraryTestingBtn);

        ObservableList<String> tempSensor = FXCollections.observableArrayList("IR", "RR", "NR", "MR");
        tempSensorBox = new ComboBox<String>(tempSensor);
        tempSensorBox.setValue(tempSensor.get(0)); // устанавливаем выбранный элемент по умолчанию
        HBox tempSensorHBox = new HBox(10, tempSensorLb, tempSensorBox);
        VBox vBoxTesting = new VBox(10, hBoxTesting, tempSensorHBox, preheatPressureField, rotationSpeedField);
        vBoxTesting.setStyle("-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 1px; -fx-padding: 5px;");


        int minWidthLb = 230;
        testingLb.setMinWidth(minWidthLb);
        tempSensorLb.setMinWidth(minWidthLb);


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox footerHBox = new HBox(10, spacer, OKBtn, canBtn);
        footerHBox.setMaxWidth(Double.MAX_VALUE);

        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(5));
        this.setSpacing(10);
        BrakingMechanismWidget brakingMechanismWidget = new BrakingMechanismWidget(); //brakingMechanismBox
        this.getChildren().addAll(title, brakingMechanismWidget, titleTesting, vBoxTesting, footerHBox);
        buttonsController(); //Обработка нажатия кнопок

        Scene scene = new Scene(this, 650, 370);
        scene.getStylesheets().add("/main.css");
        stage.setTitle("Прерывестое торможение");
        stage.setScene(scene); // Размер можно подогнать
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется

    }

     void buttonsController() {
         libraryTestingBtn.setOnAction(event -> {
             CycleTable cycleTable = new CycleTable();
         });
        OKBtn.setOnAction(e -> {
            stage.close();
        });
        canBtn.setOnAction(e -> {
            stage.close();
        });
    }

    public Stage getStage() {
        return stage;
    }


}
