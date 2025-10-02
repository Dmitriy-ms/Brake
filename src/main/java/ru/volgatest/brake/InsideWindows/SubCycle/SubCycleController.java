//package ru.volgatest.brake.InsideWindows.SubCycle;
//
//public class SubCycleController {
//    SubCycleWindow subCycleWindow;
//    public SubCycleController(SubCycleWindow subCycleWindow) {
//        this.subCycleWindow = subCycleWindow;
//        subCycleWindow.temperatureBox.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 2) {
//               subCycleWindow.subCycleModel.isTemperatureCheckedEachCycle = !subCycleWindow.subCycleModel.isTemperatureCheckedEachCycle;
//               subCycleWindow.refreshView(subCycleWindow.);
//            }
//        });
//        subCycleWindow.preheatingBox.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 2) {
//               subCycleWindow.subCycleModel.isPreheatingEnabled = !subCycleWindow.subCycleModel.isPreheatingEnabled;
//               subCycleWindow.refreshView();
//            }
//        });
//        subCycleWindow.precoolingBox.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 2) {
//               subCycleWindow.subCycleModel.isPrecoolingEnabled = !subCycleWindow.subCycleModel.isPrecoolingEnabled;
//               subCycleWindow.refreshView(subCycleWindow.subCycleModel);
//            }
//        });
//
//        subCycleWindow.cancelBtn.setOnMouseClicked(event -> subCycleWindow.stage.close());
//    }
//
//}
