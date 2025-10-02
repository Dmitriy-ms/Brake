/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 *
 * @author VTARMN013
 */
public class BigToggleButton extends ToggleButton{
    public final static int DEFAULT_PADDING  = 10;
    public final static int DEFAULT_HIGHT  = 65;
    public final static int DEFAULT_WIDTH  = 65;
    
    public ImageView imageView =new ImageView();
    int hight = DEFAULT_HIGHT;
    int width = DEFAULT_WIDTH;
    
    public BigToggleButton(String name, String iconPath){
        this(name,iconPath,DEFAULT_HIGHT,DEFAULT_WIDTH);
    }
    
    public BigToggleButton(String name, String iconPath, int hight, int width){
        this.setMaxSize(width, hight);
        this.setMinSize(width, hight);
        if(iconPath != null){
            this.setImage(iconPath);
            this.setContentDisplay(ContentDisplay.BOTTOM);
        }
        this.setText(name);        
        this.setFont(new Font(8)); 
    }
    
    public void setName(String name){
        this.setText(name);
    }

    public void setImage(String iconPath){
        var image = new Image(iconPath);    
        setImage(image);
    }

    public void setImage(Image image){
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.resize(hight/2, hight/2);
        imageView.setFitHeight(hight/1.8);
        imageView.setFitWidth(hight/1.8);
        imageView.setEffect(null);
        this.setGraphic(imageView);
    }
}
