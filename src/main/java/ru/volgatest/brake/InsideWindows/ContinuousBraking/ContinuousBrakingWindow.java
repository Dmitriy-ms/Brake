package ru.volgatest.brake.InsideWindows.ContinuousBraking;

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
import ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousCycle.ContinuousBrakeCycleTable;
import ru.volgatest.brake.Widgets.LimitedIntegerField;

public class ContinuousBrakingWindow extends VBox{
    Stage stage = new Stage();

    Label title = new Label("Характеристики испытания");
    Label titleTesting = new Label("Испытание в режиме непрерывного торможения");

    Label testingLb = new Label("Непрерывное торможение");
//    ComboBox<String> testingBox;
    Button libraryTestingBtn = new Button("Библиотека испытаний");

    LimitedIntegerField decelerationTorque = new LimitedIntegerField("Замедляющий момент", "Nm", 0, 500, 0);


    Button OKBtn = new Button("OK");
    Button canBtn = new Button("Отмена");

    public ContinuousBrakingWindow() {

        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 300px; -fx-alignment: center;");
        titleTesting.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px;  -fx-alignment: center;"); //-fx-max-width: 450px;


        HBox hBoxTesting = new HBox(10, testingLb, libraryTestingBtn);


        VBox vBoxTesting = new VBox(10, hBoxTesting, decelerationTorque);
        vBoxTesting.setStyle("-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 1px; -fx-padding: 5px;");


        int minWidthLb = 230;
        testingLb.setMinWidth(minWidthLb);
        decelerationTorque.setNameWidth(minWidthLb);


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox footerHBox = new HBox(10, spacer, OKBtn, canBtn);
        footerHBox.setMaxWidth(Double.MAX_VALUE);

        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(5));
        this.setSpacing(10);
        BrakingMechanismWidget brakingMechanismWidget = new BrakingMechanismWidget();
        this.getChildren().addAll(title, brakingMechanismWidget, titleTesting, vBoxTesting, footerHBox);

        buttonsController();

        Scene scene = new Scene(this, 650, 370);
        scene.getStylesheets().add("/main.css");
        stage.setTitle("Непрерывное торможение");
        stage.setScene(scene); // Размер можно подогнать
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется

    }

    void buttonsController() {
        libraryTestingBtn.setOnAction(e -> {
            ContinuousBrakeCycleTable continuousBrakeCycleTable = new ContinuousBrakeCycleTable();
            continuousBrakeCycleTable.saveData();
        });
        OKBtn.setOnAction(e -> {
            stage.close();
        });
        canBtn.setOnAction(e -> {
            stage.close();
        });
    }


}
