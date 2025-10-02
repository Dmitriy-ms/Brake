package ru.volgatest.brake.InsideWindows.BrakingMechanisms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import ru.volgatest.brake.InsideWindows.Limitations.Limits;
import ru.volgatest.brake.ObjectsLibrary.Library;

import java.util.ArrayList;
import java.util.List;

public class BrakingMechanismWidget extends VBox {

    Label brakingMechanismLb = new Label("Тормозной механизм");
    ComboBox<String> brakingMechanismBox = new ComboBox<String>();
    static ObservableList<String> observableBrakes = FXCollections.observableArrayList();
    static List<BrakeModel> brakeList;
    Button libraryBrakingMechanismBtn = new Button("Библиотека механизмов");
    Button limitsBtn = new Button("Ограничения");

    public BrakeModel selectedBrake;
    int selectedIndex = 0;

    public BrakingMechanismWidget() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        brakeList = Library.LOADED_LIBRARY.brakeModelList != null ? Library.LOADED_LIBRARY.brakeModelList : new ArrayList<>();
        HBox hBoxBrakeMech = new HBox(10, brakingMechanismLb, spacer, brakingMechanismBox, libraryBrakingMechanismBtn);

        int minWidthLb = 230;
        limitsBtn.setMinWidth(150);
//        brakingMechanismLb.setMinWidth(minWidthLb);
        brakingMechanismBox.setMinWidth(minWidthLb);

        this.setSpacing(10);
        this.setStyle("-fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 1px; -fx-padding: 5px;");
        this.getChildren().addAll(hBoxBrakeMech, limitsBtn);

        buttonsController();
        updateObservableList();

    }

    public void updateObservableList() {
        observableBrakes.clear();
        for (int i = 0; i < brakeList.size(); i++) {
            observableBrakes.add((i + 1) + ". " + brakeList.get(i).getName());
        }

        setBrakingMechanismBox();

    }

    public void setBrakingMechanismBox() {
        brakingMechanismBox.getItems().setAll(observableBrakes);
        brakingMechanismBox.setValue(observableBrakes.get(selectedIndex)); // устанавливаем выбранный элемент по умолчанию

        brakingMechanismBox.valueProperty().addListener((observable, oldValue, newValue) -> { // Выбор механизма из списка, поиск в библиотеке по имени и запись в переменную selectedBrake
            if (newValue != null) {
                selectedIndex = brakingMechanismBox.getSelectionModel().getSelectedIndex();
                selectedBrake = brakeList.get(selectedIndex);
                System.out.println("Выбран механизм: " + selectedBrake.getName() + " индекс:"+selectedIndex);
//            if (newValue != null) {
//                if (!Library.LOADED_LIBRARY.brakeModelList.isEmpty()) {
//                    selectedBrake = brakeList.stream().filter(brake -> brake.getName()
//                            .equals(newValue))
//                            .findFirst()
//                            .orElse(null);
////                if(selectedBrake != null) System.out.println(selectedBrake.getName());
//                    selectedIndex = observableBrakes.indexOf(newValue);
//                }
//                System.out.println("Выбран механизм: " + newValue);
//            }
            }

        });
    }

    public void buttonsController() {
        limitsBtn.setOnAction(event -> {
            Limits limits = new Limits();
        });
        libraryBrakingMechanismBtn.setOnAction(event -> {
            TableBrake tableBrake = new TableBrake();
            Library.saveData(Library.LOADED_LIBRARY.brakeModelList);
            updateObservableList();

        });
    }
}
