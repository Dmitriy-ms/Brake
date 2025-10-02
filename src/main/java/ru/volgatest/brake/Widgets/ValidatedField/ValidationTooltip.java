/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

/**
 *
 * @author VTARMN013
 */
public class ValidationTooltip {
    public static final int VERTICAL_OFFSET = 20;
    
    public static void showOneTimeTooltip(Control control, String tooltipText) {

        Point2D p = control.localToScreen(5 , 5);
        if(p == null)return; // HACK because installation process
        final Tooltip customTooltip = new Tooltip(tooltipText);
        customTooltip.setAutoHide(false);
        
        customTooltip.show(control,p.getX(),p.getY()+VERTICAL_OFFSET);

        PauseTransition pt = new PauseTransition(Duration.millis(3000));
        pt.setOnFinished(e->{
            customTooltip.hide();
        });
        pt.play();
    }
}
