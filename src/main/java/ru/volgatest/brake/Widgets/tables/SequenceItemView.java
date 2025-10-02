/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.tables;

import javafx.scene.layout.Pane;

/**
 *
 * @author VTARMN013
 */
public interface SequenceItemView {
    public static final int ELEMENT_HIGHT = 30;
    
    int getIndex();
    void setIndex(int index);
    void setSelected(boolean isSelected);
    boolean isSelected();
    Pane getPane();
    float getHight();
}
