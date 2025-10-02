package ru.volgatest.brake;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TestPanelUI extends Application {

    @Override
    public void start(Stage stage) {
        // ====== ЛЕВАЯ ПАНЕЛЬ ИСПЫТАНИЙ ======
        Label titleLeft = new Label("ИСПЫТАНИЯ");
        titleLeft.setFont(Font.font("System", 16));
        titleLeft.setTextFill(Color.BLUE);

        VBox buttonsBox = new VBox(5,
                createArrowButton("Прерывистое торможение"),
                createArrowButton("Непрерывное торможение"),
                createArrowButton("Страгивание")
        );

        VBox leftPanel = new VBox(10, titleLeft, buttonsBox);
        leftPanel.setPadding(new Insets(10));
        leftPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        leftPanel.setPrefWidth(170);

        // ====== ЦЕНТРАЛЬНАЯ ПАНЕЛЬ ======
        Label manual = new Label("РУЧНОЙ");
        Label auto = new Label("АВТО");
        Circle statusCircle = new Circle(15, Color.BLACK);

        HBox modeBox = new HBox(10, manual, statusCircle, auto);
        modeBox.setAlignment(Pos.CENTER);

        Button oscilloBtn = new Button("Осциллограф");
        oscilloBtn.setMaxWidth(Double.MAX_VALUE);
        Button limitsBtn = new Button("Ограничения");
        limitsBtn.setMaxWidth(Double.MAX_VALUE);
        Button reportBtn = new Button("Сгенерировать отчёт");
        reportBtn.setMaxWidth(Double.MAX_VALUE);

        Label stateLabel = new Label("Состояние испытания:");
        stateLabel.setStyle("-fx-text-fill: blue;");
        Label statusLabel = new Label("Испытание не запущено");
        Label timerLabel = new Label("Время с начала испытания:     0 : 00 : 00");

        VBox centerPanel = new VBox(10,
                modeBox, oscilloBtn, limitsBtn,
                stateLabel, statusLabel, timerLabel,
                reportBtn
        );
        centerPanel.setPadding(new Insets(10));
        centerPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        centerPanel.setPrefWidth(300);

        // ====== ПРАВАЯ ПАНЕЛЬ ======
        Label messageTitle = new Label("Сообщения");
        Label messageCount = new Label("3 сообщений");
        messageCount.setStyle("-fx-font-weight: bold;");

        ProgressBar yellowBar = new ProgressBar();
        yellowBar.setProgress(1.0);
        yellowBar.setStyle("-fx-accent: yellow;");

        Button resetButton = new Button("Сброс ошибок");

        ListView<String> messageList = new ListView<>();
        messageList.getItems().addAll(
                "MSG01 : Ожидание включения питания",
                "MSGPR311 : Включено удерживание всех маховиков от вращения",
                "MSGPR304 : Ограничение, введенное оператором: превышена температура"
        );
        messageList.setPrefHeight(90);

        VBox rightPanel = new VBox(5,
                messageTitle,
                messageCount,
                yellowBar,
                resetButton,
                messageList
        );
        rightPanel.setPadding(new Insets(10));
        rightPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        rightPanel.setPrefWidth(450);

        // ====== ROOT ======
        HBox root = new HBox(leftPanel, centerPanel, rightPanel);
        root.setSpacing(5);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/main.css");
        stage.setScene(scene);
        stage.setTitle("Панель управления испытаниями");
        stage.show();
    }

    private HBox createArrowButton(String text) {
        Label label = new Label(text);
        Label arrow = new Label("> ");
        arrow.setStyle("-fx-font-weight: bold;");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox hbox = new HBox(5, label, spacer, arrow);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-padding: 5;");
        return hbox;
    }

    public static void main(String[] args) {
        launch();
    }
}


