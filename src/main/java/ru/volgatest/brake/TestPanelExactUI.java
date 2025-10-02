package ru.volgatest.brake;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestPanelExactUI extends Application {

    @Override
    public void start(Stage stage) {
        // Левая панель с кнопками испытаний
        Label titleLeft = new Label("ИСПЫТАНИЯ");
        titleLeft.setTextFill(Color.BLUE);

//        HBox intermittentBraking = createArrowButton("Прерывистое торможение");
//        HBox continuousBraking = createArrowButton("Непрерывное торможение");
//        HBox sliding = createArrowButton("Страгивание");

        Button intermittentBrakingBtn = new Button("Прерывистое торможение");
        Button continuousBrakingBtn = new Button("Непрерывное торможение");
        Button slidingBtn = new Button("Страгивание");

        intermittentBrakingBtn.setStyle("-fx-min-width: 180px; -fx-min-height: 30px; -fx-alignment: center;");
        continuousBrakingBtn.setStyle("-fx-min-width: 180px; -fx-min-height: 30px; -fx-alignment: center;");
        slidingBtn.setStyle("-fx-min-width: 180px; -fx-min-height: 30px; -fx-alignment: center;");

        intermittentBrakingBtn.setOnAction(event -> {
            HelloApplication app = new HelloApplication();
            app.start(new Stage());
        });


//        VBox buttonsBox = new VBox(5,
//                intermittentBraking,
//                continuousBraking,
//                sliding
//        );
        VBox buttonsBox = new VBox(5,
                intermittentBrakingBtn,
                continuousBrakingBtn,
                slidingBtn
        );
        buttonsBox.setMinWidth(200);

        VBox leftPanel = new VBox(10, titleLeft, buttonsBox);
        leftPanel.setPadding(new Insets(10));
        leftPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        leftPanel.setPrefWidth(170);

        // Центральная панель с режимом, индикатором и кнопками
        Label manual = new Label("РУЧНОЙ");
        manual.setStyle("-fx-background-color: green;-fx-border-color: white; -fx-border-width: 1px;-fx-min-width: 100px; -fx-min-height: 25px; -fx-alignment: center;");

        Label auto = new Label("АВТО");
        auto.setStyle("-fx-border-color: white; -fx-border-width: 1px;-fx-min-width: 100px; -fx-min-height: 25px; -fx-alignment: center;");

//        Circle statusCircle = new Circle(15, Color.BLACK);

        Region spacer = new Region();
        HBox modeBox = new HBox(10, manual, spacer, auto);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        modeBox.setAlignment(Pos.CENTER_LEFT);

        Button oscilloBtn = new Button("Осциллограф");
        Button limitsBtn = new Button("Ограничения");
        oscilloBtn.setStyle("-fx-min-width: 200px; -fx-min-height: 30px; -fx-alignment: center;");
        limitsBtn.setStyle("-fx-min-width: 200px; -fx-min-height: 30px; -fx-alignment: center;");


        VBox modePanel = new VBox(10, modeBox, oscilloBtn, limitsBtn);
        modePanel.setPadding(new Insets(10));
        modePanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        // Панель состояния испытания
        Label stateLabel = new Label("Состояние испытания:");
        Label statusLabel = new Label("Испытание не запущено");
        Label timerLabel = new Label("Время с начала испытания: 0 : 00 : 00");
        Button reportBtn = new Button("Сгенерировать отчёт");
        reportBtn.setStyle("-fx-min-width: 200px; -fx-min-height: 30px; -fx-alignment: center;");


        VBox statusPanel = new VBox(5, stateLabel, statusLabel, timerLabel, reportBtn);
        statusPanel.setPadding(new Insets(10));
        statusPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        VBox centerPanel = new VBox(10, modePanel);
        centerPanel.setPrefWidth(300);

        // Правая панель с сообщениями
        Label messageTitle = new Label("Сообщения");
        Label messageCount = new Label("3 сообщений");
        messageCount.setMaxWidth(Double.MAX_VALUE);
        messageCount.setStyle("-fx-padding: 0 0 0 5;-fx-background-color: yellow; -fx-min-height: 20px; -fx-min-width: 300px;-fx-text-fill: black;-fx-border-width: 2px;-fx-border-color: lightgray;");

//        ProgressBar yellowBar = new ProgressBar();
//        yellowBar.setProgress(1.0);
//        yellowBar.setStyle("-fx-accent: yellow;");

        Button resetButton = new Button("Сброс ошибок");
        resetButton.setStyle("-fx-min-width: 100px; -fx-min-height: 20px; -fx-alignment: center;");
        HBox messageCountAndResetBt = new HBox(5, messageCount, resetButton);
        HBox.setHgrow(messageCount, Priority.ALWAYS);
        messageCountAndResetBt.setAlignment(Pos.CENTER_LEFT);
        messageCountAndResetBt.setStyle("");
        ListView<String> messageList = new ListView<>();
        messageList.getItems().addAll(
                "MSG01 : Ожидание включения питания",
                "MSGPR311 : Включено удерживание всех маховиков",
                "MSGPR304 : Превышена температура"
        );
        messageList.setPrefHeight(100);

        VBox rightPanel = new VBox(5,
                messageTitle,
                messageCountAndResetBt,
//                yellowBar,
                messageList
        );
        rightPanel.setPadding(new Insets(10));
        rightPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        rightPanel.setPrefWidth(450);

        // Основной контейнер
        HBox root = new HBox(5, leftPanel, centerPanel, statusPanel, rightPanel);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/main.css");

        stage.setScene(scene);
        stage.setTitle("Панель управления испытаниями");
        stage.show();
    }

    private HBox createArrowButton(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white;");
        Label arrow = new Label("> ");
        arrow.setStyle("-fx-text-fill: white;");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox hbox = new HBox(5, label, spacer, arrow);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-padding: 5;");
        hbox.setOnMouseEntered(event -> {
            hbox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-padding: 5; -fx-background-color: lightgray; ");
            label.setStyle("-fx-text-fill: black;");
            arrow.setStyle("-fx-text-fill: black;");
        });
        hbox.setOnMouseExited(event -> {
            hbox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-padding: 5;");
            label.setStyle("-fx-text-fill: white;");
            arrow.setStyle("-fx-text-fill: white;");

        });

        return hbox;
    }

    public static void main(String[] args) {
        launch();
    }
}
