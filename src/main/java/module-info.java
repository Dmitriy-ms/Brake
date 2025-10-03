module ru.volgatest.brake {
    requires javafx.controls;
    requires javafx.fxml;
    requires webcam.capture;
    requires opencv;
    requires java.desktop;
    requires javafx.swing;
    requires Medusa;
    requires com.google.gson;
    requires org.apache.poi.poi;

    opens ru.volgatest.brake.InsideWindows.BrakingMechanisms to com.google.gson;
    opens ru.volgatest.brake.InsideWindows.IntermittentBraking.Cycle to com.google.gson;
    opens ru.volgatest.brake.InsideWindows.IntermittentBraking.SubCycle to com.google.gson;
    opens ru.volgatest.brake.InsideWindows.ContinuousBraking to com.google.gson;

    opens ru.volgatest.brake to javafx.fxml;
    exports ru.volgatest.brake;
    opens ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousCycle to com.google.gson;

}