package ru.volgatest.brake.InsideWindows.SubCycle;

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
import ru.volgatest.brake.ObjectsLibrary.Library;

import java.util.ArrayList;
import java.util.List;

public class SubCycleTable extends VBox {

    List<SubCycleTableRow> rows = new ArrayList<>();

    SubCycleTableRow selectedSubCycleRow;

    Button add = new Button("+");
    Button delete = new Button("-");
    Button edit = new Button("edit");
    Button copy = new Button("copy");
    HBox buttonBox = new HBox(10, add, delete, edit, copy);

    Label numberLb = new Label("No");
    Label nameLb = new Label("Name");
    HBox header = new HBox(10, numberLb, nameLb);

    VBox table = new VBox(10);

    public SubCycleTable() {


        add.setOnAction(event -> {
            rows.add(new SubCycleTableRow(new SubCycleModel()));
            refreshView();
        });

        delete.setOnAction(event -> {
            rows.remove(selectedSubCycleRow);
            refreshView();
        });

        edit.setOnAction(event -> {
            SubCycleWindow subCycleWindow = new SubCycleWindow(selectedSubCycleRow.subCycleModel);
        });

        copy.setOnAction(event -> {
            if (selectedSubCycleRow != null) {
                rows.add(new SubCycleTableRow(selectedSubCycleRow.subCycleModel.clone()));
                refreshView();
            }
        });

        this.setSpacing(10);
        this.getChildren().addAll(buttonBox, header, table);
        this.setPadding(new Insets(5));

        Scene scene = new Scene(this, 600, 400);
        scene.getStylesheets().add("/main.css");

        Stage stage = new Stage();
        stage.setTitle("Библиотека испытаний");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется
        stage.setOnHidden(event -> {
            Library.saveData(Library.LOADED_LIBRARY.subCycleModelList);
        });

    }

    public void refreshView() {
        table.getChildren().clear();
        for (int i = 0; i < rows.size(); i++) {
            SubCycleTableRow row = rows.get(i);
            row.numberLb.setText(String.valueOf(i));
            row.number = i;
            table.getChildren().add(row);
        }


    }

    class SubCycleTableRow extends HBox {
        public SubCycleModel subCycleModel;

        int number = 0;


        Label numberLb = new Label("0");

        TextField nameField = new TextField("");

        public SubCycleTableRow(SubCycleModel subCycleModel) {
            this.subCycleModel = subCycleModel;

            numberLb.setStyle("-fx-min-height: 25px; -fx-min-width: 25px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 2px; -fx-alignment: center;");

            nameField.setText(subCycleModel.name);
            nameField.textProperty().addListener(n -> subCycleModel.name = nameField.getText());
            nameField.setStyle("-fx-min-height: 25px; -fx-min-width: 70px;-fx-max-width: 70px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%);-fx-border-radius: 2px; -fx-alignment: center-left;");

            this.addEventFilter(MouseEvent.MOUSE_CLICKED, ev -> {
                selectedSubCycleRow = this;

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
