package ru.volgatest.brake.InsideWindows.Breakaway;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.volgatest.brake.InsideWindows.BrakingMechanisms.BrakeModel;
import ru.volgatest.brake.InsideWindows.BrakingMechanisms.BrakingMechanismWidget;
import ru.volgatest.brake.Widgets.LimitedIntegerField;

public class BreakawayTestWindow extends VBox {

    Stage stage = new Stage();

    Label title = new Label("Характеристики испытания");
    Label titleTesting = new Label("Статическое испытание на страгивание");
    LimitedIntegerField controlPressureLiF = new LimitedIntegerField("Давление в управляющей магистрали", "bar", 0, 500, 0);

    Button OKBtn = new Button("OK");
    Button canBtn = new Button("Отмена");


    public BreakawayTestWindow() {


        VBox controlPressureVBox = new VBox(10, controlPressureLiF);
        controlPressureVBox.setStyle("-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 1px; -fx-padding: 5px;");


        int minWidthLb = 230;
        controlPressureLiF.setNameWidth(minWidthLb);


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox footerHBox = new HBox(10, spacer, OKBtn, canBtn);
        footerHBox.setMaxWidth(Double.MAX_VALUE);

        buttonsController();


        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(5));
        this.setSpacing(10);
        BrakingMechanismWidget brakingMechanismWidget = new BrakingMechanismWidget();
        this.getChildren().addAll(title, brakingMechanismWidget/*vBoxBrakeMech*/, titleTesting, controlPressureVBox, footerHBox);


        Scene scene = new Scene(this, 650, 370);
        scene.getStylesheets().add("/main.css");
        stage.setTitle("Страгивание");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется

    }


    public void buttonsController() {
        OKBtn.setOnAction(event -> stage.close());
        canBtn.setOnAction(event -> stage.close());
    }

    void setStyleElements() {
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 300px; -fx-alignment: center;");
        titleTesting.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 450px; -fx-alignment: center;");
    }
}
