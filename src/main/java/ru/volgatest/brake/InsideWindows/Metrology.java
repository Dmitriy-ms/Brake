package ru.volgatest.brake.InsideWindows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.volgatest.brake.Widgets.BigToggleButton;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Metrology extends VBox {

    Label timeLb = new Label("Время: ");
    TextField timeTf = new TextField();

    BigToggleButton recordBtn = new BigToggleButton("", null);
    Button uploadBtn = new Button("Загрузить испытание");

    public Metrology() {
        HBox timeBox = new HBox(10,timeLb, timeTf, recordBtn);
        timeBox.setAlignment(Pos.CENTER_LEFT);

        recordBtn.setMinSize(50, 50);
        recordBtn.setMaxSize(50, 50);
        timeLb.setMinSize(100, 30);
        setImageBigButton(recordBtn, "icons/record.png");


        uploadBtn.setMinSize(200, 30);

        InformationRow row1 = new InformationRow("Момент силы:", "88", "Nm");
        InformationRow row2 = new InformationRow("Угол поворота:", "0,62", "deg");
        InformationRow row3 = new InformationRow("Частота вращения:", "0", "rpm");
        InformationRow row4 = new InformationRow("Количество оборотов:", "53,81", "");

        this.setSpacing(10);
        this.getChildren().addAll(timeBox,uploadBtn, row1, row2, row3, row4);
        Scene scene = new Scene(this, 700, 300);
        scene.getStylesheets().add("/main.css");
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // <-- главное
        stage.setTitle("Метроло́гия");
        stage.setScene(scene); // Размер можно подогнать
        stage.showAndWait(); // <-- блокирует остальные окна, пока не закроется
    }

    public void setImageBigButton(BigToggleButton button, String path) {
        try {
            Image imageIcon = getImage(path);
            button.setImage(imageIcon);
        } catch (FileNotFoundException ignored) {

        }
   }
    public Image getImage(String path) throws FileNotFoundException {
        Path pathAbsolute = Paths.get(path).toAbsolutePath();
        if (!Files.exists(pathAbsolute)) {
            throw new FileNotFoundException("Файл не найден: " + pathAbsolute);
        }
        return new Image("file:" + pathAbsolute);
    }

    class InformationRow extends HBox {
        String name;
        String value;
        String unit;
        Label nameLb = new Label();
        Label valueLb = new Label();
        Label unitLb = new Label();

        public InformationRow(String name, String value, String unit) {
            this.name = name;
            this.value = value;
            this.unit = unit;

            nameLb.setMinSize(300, 30);
            nameLb.setMaxSize(300, 30);
            nameLb.setStyle("-fx-font-size: 16px; -fx-alignment: center-left; -fx-font-weight: bold;");

            valueLb.setMinSize(70, 30);
            valueLb.setMaxSize(70, 30);
            valueLb.setStyle("-fx-font-size: 16px; -fx-alignment: center-left; -fx-font-weight: bold;");

            unitLb.setMinSize(50, 30);
            unitLb.setMaxSize(50, 30);
            unitLb.setStyle("-fx-font-size: 16px; -fx-alignment: center-left; -fx-font-weight: bold;");

            nameLb.setText(name);
            valueLb.setText(value);
            unitLb.setText(unit);

            this.getChildren().addAll(nameLb, valueLb, unitLb);
        }
    }

}
