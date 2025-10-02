/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.Indicators;

import javafx.scene.layout.HBox;
import ru.volgatest.brake.Widgets.Coordinate;

/**
 *
 * @author VTARMN013
 */
public class Indicator extends HBox{
    
    public Coordinate coordinate;
    
    // Development mode
    public Indicator(){
        
    }
    // Set mode
    public Indicator(Coordinate coordinate){
        this.coordinate = coordinate;
    }

}
