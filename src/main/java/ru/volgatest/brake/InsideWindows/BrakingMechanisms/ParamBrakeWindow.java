package ru.volgatest.brake.InsideWindows.BrakingMechanisms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ru.volgatest.brake.ObjectsLibrary.Library;
import ru.volgatest.brake.Widgets.LimitedFloatField;

public class ParamBrakeWindow extends VBox {

   public Stage stage = new Stage();
//    BrakeModel brakeModel;

    Label title = new Label("Параметры тормозного механизма");
    Label name = new Label("Наименование");
    //    Label manufacturerLb = new Label("Изготовитель");
//    Label brand = new Label("Марка");
//    Label type = new Label("Тип");
//    Label additionalInformation = new Label("Дополнительные сведения");
//
    TextField nameField = new TextField();
//    TextField manufacturerField = new TextField();
//    TextField brandField = new TextField();
//    TextField typeField = new TextField();
//    TextField additionalInformationField = new TextField();
//
//    LimitedFloatField weightWheel;


    LimitedFloatField rollingRadius;
    LimitedFloatField minTemperature;
    LimitedFloatField maxTemperature;
    LimitedFloatField maxBrakingForce;
    LimitedFloatField maxStaticLoad;
    LimitedFloatField minAirPressure;
    LimitedFloatField minAirTemperature;
    LimitedFloatField maxAirTemperature;


    Button okBtn = new Button("OK");
    Button cancelBtn = new Button("Отмена");

    public ParamBrakeWindow(BrakeModel brakeModel) {
//        weightWheel = new LimitedFloatField("Масса на колесо", "kg", 0, 500, (float)brakeModel.weightWheel);
        rollingRadius = new LimitedFloatField("Радиус качения", "m", 0, 500, brakeModel.rollingRadius);
        minTemperature = new LimitedFloatField("Минимальная температура", "°C", 0, 500, brakeModel.minTemperature);
        maxTemperature = new LimitedFloatField("Максимальная температура", "°C", 0, 500, brakeModel.maxTemperature);
        maxBrakingForce = new LimitedFloatField("Максимальное усилие торможения", "N", 0, 500, brakeModel.maxBrakingForce);
        maxStaticLoad = new LimitedFloatField("Максимальное усилие статического нагружения", "N", 0, 500, brakeModel.maxStaticLoad);
        minAirPressure = new LimitedFloatField("Минимальное давление воздуха", "Pa", 0, 500, brakeModel.minAirPressure);
        minAirTemperature = new LimitedFloatField("Минимальная температура воздуха", "°C", 0, 500, brakeModel.minAirTemperature);
        maxAirTemperature = new LimitedFloatField("Максимальная температура воздуха", "°C", 0, 500, brakeModel.maxAirTemperature);
        if (brakeModel != null) {
            nameField.setText(brakeModel.name);
//            manufacturerField.setText(brakeModel.manufacturer);
//            brandField.setText(brakeModel.brand);
//            typeField.setText(brakeModel.type);
//            additionalInformationField.setText(brakeModel.additionalInformation);
        }


        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-padding: 5px; -fx-max-width: 300px; -fx-alignment: center;");
        title.setTextAlignment(TextAlignment.CENTER);


        nameField.textProperty().addListener(observable -> brakeModel.name = nameField.getText());
//        manufacturerField.textProperty().addListener(observable -> {
//            brakeModel.manufacturer = manufacturerField.getText();
//
//        });
//        brandField.textProperty().addListener(observable -> brakeModel.brand = brandField.getText());
//        typeField.textProperty().addListener(observable -> brakeModel.type = typeField.getText());
//        additionalInformationField.textProperty().addListener(observable -> brakeModel.additionalInformation = additionalInformationField.getText());
//
//        weightWheel.value.addListener((observable, oldValue, newValue) -> brakeModel.weightWheel = newValue.doubleValue());
        rollingRadius.value.addListener((observable, oldValue, newValue) -> brakeModel.rollingRadius = newValue.floatValue());
        minTemperature.value.addListener((observable, oldValue, newValue) -> brakeModel.minTemperature = newValue.floatValue());
        maxTemperature.value.addListener((observable, oldValue, newValue) -> brakeModel.maxTemperature = newValue.floatValue());
        maxBrakingForce.value.addListener((observable, oldValue, newValue) -> brakeModel.maxBrakingForce = newValue.floatValue());
        maxStaticLoad.value.addListener((observable, oldValue, newValue) -> brakeModel.maxStaticLoad = newValue.floatValue());
        minAirPressure.value.addListener((observable, oldValue, newValue) -> brakeModel.minAirPressure = newValue.floatValue());
        minAirTemperature.value.addListener((observable, oldValue, newValue) -> brakeModel.minAirTemperature = newValue.floatValue());
        maxAirTemperature.value.addListener((observable, oldValue, newValue) -> brakeModel.maxAirTemperature = newValue.floatValue());


//        HBox nameBox = createParamBrake(name, nameField);
        HBox nameBox = new HBox(name, nameField);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        nameField.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(nameField, Priority.ALWAYS);
//        nameBox.widthProperty().addListener(value -> System.out.println("Name box width: " + nameBox.getWidth()));
//        rollingRadius.widthProperty().addListener(value -> System.out.println("Rolling radius width: " + rollingRadius.getWidth()));
//        HBox manufacturerBox = createParamBrake(manufacturerLb, manufacturerField);
//        HBox brandBox = createParamBrake(brand, brandField);
//        HBox typeBox = createParamBrake(type, typeField);
//        HBox additionalInformationBox = createParamBrake(additionalInformation, additionalInformationField);
//        VBox root = new VBox(10, nameBox, manufacturerBox, brandBox, typeBox, additionalInformationBox, weightWheel, rollingRadius);
        VBox root = new VBox(10, nameBox, rollingRadius, minTemperature, maxTemperature, maxBrakingForce, maxStaticLoad, minAirPressure, minAirTemperature, maxAirTemperature);
        root.setStyle("-fx-padding: 5; -fx-alignment: center; -fx-border-width: 1px; -fx-border-color: derive(-fx-base, -20%); -fx-border-radius: 1px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox buttonHbox = new HBox(10, spacer, okBtn, cancelBtn);
        buttonHbox.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(buttonHbox, new Insets(10, 0, 0, 0));

        cancelBtn.setOnAction(event -> {
            stage.close();
        });

        this.setSpacing(10);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(title, root, buttonHbox);

        okBtn.setOnMouseClicked(event -> {
            Library.saveData(Library.LOADED_LIBRARY.brakeModelList);
            stage.close();
        });

        cancelBtn.setOnAction(event -> stage.close());

        setSizeNodes();

        Scene scene = new Scene(this, 520, 470);
        scene.widthProperty().addListener(value -> System.out.println("Scene width: " + scene.getWidth()));
        scene.heightProperty().addListener(value -> System.out.println("Scene height: " + scene.getHeight()));
        scene.getStylesheets().add("/main.css");
        stage.setTitle("Параметры тормозного механизма");
        stage.setScene(scene);
        stage.show();

    }

    void setSizeNodes() {
        int width = 300;
        int height = 25;
        name.setMinWidth(width);
        nameField.setMinHeight(height);
//        nameField.setMaxWidth(70);
//        manufacturerLb.setPrefWidth(widthLb);
//        brand.setPrefWidth(widthLb);
//        type.setPrefWidth(widthLb);
//        additionalInformation.setPrefWidth(widthLb);
//        weightWheel.setNameWidth(widthLb);
//        rollingRadius.setNameWidth(widthLb);


        rollingRadius.setNameWidth(width);
        rollingRadius.textField.setMinHeight(height);

        minTemperature.setNameWidth(width);
        minTemperature.textField.setMinHeight(height);

        maxTemperature.setNameWidth(width);
        maxTemperature.textField.setMinHeight(height);

        maxStaticLoad.setNameWidth(width);
        maxStaticLoad.textField.setMinHeight(height);

        maxBrakingForce.setNameWidth(width);
        maxBrakingForce.textField.setMinHeight(height);

        minAirPressure.setNameWidth(width);
        minAirPressure.textField.setMinHeight(height);

        minAirTemperature.setNameWidth(width);
        minAirTemperature.textField.setMinHeight(height);

        maxAirTemperature.setNameWidth(width);
        maxAirTemperature.textField.setMinHeight(height);

        okBtn.setMinSize(70, height);
        cancelBtn.setMinSize(70, height);
    }

    public HBox createParamBrake(Label name, TextField field) {
        HBox hbox = new HBox(10, name, field);
        return hbox;
    }
}
