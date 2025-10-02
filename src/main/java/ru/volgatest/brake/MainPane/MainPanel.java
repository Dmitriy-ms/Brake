package ru.volgatest.brake.MainPane;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.volgatest.brake.MainPane.Widgets.BlowingHeating;
import ru.volgatest.brake.MainPane.Widgets.Controller.FooterConroller;
import ru.volgatest.brake.MainPane.Widgets.DynamicLoading;
import ru.volgatest.brake.MainPane.Widgets.MedusaGauge;
import ru.volgatest.brake.UI.UIconnector;
import ru.volgatest.brake.UI.Variables;

public class MainPanel extends  AnchorPane{
    public static String CSSPATH = "/main.css";

    public static UIconnector CONNECTOR;
    public static Variables VARIABLES = new Variables();

    public MainPanel() {

        CONNECTOR = new UIconnector();


        DynamicLoading dynamicLoading = new DynamicLoading();
        BlowingHeating blowingHeating = new BlowingHeating();
        MedusaGauge medusaGauge = new MedusaGauge();
//        TestPanelExactUI testPanelExactUI = new TestPanelExactUI();
//        FooterPanel footerPanel = new FooterPanel();
        FooterConroller footerConroller = new FooterConroller();

        this.setBackground(new Background(new BackgroundImage(new Image("/bgr.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)));

//        Image image = new Image("/bgr.jpg");
//        ImageView imageView = new ImageView(image);
//
//        BackgroundFill[] fills = new BackgroundFill[]{new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)};
//        BackgroundImage[] images = new BackgroundImage[]{new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(800, 600, false, false, false, false))};
//        this.setBackground(new Background(fills, images));
//
//        VBox img = new VBox(imageView);

        this.setStyle("-fx-background-image: url('bgr.png');" +
                "-fx-background-position: 99% 15%;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 55% 55%;");

        this.getChildren().addAll(dynamicLoading, blowingHeating, medusaGauge, footerConroller.getFooterPanel());
    }
}
