package ru.volgatest.brake.InsideWindows.IntermittentBraking.Cycle;

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
import ru.volgatest.brake.InsideWindows.IntermittentBraking.SubCycle.SubCycleTable;
import ru.volgatest.brake.ObjectsLibrary.Library;
import ru.volgatest.brake.Widgets.LimitedIntegerField;

import java.util.ArrayList;
import java.util.List;

public class CycleTable extends VBox {

//    List<SubCycleTableRow> rows = new ArrayList<>();

    CycleTableRow selectedCycleRow;

    List<CycleModel> cycleModelList;

    Button add = new Button("+");
    Button delete = new Button("-");
    Button edit = new Button("edit");
    Button copy = new Button("copy");
    Button openLibrarySubCycle = new Button("Библиотка подциклов");
    HBox buttonBox = new HBox(10, add, delete, edit, copy, openLibrarySubCycle);

    Label numberLb = new Label("No");
    Label nameLb = new Label("Name");
    Label repeatLb = new Label("Кол-во повторений");
    HBox header = new HBox(10, numberLb, nameLb, repeatLb);

    VBox table = new VBox(10);

    public CycleTable() {

        cycleModelList = Library.LOADED_LIBRARY.cycleModelList != null ? Library.LOADED_LIBRARY.cycleModelList : new ArrayList<>();
        refreshView();
        add.setOnAction(event -> {
            cycleModelList.add(new CycleModel());
            refreshView();
        });

        delete.setOnAction(event -> {
            cycleModelList.remove(selectedCycleRow.cycleModel);
            refreshView();
        });

        edit.setOnAction(event -> {
            SelectSubCycleWindow selectSubCycleWindow = new SelectSubCycleWindow(selectedCycleRow.cycleModel, selectedCycleRow.cycleModel.subCycleModels);
            Library.saveData(selectSubCycleWindow.subCycleModels);
        });

        copy.setOnAction(event -> {
            if (selectedCycleRow != null) {
                cycleModelList.add(selectedCycleRow.cycleModel.clone());
                refreshView();
            }
        });

        openLibrarySubCycle.setOnAction(event -> {
            SubCycleTable subCycleTable = new SubCycleTable();
            Library.saveData(Library.LOADED_LIBRARY.subCycleModelList);
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
//        stage.setOnHidden(event -> Library.saveData(Library.LOADED_LIBRARY.cycleModelList));
    }

    public void refreshView() {
        Library.LOADED_LIBRARY.cycleModelList = cycleModelList;
        table.getChildren().clear();
        for (int i = 0; i < cycleModelList.size(); i++) {
            CycleTableRow row = new CycleTableRow(cycleModelList.get(i));
            row.numberLb.setText(String.valueOf(i));
            row.number = i;
            table.getChildren().add(row);
        }


    }

    class CycleTableRow extends HBox {
        public CycleModel cycleModel;

        int number = 0;


        Label numberLb = new Label("0");

        TextField nameField = new TextField();
        LimitedIntegerField repeatField = new LimitedIntegerField("", "", 0, 500, 0);

        public CycleTableRow(CycleModel cycleModel) {
            this.cycleModel = cycleModel;

            numberLb.setStyle("-fx-min-height: 25px; -fx-min-width: 25px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 2px; -fx-alignment: center;");

            nameField.setText(cycleModel.name);
            nameField.textProperty().addListener(n -> cycleModel.name = nameField.getText());
            nameField.setStyle("-fx-min-height: 25px; -fx-min-width: 70px;-fx-max-width: 70px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%);-fx-border-radius: 2px; -fx-alignment: center-left;");

            repeatField = new LimitedIntegerField("Кол-во", "", 0, 500, cycleModel.repeat);
            repeatField.textField.textProperty().addListener(value -> {
                cycleModel.repeat = repeatField.getValue();
            });
            repeatField.setFieldSize(50, 30);
//            repeatField.textField.setStyle("-fx-min-height: 25px; -fx-min-width: 70px;-fx-max-width: 70px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%);-fx-border-radius: 2px; -fx-alignment: center-left;");


            this.addEventFilter(MouseEvent.MOUSE_CLICKED, ev -> {
                if (selectedCycleRow != null) {
                    selectedCycleRow.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, 27%);");
                }

                selectedCycleRow = this;

                this.setStyle("-fx-border-width: 2px; -fx-border-color: rgba(9,252,9,0.5);");
//                rows.forEach(row -> {
//                    if (row != this) {
//                        row.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, 27%);");
//                    }
//                });
            });


            this.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(1));
            this.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, 27%);");
            this.setMaxWidth(105);
            this.setSpacing(10);
            this.getChildren().addAll(numberLb, nameField, repeatField.textField);
        }

    }
}
