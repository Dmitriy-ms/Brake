package ru.volgatest.brake.InsideWindows.Cycle;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.volgatest.brake.InsideWindows.SubCycle.SubCycleModel;

import java.util.ArrayList;
import java.util.List;

public class SelectSubCycleWindow extends SplitPane {

    List<SubCycleModel> subCycleModels = new ArrayList<>();

    VBox rightTable = new VBox(10);
    VBox leftTable = new VBox(10);

    public SelectSubCycleWindow(CycleModel cycleModel, List<SubCycleModel> subCycleModels) {
        if (!subCycleModels.isEmpty()) {
            this.subCycleModels = subCycleModels;
            for (SubCycleModel subCycleModel : subCycleModels) {
                rightTable.getChildren().add(new Label(subCycleModel.name));
            }
        }

        if (cycleModel != null && !cycleModel.subCycleModels.isEmpty()) {
            for (SubCycleModel subCycleModel : cycleModel.subCycleModels) {
                leftTable.getChildren().add(new Label(subCycleModel.name));
            }

        }


        this.getChildren().addAll(leftTable, rightTable);

        Scene scene = new Scene(this, 400, 500);
        scene.getStylesheets().add("/main.css");
        Stage stage = new Stage();
        stage.setTitle("Выбор подциклов");
        stage.setScene(scene);
        stage.show();
    }
}
