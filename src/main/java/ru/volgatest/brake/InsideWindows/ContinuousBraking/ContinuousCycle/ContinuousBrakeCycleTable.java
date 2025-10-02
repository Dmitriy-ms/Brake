package ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousCycle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousBrakingModel;
import ru.volgatest.brake.ObjectsLibrary.Library;

import java.util.ArrayList;
import java.util.List;

public class ContinuousBrakeCycleTable extends VBox {
    public Stage stage = new Stage();


    List<CycleTableRow> rows = new ArrayList<>();
    List<ContinuousBrakingModel> continuousBrakingModels = new ArrayList<>();

    CycleTableRow selectedCycleRow;
    ContinuousBrakingModel selectedContinuousBrakingModel;

    Button add = new Button("+");
    Button delete = new Button("-");
    Button edit = new Button("edit");
    Button copy = new Button("copy");
    HBox buttonBox = new HBox(10, add, delete, edit, copy);

    Label numberLb = new Label("No");
    Label nameLb = new Label("Name");
    HBox header = new HBox(10, numberLb, nameLb);

    VBox table = new VBox(10);

    public ContinuousBrakeCycleTable() {

        continuousBrakingModels = Library.LOADED_LIBRARY.continuousCycle != null ? Library.LOADED_LIBRARY.continuousCycle : new ArrayList<>();

//        refreshView();
        add.setOnAction(event -> {
//            rows.add(new CycleTableRow(new ContinuousBrakingModel()));
            continuousBrakingModels.add(new ContinuousBrakingModel());
            refreshView();
        });

        delete.setOnAction(event -> {
//            rows.remove(selectedCycleRow);
            continuousBrakingModels.remove(selectedCycleRow.continuousBrakingModel);
            refreshView();
        });

        edit.setOnAction(event -> {
            if (selectedCycleRow == null) return;
            ContinuousBrakeCycleWindow continuousBrakeCycleWindow = new ContinuousBrakeCycleWindow(selectedCycleRow.continuousBrakingModel);
        });

        copy.setOnAction(event -> {
            if (selectedCycleRow != null) {
                continuousBrakingModels.add(selectedCycleRow.continuousBrakingModel.clone());
//                rows.add(new CycleTableRow(selectedCycleRow.continuousBrakingModel.clone()));
                refreshView();
            }
        });

        this.setSpacing(10);
        this.getChildren().addAll(buttonBox, header, table);
        this.setPadding(new Insets(5));

        Scene scene = new Scene(this, 600, 400);
        scene.getStylesheets().add("/main.css");

        stage.setTitle("Библиотека испытаний");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется

    }

    public void saveData() {
        System.out.println("Начало");
        Library.saveData(continuousBrakingModels);
        System.out.println("КОнец");
    }

    public void refreshView() {
        Library.LOADED_LIBRARY.continuousCycle = continuousBrakingModels;
        table.getChildren().clear();
        for (int i = 0; i < continuousBrakingModels.size(); i++) {
            CycleTableRow row = new CycleTableRow(continuousBrakingModels.get(i));
//            CycleTableRow row = rows.get(i);
            row.numberLb.setText(String.valueOf(i));
            row.number = i;
            table.getChildren().add(row);
        }


    }

    class CycleTableRow extends HBox {
        public ContinuousBrakingModel continuousBrakingModel;

        int number = 0;


        Label numberLb = new Label("0");

        TextField nameField = new TextField("");

        public CycleTableRow(ContinuousBrakingModel continuousBrakingModel) {
            this.continuousBrakingModel = continuousBrakingModel;

            numberLb.setStyle("-fx-min-height: 25px; -fx-min-width: 25px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 2px; -fx-alignment: center;");

            nameField.setText(continuousBrakingModel.name);
            nameField.textProperty().addListener(n -> continuousBrakingModel.name = nameField.getText());
            nameField.setStyle("-fx-min-height: 25px; -fx-min-width: 70px;-fx-max-width: 70px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%);-fx-border-radius: 2px; -fx-alignment: center-left;");

            this.addEventFilter(MouseEvent.MOUSE_CLICKED, ev -> {
                selectedCycleRow = this;

                this.setStyle("-fx-border-width: 2px; -fx-border-color: rgba(9,252,9,0.5);");
                rows.forEach(row -> {
                    if (row != this) {
                        row.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, 27%);");
                    }
                });
            });


            this.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(1));
            this.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, 27%);");
            this.setMaxWidth(105);
            this.setSpacing(10);
            this.getChildren().addAll(numberLb, nameField);
        }

    }
}
