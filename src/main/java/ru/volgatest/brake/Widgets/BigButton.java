/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;

import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author VTARMN013
 */
public class BigButton extends Button{
    public final static String WAITING_CONNECTION_ICON_PATH = "/GUI/artefacts/common/Loading.png";
    public final static Color DEFAULT_COLOR = Color.DARKSLATEBLUE;
    
    
    public final static int DEFAULT_PADDING  = 10;
    public final static int DEFAULT_HIGHT  = 65;
    public final static int DEFAULT_WIDTH  = 65;
    public final static Color borderColor = Color.DARKSLATEGREY;
    public ImageView imageView =new ImageView();
    Glow glow = new Glow(0);   
    FadeTransition ft;
         
    int hight = DEFAULT_HIGHT;
    int width = DEFAULT_WIDTH;
    //public Color imageColor = Color.DARKSLATEGREY;
    
    public BigButton(String name, String iconPath){
        this(name,iconPath,DEFAULT_HIGHT,DEFAULT_WIDTH);
    }
    
    public BigButton(String name, String iconPath, int hight, int width){
        this.hight = hight;
        this.width = width;
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

    public void setImage(Image image, int glowIntencity){
        setImage(image);
        glow.setLevel(glowIntencity);
        imageView.setEffect(glow); 
    }
    
    public void setImage(Image image){
        imageView.setImage(image);
        imageView.resize(hight/2, hight/2);
        imageView.setFitHeight(hight/1.8);
        imageView.setFitWidth(hight/1.8);
        imageView.setEffect(null);
        this.setGraphic(imageView);
    }

    public void setBilinking(int reps,int cycleMs, Runnable onBlinkingFinish){
        //glow.setLevel(10);  
        //imageView.setEffect(glow); 
                
        ft = new FadeTransition(Duration.millis(cycleMs), imageView);
        ft.setDelay(Duration.millis(100));
        ft.setFromValue(1);
        ft.setToValue(0.2);
        ft.setCycleCount(reps);
        ft.setAutoReverse(true);   
        ft.setOnFinished((e)-> {
            imageView.setEffect(null);
            if(onBlinkingFinish!=null) onBlinkingFinish.run();
        });
        ft.play();
    }
    
    public void setGlowing(int reps,int cycleMs){
        imageView.setEffect(glow); 
        
        final Timeline timeline = new Timeline();
        timeline.setDelay(Duration.millis(100));
        timeline.setCycleCount(reps);
        timeline.setAutoReverse(true);
        KeyFrame kf1 = new KeyFrame(Duration.millis(cycleMs), new KeyValue(glow.levelProperty(), 1, Interpolator.EASE_BOTH));
        //KeyFrame kf2 = new KeyFrame(Duration.millis(cycleMs), new KeyValue(glow.levelProperty(), 0.8, Interpolator.EASE_BOTH));
        timeline.getKeyFrames().add(kf1);
        //timeline.getKeyFrames().add(kf2);
        timeline.setOnFinished((e)-> imageView.setEffect(null));
        timeline.play();
    }
    
    public void stopAnimation(){
        ft.stop();
    }
    
//    public StrokeTransition​ setBilinking(int cycleMs, Color color){
//        return new StrokeTransition();
//    }
    
    public void animateWaitState(boolean animate){
        
    }
    public Button makeBindedCopy(int fontSize,ContentDisplay display){
        Button copyButton = new Button();       
        copyButton.setFont(new Font(fontSize));
        copyButton.setContentDisplay(display);
        
        ImageView copyImageView = new ImageView();        
        //copyImageView.resize(20, 20); 
        copyImageView.setFitHeight(20);
        copyImageView.setFitWidth(20);
        copyImageView.effectProperty().bind(imageView.effectProperty());
        copyImageView.imageProperty().bind(imageView.imageProperty());
        copyImageView.effectProperty().bind(imageView.effectProperty());
        copyButton.textProperty().bind(this.textProperty());
        copyButton.setGraphic(copyImageView);
        return copyButton;
    }
    public void setBorderColor(Color color){
        if(color == Color.RED){
            setStyle("-fx-background-color: red;");
        }else if(color == Color.YELLOW){
            setStyle("-fx-background-color: sandybrown;");
        }else if(color == Color.GREEN){
            setStyle("-fx-background-color: green;");
        }else
            setStyle("-fx-background-color: LIGHTSTEELBLUE;");
    }
}


/*
public void setImage(Image image, Color color){
    //imageColor = color;        
    imageView.setImage(image);
    imageView.resize(DEFAULT_HIGHT/2, DEFAULT_HIGHT/2);

    ColorAdjust colorAdjust = new ColorAdjust();  
    System.out.println("R: "+color.getRed());
    System.out.println("G: "+color.getGreen());
    System.out.println("B: "+color.getBlue());
    System.out.println("hue: "+color.getHue());        
    double hueCh = (color.getHue()/180)-1;
    System.out.println("hue calculated: "+ hueCh);
    System.out.println("Brightness: "+Color.RED.getBrightness());
    System.out.println("Saturation: "+Color.RED.getSaturation());                        
    double hue = mapHueToAdjust( (color.getHue() + 180) % 360, 0, 360, -1, 1);
    colorAdjust.setHue(hue);
    double saturation = color.getSaturation();
    colorAdjust.setSaturation(1);
    double brightness = mapHueToAdjust( color.getBrightness(), 0, 1, -1, 0);
    colorAdjust.setBrightness(0.5);

    imageView.setEffect(colorAdjust);
    this.setGraphic(imageView);    
}

public static double mapHueToAdjust(double value, double start, double stop, double targetStart, double targetStop) {
    return targetStart + (targetStop - targetStart) * ((value - start) / (stop - start));
}
public float getHue(double red, double green, double blue) {

    double min = Math.min(Math.min(red, green), blue);
    double max = Math.max(Math.max(red, green), blue);

    if (min == max) {
        return 0;
    }

    double hue = 0f;
    if (max == red) {
        hue = (green - blue) / (max - min);

    } else if (max == green) {
        hue = 2f + (blue - red) / (max - min);

    } else {
        hue = 4f + (red - green) / (max - min);
    }

    hue = hue * 60;
    if (hue < 0) hue = hue + 360;

    return Math.round(hue);
}

*/