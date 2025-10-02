/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.beans.property.SimpleFloatProperty;

import java.util.function.Function;

/**
 *
 * @author VTARMN013
 */
public class FloatValidatedValue extends SimpleFloatProperty implements TextValidatedValue{
    static final String STRING_IS_NOT_FLOAT = "String is not an float"; 
    ValueValidation<Float> validation;
    //Function<Float,String> validator;
    
    public FloatValidatedValue( Function<Float,String> validator) {
        super();
        //this.validator = validator;
        validation = new ValueValidation<>(this, validator);
    }
    
    public String testForInvalidStringRepresentation(String str) {
        Float valueFromString = getValueFromString(str);
        if(valueFromString ==null){
            return STRING_IS_NOT_FLOAT;
        }
        return null;
    }

    public void setValueAsString(String str) {
        if(testForInvalidStringRepresentation(str) == null){
            Float valueFromString = getValueFromString(str);
            this.set(valueFromString);
        }
    }
    
    private Float getValueFromString(String str){
        Float val = null;
        try{
            val = Float.parseFloat(str);
        }catch(Exception e){}        
        return val;
    }

    @Override
    public ValueValidation valueValidation() {
        return validation;
    } 
    @Override
    public ValueValidation validation() {
        return validation;
    }    
    @Override
    public String getValueAsString() {
        return String.valueOf(this.getValue());
    }
}
    /*
    public FloatValidatedValue( Function<Float,String> validator,Observable... dependents) {
        super();
        validation= new ValueValidation<>(this, validator,dependents);
    }*/