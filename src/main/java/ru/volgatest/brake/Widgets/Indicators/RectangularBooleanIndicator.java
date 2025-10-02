/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.Indicators;

import javafx.scene.layout.Pane;
import ru.volgatest.brake.Widgets.Coordinate;

/**
 * @author VTARMN013
 */
public class RectangularBooleanIndicator extends Pane {
    static public final int NAME_WIDTH = 120;
    static public final int PREF_HIGHT = 25;

    static public final int NAME_WIDTH_SMALL = 120;
    static public final int PREF_HIGHT_SMALL = 20;

    boolean isColorGreen = true;
    public boolean onOff = false;

    public Coordinate coordinate = new Coordinate(0, 0);

    public RectangularBooleanIndicator(int width, int height, int rotateAngle) {

        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.setRotate(rotateAngle);
    }

    public RectangularBooleanIndicator(int width, int height, int rotateAngle, Coordinate coordinate) {
        this.coordinate = coordinate;
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.setRotate(rotateAngle);
    }

    public void setValue(boolean on) {
        this.onOff = on;
        if (on) {
//                this.setStyle("-fx-background-color: rgba(144,238,144,0.5);"); //LIGHTGREEN
            this.setStyle("-fx-background-color: rgba(9,252,9,0.5)"); //LIGHTGREEN
        } else {
            this.setStyle("-fx-background-color: rgba(227,38,54,0.5);"); //LIGHTGREEN
        }
    }

//    public void setValue(boolean on) {
//        if (on) {
//            this.setStyle("-fx-background-color: rgba(9,252,9,0.5); -fx-background-radius: 50%; -fx-border-radius: 50%;"); // LIGHTGREEN
//        } else {
//            this.setStyle("-fx-background-color: rgba(246,0,20,0.5); -fx-background-radius: 50%; -fx-border-radius: 50%;"); // RED
//        }
//    }
}