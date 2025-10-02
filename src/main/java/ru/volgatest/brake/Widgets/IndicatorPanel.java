/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;

import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Map;

/**
 * @author VTARMN013
 */
public class IndicatorPanel extends AnchorPane {
    static public final int PREF_WIDTH = 1860;
    static public final int PREF_HIGHT = 1000;

    ImageView backgroundImage;

    boolean inDeveloperMode;

    Label dragLb = new Label();
    Coordinate dragDelta = new Coordinate(0, 0);

    Map<Node, Coordinate> indicatorsPosition = new java.util.HashMap<Node, Coordinate>();

    InvalidationListener sizeChanged = il -> {
        if (backgroundImage != null) {
            backgroundImage.resize(this.getWidth(), this.getHeight());
            backgroundImage.setFitWidth(this.getWidth());
            backgroundImage.setFitHeight(this.getHeight());
        }
    };

    public IndicatorPanel(boolean inDeveloperMode, String backgroundPath) {
        this.inDeveloperMode = inDeveloperMode;
        this.getChildren().add(dragLb);

        this.widthProperty().addListener(x -> repositionIndicators());
        this.heightProperty().addListener(x -> repositionIndicators());

//        if(backgroundPath != null){
//            Image image = new Image(backgroundPath);
//
//            BackgroundFill[] fills = new BackgroundFill[]{new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)};
//            BackgroundImage[] images = new BackgroundImage[]{new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(PREF_WIDTH, PREF_HIGHT, false, false, false, false))};
//            this.setBackground(new Background(fills, images));
//        }
    }

    public void repositionIndicators() {
        for (Map.Entry<Node, Coordinate> entry : indicatorsPosition.entrySet()) {
            Coordinate coordinate = entry.getValue();
            double adsX = this.getWidth() * coordinate.x;
            double adsY = this.getHeight() * coordinate.y;
            Node indicator = entry.getKey();
            indicator.setLayoutX(adsX);
            indicator.setLayoutY(adsY);
        }

    }
    public void addIndicator(Node indicator, Coordinate coordinate) {
        assert !(inDeveloperMode && coordinate == null);

        this.getChildren().add(indicator);
        if (coordinate == null) {
            return;
        }

//        indicator.setLayoutX(coordinate.x);
//        indicator.setLayoutY(coordinate.y);
        indicatorsPosition.put(indicator, coordinate);

        if (inDeveloperMode) {

            dragLb.setLayoutX(20);
            dragLb.setLayoutY(20);

            indicator.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
                dragDelta.x = indicator.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = indicator.getLayoutY() - mouseEvent.getSceneY();
                mouseEvent.consume();
            });

//            indicator.setOnMousePressed( mouseEvent -> {
//              // record a delta distance for the drag and drop operation.
//              dragDelta.x = indicator.getLayoutX() - mouseEvent.getSceneX();
//              dragDelta.y = indicator.getLayoutY() - mouseEvent.getSceneY();
//              mouseEvent.consume();
//            });

            /*indicator.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseEvent -> {
                indicator.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                indicator.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
                dragLb.setText("X: " + (mouseEvent.getSceneX() + dragDelta.x) + " Y: " + (mouseEvent.getSceneY() + dragDelta.y));
                mouseEvent.consume();
            });*/

            indicator.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseEvent -> {
                // Получаем текущие размеры AnchorPane
                double paneWidth = this.getWidth();
                double paneHeight = this.getHeight();

                // Рассчитываем абсолютные координаты
                double absX = mouseEvent.getSceneX() + dragDelta.x;
                double absY = mouseEvent.getSceneY() + dragDelta.y;

                // Преобразуем в относительные (0.0-1.0)
                double relX = absX / paneWidth;
                double relY = absY / paneHeight;

                // Устанавливаем новую позицию
                indicator.setLayoutX(absX);
                indicator.setLayoutY(absY);

                // Выводим относительные координаты (форматированные)
                dragLb.setText(String.format("Относительные координаты: X: %.3f, Y: %.3f", relX, relY));

                mouseEvent.consume();
            });

//            indicator.setOnMouseDragged(mouseEvent -> {
//                indicator.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
//                indicator.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
//                dragLb.setText("X: "+(mouseEvent.getSceneX() + dragDelta.x) + " Y: "+ (mouseEvent.getSceneY() + dragDelta.y));
//                mouseEvent.consume();
//            });

        }
    }
    //public void setNewCoordinate()

    public void setFixedSize(int Width, int Height) {
        this.setMinSize(Width, Height);
        this.setMaxSize(Width, Height);
        if (backgroundImage != null) {
            backgroundImage.resize(this.getWidth(), this.getHeight());
            backgroundImage.setFitWidth(this.getWidth());
            backgroundImage.setFitHeight(this.getHeight());
        }
    }
    /*
    public void setBackground(String iconPath){
        backgroundImage = new ImageView(new Image(iconPath));
        this.getChildren().add(backgroundImage);
    }
    */
}
