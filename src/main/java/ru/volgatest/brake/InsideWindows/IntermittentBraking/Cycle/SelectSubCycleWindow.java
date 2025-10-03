package ru.volgatest.brake.InsideWindows.IntermittentBraking.Cycle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.volgatest.brake.InsideWindows.IntermittentBraking.SubCycle.SubCycleModel;
import ru.volgatest.brake.ObjectsLibrary.Library;

import java.util.ArrayList;
import java.util.List;

public class SelectSubCycleWindow extends HBox {

    List<SubCycleModel> subCycleModels;

//    List<SubCycleModel> subCycleOnCycle = new ArrayList<>();

    VBox rightBox = new VBox(10);
    VBox rightTable = new VBox(10);

    VBox leftBox = new VBox(10);
    VBox leftTable = new VBox(10);

    SubCycleTableRow selectedSubCycleRow;
    SubCycleTableRow selectedSubCycleOnCycleRow;

    Button addBtn = new Button("+");

    public SelectSubCycleWindow(CycleModel cycleModel/*, List<SubCycleModel> subCycleModels*/) {
//        if (!subCycleModels.isEmpty()) {
//            this.subCycleModels = subCycleModels;
//            for (SubCycleModel subCycleModel : subCycleModels) {
//                rightTable.getChildren().add(new Label(subCycleModel.name));
//            }
//        }

//        if (cycleModel != null && !cycleModel.subCycleModels.isEmpty()) {
//            for (SubCycleModel subCycleModel : cycleModel.subCycleModels) {
//                leftTable.getChildren().add(new Label(subCycleModel.name));
//            }
//
//        }
        subCycleModels = Library.LOADED_LIBRARY.subCycleModelList != null ? Library.LOADED_LIBRARY.subCycleModelList : new ArrayList<>(); //Получаем список подциклов
        Label titleCycle = new Label("Редактирования цикла - " + cycleModel.name);
        titleCycle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-padding: 0 0 20 0;");
        addBtn.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label titleRight = new Label("Список подциклов");
        titleRight.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-padding: 0 0 20 0;");

        rightBox.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, -50%); -fx-padding: 5px");
        leftBox.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, -50%); -fx-padding: 5px");

        updateCycle(cycleModel); //Выводим список подциклов c повторениями в левой таблице
//        if(!subCycleOnCycle.isEmpty()) {
//            for(int i = 0; i < subCycleOnCycle.size(); i++) {
//                SubCycleTableRow subCycleTableRow = new SubCycleTableRow(i + 1, subCycleOnCycle.get(i).name, subCycleOnCycle.get(i).repeat);
//            }
//
//        }

//        for (SubCycleModel subCycleModel : subCycleModels) {
//            SubCycleTableRow subCycleTableRow = new SubCycleTableRow(1, subCycleModel.name, -1, subCycleModel);
//            rightTable.getChildren().add(subCycleTableRow);
////            rightTable.getChildren().add(new Label(subCycleModel.name));
//        }

        for (int i = 0; i < subCycleModels.size(); i++) { //Выводим список подциклов в правой таблице
            SubCycleTableRow subCycleTableRow = new SubCycleTableRow((i + 1), subCycleModels.get(i).name, -1, cycleModel, subCycleModels.get(i));
            rightTable.getChildren().add(subCycleTableRow);
        }

        addBtn.setOnAction(event -> {
            if (selectedSubCycleRow != null) {
                cycleModel.subCycleMap.put(selectedSubCycleRow.subCycleModel.uuid, 0);
            }
            updateCycle(cycleModel);
        });
//        if (ev.getClickCount() == 2) {
//            System.out.println("double click");
//            cycleModel.subCycleMap.put(subCycleModel.uuid, Integer.parseInt(repeatField.getText()));
//        } else {
        leftBox.getChildren().addAll(titleCycle, leftTable);
        rightBox.getChildren().addAll(titleRight, rightTable);
        this.setSpacing(10);
        this.paddingProperty().setValue(new Insets(5));
        this.getChildren().addAll(leftBox, addBtn, rightBox);

        Scene scene = new Scene(this, 700, 500);
        scene.getStylesheets().add("/main.css");
        Stage stage = new Stage();
        stage.setTitle("Выбор подциклов");
        stage.setScene(scene);
        stage.show();
    }

    void updateCycle(CycleModel cycleModel) {

        if (cycleModel != null && !cycleModel.subCycleMap.isEmpty()) { // Проходим по списку подциклов в цикле и ищем в списке подциклов из библиотеки для вывода информации

            leftTable.getChildren().clear();
            cycleModel.subCycleMap.forEach((key, value) -> {
                for (int i = 0; i < subCycleModels.size(); i++) {
                    if (subCycleModels.get(i).uuid.equals(key)) {
                        SubCycleTableRow subCycleTableRow = new SubCycleTableRow((i + 1), subCycleModels.get(i).name, value, cycleModel, subCycleModels.get(i));
                        leftTable.getChildren().add(subCycleTableRow);
                    }
                }
//                subCycleModels.forEach(subCycleModel -> {
//                    if (subCycleModel.uuid.equals(key)) {
////                        subCycleOnCycle.add(subCycleModel);
//                        SubCycleTableRow subCycleTableRow = new SubCycleTableRow(1, subCycleModel.name, value);
//                        leftTable.getChildren().add(subCycleTableRow);
////                        leftTable.getChildren().add(new HBox(new Label(subCycleModel.name), new Label("Repeat: " + value)));
//                    }
//                });
            });
        } else {
            leftTable.getChildren().clear();
        }
    }


    class SubCycleTableRow extends HBox {
        SubCycleModel subCycleModel;
        Label numberLb = new Label();
        TextField nameField = new TextField();
        TextField repeatField = new TextField();

        SubCycleTableRow(int number, String name, int repeat, CycleModel cycleModel, SubCycleModel subCycleModel) {
            this.subCycleModel = subCycleModel;
            styleNodes();
            numberLb.setText(String.valueOf(number));
            nameField.setText(name.toString());
            repeatField.setText(String.valueOf(repeat));
            this.setSpacing(10);
            this.alignmentProperty().set(Pos.CENTER_LEFT);

            if (repeat != -1) {
                this.getChildren().addAll(numberLb, nameField, repeatField);
            } else {
                this.getChildren().addAll(numberLb, nameField);
            }

            ContextMenu contextMenu = new ContextMenu();
            MenuItem deleteItem = new MenuItem("Удалить");

            this.addEventFilter(MouseEvent.MOUSE_CLICKED, ev -> {
                if (repeat != -1) { //Слушатель будет работать на подцикле в цикле
                    if (selectedSubCycleOnCycleRow != null) {
                        selectedSubCycleOnCycleRow.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, 27%);");
                        if (ev.getButton() == MouseButton.SECONDARY) {
                            ev.consume();
                            double x = ev.getScreenX();
                            double y = ev.getScreenY();
                            deleteItem.setOnAction(e -> {
                                cycleModel.subCycleMap.remove(selectedSubCycleOnCycleRow.subCycleModel.uuid);
                                updateCycle(cycleModel);
                            });
                            contextMenu.getItems().clear();
                            contextMenu.getItems().addAll(deleteItem);
                            contextMenu.show(selectedSubCycleOnCycleRow, x, y);

                        }
                    }
                    selectedSubCycleOnCycleRow = this;
                } else { //Слушатель будет работать на подцикле в Списке подциклов
                    if (selectedSubCycleRow != null) {
                        selectedSubCycleRow.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, 27%);");
                    }
                    selectedSubCycleRow = this;
                }
                this.setStyle("-fx-border-width: 2px; -fx-border-color: rgba(9,252,9,0.5);");
            });
        }

        void styleNodes() {
            nameField.setContextMenu(new ContextMenu());
            repeatField.setContextMenu(new ContextMenu());

            nameField.setMinWidth(200);
            nameField.setEditable(false);

            repeatField.setMinWidth(50);
            repeatField.setMaxWidth(50);
            repeatField.setAlignment(Pos.CENTER);
        }


    }
}
