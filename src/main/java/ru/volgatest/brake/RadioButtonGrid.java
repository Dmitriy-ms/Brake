package ru.volgatest.brake;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RadioButtonGrid extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Создаем группу для RadioButton
        ToggleGroup toggleGroup = new ToggleGroup();

        // Подписи
        Label label = new Label("По датчику температуры:");

        // Создаем RadioButton'ы
        String[] labels = {"T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "IR"};
        RadioButton[] buttons = new RadioButton[labels.length];

        for (int i = 0; i < labels.length; i++) {
            buttons[i] = new RadioButton(labels[i]);
            buttons[i].setToggleGroup(toggleGroup);
        }

        // Используем GridPane для размещения в две строки
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        for (int i = 0; i < buttons.length; i++) {
            int row = i % 2;
            int col = i / 2;
            grid.add(buttons[i], col, row);
        }

        // Вкладываем в VBox
        VBox content = new VBox(5);
        content.getChildren().addAll(label, grid);
        content.setPadding(new Insets(10));

        // Обводка (как на скрине)
        TitledPane titledPane = new TitledPane();
        titledPane.setText(""); // пустой, т.к. заголовок уже есть отдельно
        titledPane.setContent(content);
        titledPane.setCollapsible(false);

        // Оборачиваем в StackPane
        StackPane root = new StackPane(titledPane);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: lightblue;");

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/main.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Температурные сенсоры");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}