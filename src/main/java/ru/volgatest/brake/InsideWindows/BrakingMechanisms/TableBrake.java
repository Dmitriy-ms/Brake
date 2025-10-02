package ru.volgatest.brake.InsideWindows.BrakingMechanisms;

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

public class TableBrake extends VBox {

//    Переделать добавление строк, при кнопки добавить,  должна добавляться в лист усовно БракМодел, а потом проходя по этому листу должна добавлять уже BrakeTableRow передавая туда БракеМодель, можно посмотреть в предыдущем проекте

    public Stage stage = new Stage();

    //    List<BrakeTableRow> rows = new ArrayList<>();

    List<BrakeModel> brakeList;

    BrakeTableRow selectedBrake;

    Button add = new Button("+");
    Button delete = new Button("-");
    Button edit = new Button("edit");
    Button copy = new Button("copy");
    Button save = new Button("save");
    //    Button openLibrarySubCycle = new Button("Библиотека механизмов");
    HBox buttonBox = new HBox(10, add, delete, edit, copy, save);

    Label numberLb = new Label("No");
    Label nameLb = new Label("Name");
    HBox header = new HBox(10, numberLb, nameLb);

    VBox table = new VBox(10);

    public TableBrake() {

        brakeList = Library.LOADED_LIBRARY.brakeModelList != null ? Library.LOADED_LIBRARY.brakeModelList : new ArrayList<>();
        if (!brakeList.isEmpty()) refreshView();

        add.setOnAction(event -> {
            brakeList.add(new BrakeModel());
//            rows.add(new BrakeTableRow(new BrakeModel()));
            refreshView();
        });

        delete.setOnAction(event -> {
            brakeList.remove(selectedBrake.brakeModel);
//            rows.remove(selectedBrake);
            refreshView();
        });

        edit.setOnAction(event -> {
            if(selectedBrake == null) return;
            ParamBrakeWindow selectBrake = new ParamBrakeWindow(selectedBrake.brakeModel);
        });

        copy.setOnAction(event -> {
            if (selectedBrake != null) {
                brakeList.add(selectedBrake.brakeModel.clone());
//                rows.add(new BrakeTableRow(selectedBrake.brakeModel.clone()));
                refreshView();
            }
        });

        save.setOnAction(event -> {
            Library.saveData(brakeList);
        });

//        openLibrarySubCycle.setOnAction(event -> {
//            SubCycleTable subCycleTable = new SubCycleTable();
//        });

        this.setSpacing(10);
        this.getChildren().addAll(buttonBox, header, table);
        this.setPadding(new Insets(5));

        Scene scene = new Scene(this, 600, 400);
        scene.getStylesheets().add("/main.css");


        stage.setTitle("Библиотека механизмов");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется
        stage.setOnHidden(event -> Library.saveData(brakeList));
    }

    public void refreshView() {
        Library.LOADED_LIBRARY.brakeModelList = brakeList;
        table.getChildren().clear();
        for (int i = 0; i < brakeList.size(); i++) {
            BrakeTableRow row = new BrakeTableRow(brakeList.get(i));
            row.numberLb.setText(String.valueOf(i));
            row.number = i;
            table.getChildren().add(row);
//            BrakeTableRow row = rows.get(i);
//            row.numberLb.setText(String.valueOf(i));
//            row.number = i;
//            table.getChildren().add(row);
        }


    }

    class BrakeTableRow extends HBox {
        public BrakeModel brakeModel;

        int number = 0;


        Label numberLb = new Label("0");

        TextField nameField = new TextField("");

        public BrakeTableRow(BrakeModel brakeModel) {
            this.brakeModel = brakeModel;

            numberLb.setStyle("-fx-min-height: 25px; -fx-min-width: 25px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 2px; -fx-alignment: center;");

            nameField.setText(brakeModel.name);
            nameField.textProperty().addListener(n -> brakeModel.name = nameField.getText());
            nameField.setStyle("-fx-min-height: 25px; -fx-min-width: 150px;-fx-max-width: 70px;-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%);-fx-border-radius: 2px; -fx-alignment: center-left;");

            this.addEventFilter(MouseEvent.MOUSE_CLICKED, ev -> {
                if (selectedBrake != null) {
                    selectedBrake.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, 27%);");
                }
                selectedBrake = this;

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
            this.getChildren().addAll(numberLb, nameField);
        }

    }

}
