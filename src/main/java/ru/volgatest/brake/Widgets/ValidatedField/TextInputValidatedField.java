/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author VTARMN013
 */
public class TextInputValidatedField  extends HBox{
    static public final int HIGHT = 20;
    static public final int WIDTH = 70;
    static public final int UNIT_WIDTH = 50;

    public TextField textField = new TextField();
    public Label name;
    public Label unitLb;
    
    //SimpleBooleanProperty textMakeSense = new SimpleBooleanProperty();
    TextValidatedValue field;
    public SimpleStringProperty problemWithText = new SimpleStringProperty();
    //StringBinding textFieldBinding;
    
    Runnable mouseClickedListener;
        
    
    
    public TextInputValidatedField(String nameString, TextValidatedValue value, String unit){
        this(nameString);
        setValidatedValue(value);
        
        if(unit != null){
            unitLb = new Label(unit);
            unitLb.setMinSize(UNIT_WIDTH, HIGHT);
            unitLb.setPrefSize(UNIT_WIDTH, HIGHT);
            this.getChildren().add(unitLb);
        }
    }
    
    public TextInputValidatedField(String nameString, TextValidatedValue value){
        this(nameString);
        setValidatedValue(value);
    }    
    
    public TextInputValidatedField(String nameString, String unit){
        this(nameString);
        
        if(unit != null){
            unitLb = new Label(unit);
            unitLb.setMinSize(UNIT_WIDTH, HIGHT);
            unitLb.setPrefSize(UNIT_WIDTH, HIGHT);
            this.getChildren().add(unitLb);
        }
        HBox.setMargin(unitLb, new Insets(0, 0, 0, 5));
    }
    public TextInputValidatedField(String nameString, String unit, String symbol){
        this(nameString);

        if(unit != null){
            unitLb = new Label(unit);
            unitLb.setMinSize(UNIT_WIDTH, HIGHT);
            unitLb.setPrefSize(UNIT_WIDTH, HIGHT);
            this.getChildren().add(unitLb);
        }
        if(symbol != null){
            Label symbolLb = new Label(symbol);
            symbolLb.setMinSize(UNIT_WIDTH, HIGHT);
            symbolLb.setPrefSize(UNIT_WIDTH, HIGHT);
            symbolLb.setPadding(new Insets(0, 0, 0, 30));
            this.getChildren().add(1,symbolLb);
        }
    }
        
    public TextInputValidatedField(String nameString){
        textField.setMinSize(WIDTH, HIGHT);
        textField.setPrefSize(WIDTH, HIGHT);
        textField.setOnKeyReleased( (KeyEvent ke) -> {
            onKeyReleasedListener(ke);
        });
        
        if(nameString!=null){
            name = new Label(); 
            name.setMinSize(WIDTH, HIGHT);
            name.setPrefSize(WIDTH, HIGHT);
            name.setText(nameString);
            name.setOnMouseClicked((e) -> onMouseClickedListener());
            this.getChildren().addAll(name);
        }      
        
        textField.setOnMouseClicked((e) -> onMouseClickedListener());        
        this.getChildren().addAll(textField);
        //this.setOnMouseClicked((e) -> {if(mouseClickedListener!=null) mouseClickedListener.run();});
        //this.setStyle("-fx-border-color: #373e43;");
    }
    
    private void onMouseClickedListener(){
        if(mouseClickedListener!=null) {
            mouseClickedListener.run();
        }
    }
    
    public void setLastValidValue(){
        this.textField.setText(field.getValueAsString());        
        onKeyReleasedListener(null);
    }
    
    public void setValidatedValue(TextValidatedValue value){
        this.field = value;
        this.textField.setText(value.getValueAsString());
        this.textField.styleProperty().setValue(getTextFieldBackground());
        setFieldStyleBinding();        
    }
    
    public void setDirectValue(String text){
        field.setValueAsString(text);
        this.textField.setText(text);
        //this.textField.styleProperty().setValue(getTextFieldBackground());
        refreshValidation();
    }

    private void onKeyReleasedListener(KeyEvent ke){
        problemWithText.setValue(field.testForInvalidStringRepresentation(textField.getText()));

        if(problemWithText.getValue()==null){
            field.setValueAsString(textField.getText());
            problemWithText.setValue(field.validation().getInvalidDescription());
        }

        if(ke!= null && ke.getCode().equals(KeyCode.ENTER)){
            this.requestFocus();
        }
    }
    
    private void setFieldStyleBinding(){
        StringBinding binding = Bindings.createStringBinding(()-> getTextFieldBackground(), problemWithText, field.validation()); 
        this.textField.styleProperty().bind(binding); 
    }
    
    private String getTextFieldBackground(){
        if(problemWithText.getValue()!=null){
            ValidationTooltip.showOneTimeTooltip(textField,problemWithText.getValue());
            textField.setTooltip(new Tooltip(problemWithText.getValue()));
            return "-fx-background-color: indianred;";
        }
        else if(!field.validation().isValid()){
            ValidationTooltip.showOneTimeTooltip(textField,field.validation().getInvalidDescription());
            textField.setTooltip(new Tooltip(field.validation().getInvalidDescription()));
            return "-fx-background-color: sandybrown;";
        }
        textField.setTooltip(null);
        return "-fx-background-color: derive(-fx-base, 35%);";
    }
    
    public void refreshValidation(){
        field.valueValidation().refreshValidation();
    }
    
    public void setMouseClickedListener(Runnable mouseClickedListener){
        this.mouseClickedListener = mouseClickedListener;
    }
    
    public void setFieldSize(int width, int hight){
        textField.setMinSize(width, hight);
        textField.setPrefSize(width, hight);
        //name.setMinSize(width, hight);
        //name.setPrefSize(width, hight);
    }
    
    public void setFieldFixedSize(int width, int hight){
        this.getChildren().clear();
        
        textField.setMinSize(width, hight);
        textField.setMaxSize(width, hight);
        textField.setPrefSize(width, hight);
        if(name != null){
            name.setMinHeight(hight);
            name.setMaxHeight(hight);
            name.setPrefHeight(hight);
            this.getChildren().add(name);
        }      
        this.getChildren().add(textField);
    }

    public void setNameWidth(int width){
        name.setMinWidth(width);
        name.setMaxWidth(width);
        name.setPrefWidth(width);
    }
    public void setSizeName(int width, int height){
        name.setMinWidth(width);
        name.setMaxWidth(width);
        name.setPrefWidth(width);

        name.setMinHeight(height);
        name.setMaxHeight(height);
        name.setPrefHeight(height);
    }
    public void setUnitWidth(int width){
        unitLb.setMinWidth(width);
        unitLb.setMaxWidth(width);
        unitLb.setPrefWidth(width);
    }

    public void setFieldSize(int minWidth, int minHight, int prefWidth, int prefHight){
        textField.setMinSize(minWidth, minHight);
        textField.setPrefSize(prefWidth, prefHight);
        //name.setMinSize(width, hight);
        //name.setPrefSize(width, hight);
    }
    
    public String getInvalidationMsg(){
        return problemWithText.getValue();
    }
    
    public boolean isValid(){
        return problemWithText.getValue() == null;
    }
    
}
