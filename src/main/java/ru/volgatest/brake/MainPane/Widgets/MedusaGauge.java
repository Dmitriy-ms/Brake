package ru.volgatest.brake.MainPane.Widgets;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Section;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MedusaGauge extends MovingPane {

    public MedusaGauge() {
        DemoGauge demoGaugeOneOne = new DemoGauge(0, 100, 10, "km/h", "Скорость", new Section(80, 100, Color.RED));
        DemoGauge demoGaugeOneTwo = new DemoGauge(0, 450, 50, "rmp", "Скорость вала", null);

        DemoGauge demoGaugeTwoOne = new DemoGauge(0, 10, 1, "bar", "Пневматическое", null);
        DemoGauge demoGaugeTwoTwo = new DemoGauge(0, 250, 50, "bar", "Гидравлическое", null);
        DemoGauge demoGaugeTwoThree = new DemoGauge(0, 60000, 10000, "Nm", "Момент силы", null);

        VBox gaugeOne = new VBox(demoGaugeOneOne, demoGaugeOneTwo); //верхний блок из двух спидометров
        gaugeOne.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, -20%); -fx-alignment: center;");

        Label gaugeTwoHBoxTitle = new Label("Давление ГТЦ");
        gaugeTwoHBoxTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        HBox gaugeTwoHbox = new HBox(demoGaugeTwoOne, demoGaugeTwoTwo); //горизонтальный блок с двумя спидометрами
        VBox gaugeTwo = new VBox(gaugeTwoHBoxTitle,gaugeTwoHbox, demoGaugeTwoThree); //нижний блок с тремя спидометрами
        gaugeTwo.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, -20%); -fx-alignment: center;");


        VBox vBoxRootOne = new VBox(10, gaugeOne, gaugeTwo);
        vBoxRootOne.setStyle("-fx-border-width: 2px; -fx-border-color: derive(-fx-base, -20%); -fx-alignment: center;");


        this.getChildren().addAll(vBoxRootOne);
    }

    class DemoGauge extends VBox {
        Gauge gauge = new Gauge();

        public DemoGauge(int minValue, int maxValue, int initValue, String unit, String title, Section redZone) {

            gauge.setMinValue(minValue);
            gauge.setMaxValue(maxValue);
            gauge.setMajorTickSpace(initValue);
            gauge.setValue(0);
            gauge.setUnit(unit);
            gauge.setTitle(title);
            gauge.setAnimated(true);
            gauge.setAutoScale(true);
//            gauge.setSubTitle("SUBTITLE");

            gauge.setValueColor(Color.WHITE);
            gauge.setUnitColor(Color.WHITE);
            gauge.setTitleColor(Color.WHITE);
            gauge.setSubTitleColor(Color.WHITE);
            gauge.setBarColor(Color.WHITE);
            gauge.setNeedleColor(Color.WHITE);
            gauge.setThresholdColor(Color.WHITE);
            gauge.setTickLabelColor(Color.WHITE);
            gauge.setTickMarkColor(Color.WHITE);


            if (redZone != null) {
                gauge.getSections().add(redZone);
                gauge.setSectionsVisible(true); // <-- Важно!
            }

            this.setStyle("-fx-max-width: 230; -fx-max-height: 230;");
//            this.setStyle("-fx-font-size: 4px;");
            this.getChildren().add(gauge);
        }
    }
}


//    public VBox getGaugeVbox() {
//        Gauge gauge = new Gauge();
//        gauge.setMinValue(0);
//        gauge.setMaxValue(100);
//        gauge.setValue(10);               // начальное значение
//        gauge.setUnit("°C");
//        gauge.setTitle("Температура");
//        gauge.setAnimated(true);          // включить анимацию
//        gauge.setAutoScale(true);
//
////        Button button = new Button("Поднять до 50°C");
////        button.setOnAction(e -> gauge.setValue(50));  // ← плавное обновление
////
////        Button button1 = new Button("Поднять до 100°C");
////        button1.setOnAction(e -> gauge.setValue(100));
////
////        Button button2 = new Button("Опустить до 0°C");
////        button2.setOnAction(e -> gauge.setValue(0));
//
//        Section redZone = new Section(80, 100, Color.RED);
//        gauge.getSections().add(redZone);
//        gauge.setSectionsVisible(true);
//
//
//        VBox root = new VBox(10, gauge);
//        root.setStyle("-fx-padding: 10; -fx-alignment: center;");
//        return root;
//    }



