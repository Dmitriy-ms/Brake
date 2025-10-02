/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.tables;

import ru.volgatest.brake.Widgets.LimitedFloatField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author VTARMN013
 */
public class LearnedSynchTable extends HBox{ 
    public static int ONE_CELL_WIDTH = 80;
    public static int HIGHT = 30;
    
    final float sensorCoeff, sensorOffset;
    
    
    public LearnedSynchTable(int synchNum, boolean lastGearNoNeutral, float[][] values, float sensorCoeff, float sensorOffset){
        super(4);
        this.sensorCoeff = sensorCoeff;
        this.sensorOffset = sensorOffset;
        
        for(int i=0; i<synchNum; i++){
            this.getChildren().add(new SynchLearnedPosition(i,false,values[i]));
        }
    }
    
    public class SynchLearnedPosition extends VBox{
        int index;
        
        //Label leftValueLabel;    
        //Label zeroValueLabel;    
        //Label rightValueLabel;
        
        //Label leftMinMaxLabel,rightMinMaxLabel;
        
        float leftVoltage, middleVoltage, rightVoltage;
        float leftMinValue, leftMaxValue, rightMinValue, rightMaxValue;
        
        VBox leftBox = new VBox();
        VBox rightBox = new VBox();
        
        FieldBox leftPosField;
        FieldBox zeroPosField;
        FieldBox rightPosField;
        
        public SynchLearnedPosition(int index, boolean noNeutral, float[] values){
            this.index = index;
            
            Label indexLabel;
            
            HBox labelBox = new HBox();
            Label leftLabel = createLabel(ONE_CELL_WIDTH, "-1");            
            Label middleLabel = createLabel(ONE_CELL_WIDTH, "0");            
            Label rightLabel = createLabel(ONE_CELL_WIDTH,"+1");
            
            HBox fieldBox = new HBox();
            leftPosField =  new FieldBox(0,values);
            zeroPosField =  new FieldBox(1,values);//,"V", "mm"
            rightPosField =  new FieldBox(2,values);//,"V", "mm"
                        
            
            if(noNeutral){
                indexLabel = createLabel(2 * ONE_CELL_WIDTH, String.valueOf(index+1));
                labelBox.getChildren().addAll(leftLabel,rightLabel);
                fieldBox.getChildren().addAll(leftPosField,rightPosField);
            }else{
                indexLabel = createLabel(3 * ONE_CELL_WIDTH, String.valueOf(index+1));
                labelBox.getChildren().addAll(leftLabel,middleLabel,rightLabel);
                fieldBox.getChildren().addAll(leftPosField,zeroPosField,rightPosField);
            }
            
            //leftMinMaxLabel = createLabel(ONE_CELL_WIDTH, "0");            
            //rightMinMaxLabel = createLabel(ONE_CELL_WIDTH, "0");     
            
            this.getChildren().addAll(indexLabel,labelBox, fieldBox);
            this.setStyle("-fx-border-color: derive(-fx-base, 5%);");
        }
        
        private Label createLabel(int width, String text){
            Label label = new Label(text);
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-font-size: 16; -fx-background-color: derive(-fx-base, 35%); -fx-border-color: derive(-fx-base, 10%);");
            label.setMinSize(width, HIGHT+1);
            label.setMaxSize(width, HIGHT+1);
            return label;
        }
    }
    
    class FieldBox extends VBox{
        LimitedFloatField convertedValueField;
        Label rawValueField = new Label();
        final String RAWunit, convertedUnit;
        
        int index;
        float[] values;
        
        public FieldBox(int index, float[] values){//, String RAWunit, String convertedUnit
            this.RAWunit = "V";
            this.convertedUnit = "mm";
            this.index = index;
            this.values = values;
            
            convertedValueField = new LimitedFloatField(null, -50, 50,0);
            convertedValueField.value.addListener(il -> {                
                setRawValue();
            });
            convertedValueField.setFieldFixedSize(ONE_CELL_WIDTH - 25,HIGHT);
            
            
            rawValueField.setMinSize(ONE_CELL_WIDTH - 25,HIGHT);
            rawValueField.setMaxSize(ONE_CELL_WIDTH - 25,HIGHT);
            rawValueField.setAlignment(Pos.CENTER);
            Label rawUnitLb = new Label(RAWunit);
            rawUnitLb.setMaxSize(23, HIGHT);
            rawUnitLb.setMinSize(23, HIGHT);
            //rawUnitLb.setStyle("-fx-background-color: derive(-fx-base, 35%);");
            HBox rawValueBox = new HBox(rawValueField, rawUnitLb);
            //rawValueBox.setStyle("-fx-border-color: derive(-fx-base, 10%);");
            
            
            convertedValueField.setMinSize(ONE_CELL_WIDTH - 25,HIGHT);
            convertedValueField.setMaxSize(ONE_CELL_WIDTH - 25,HIGHT);
            convertedValueField.setStyle("-fx-background-color: derive(-fx-base, 35%);");
            convertedValueField.setAlignment(Pos.CENTER);
            
            Label convertedUnitLb = new Label(convertedUnit);
            convertedUnitLb.setMaxSize(23, HIGHT);
            convertedUnitLb.setMinSize(23, HIGHT);
            convertedUnitLb.setStyle("-fx-background-color: derive(-fx-base, 35%);");
            HBox convertedValueBox = new HBox(convertedValueField,convertedUnitLb);
            //convertedValueBox.setStyle("-fx-border-color: derive(-fx-base, 10%);");
            
            setConvertedValue();
            String valueString = String.valueOf(values[index]);
            rawValueField.setText(valueString.substring(0, Math.min(valueString.length(), 7)));
            
            this.getChildren().addAll(rawValueBox, convertedValueBox);
            this.setStyle("-fx-border-color: lightgrey;");
        }
        
        private void setConvertedValue(){
            float convertedValue = (values[index]-sensorOffset)*sensorCoeff;
            String valueString = String.valueOf(convertedValue);
            convertedValueField.setDirectValue(valueString.substring(0, Math.min(valueString.length(), 7)));
        }
        private void setRawValue(){
            float rawValue = convertedValueField.value.getValue()/sensorCoeff+sensorOffset;
            values[index] = rawValue;
            String valueString = String.valueOf(rawValue);
            rawValueField.setText(valueString.substring(0, Math.min(valueString.length(), 7)));
        }
    }
}
/*

rawValueField = new LimitedFloatField(null, -50, 50, values[index]);
rawValueField.value.addListener(il -> {
    values[index] = rawValueField.value.getValue();
    setConvertedValue();
});
rawValueField.setFieldFixedSize(ONE_CELL_WIDTH - 25,HIGHT);


float convertedValue = (rawValueField.value.getValue()-sensorOffset)*sensorCoeff;
String valueString = String.valueOf(convertedValue);
convertedValueField.setText(valueString.substring(0, Math.min(valueString.length(), 7)));
*/

                
/*
public void setValues(float leftVoltage, float zeroValue, float rightVoltage){
    this.leftVoltage = leftVoltage;
    this.rightVoltage = rightVoltage;

    leftValueLabel.setText(String.valueOf(leftVoltage));
    rightValueLabel.setText(String.valueOf(rightVoltage));
}        
public void setMinMax(float leftMinValue, float leftMaxValue, float rightMinValue, float rightMaxValue){
    this.leftMinValue = leftMinValue;
    this.leftMaxValue = leftMaxValue;
    this.rightMinValue = rightMinValue;
    this.rightMaxValue = rightMaxValue;

    leftMinMaxLabel.setText(String.valueOf(leftMinValue) +"-"+String.valueOf(leftMaxValue));
    rightMinMaxLabel.setText(String.valueOf(rightMinValue) +"-"+String.valueOf(rightMaxValue));
}
public void markValuesValidity(){
    if(leftVoltage < leftMinValue || leftVoltage> leftMaxValue){
        leftBox.setStyle("-fx-border-color: red;");
    }else{
        leftBox.setStyle("-fx-border-color: green;");
    }
    if(rightVoltage < rightMinValue || rightVoltage > rightMaxValue){
        rightBox.setStyle("-fx-border-color: red;");
    }else{
        rightBox.setStyle("-fx-border-color: green;");
    }
}
*/