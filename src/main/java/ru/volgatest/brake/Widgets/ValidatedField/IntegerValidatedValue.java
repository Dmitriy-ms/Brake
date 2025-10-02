/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.function.Function;

/**
 *
 * @author VTARMN013
 */
public class IntegerValidatedValue extends SimpleIntegerProperty implements TextValidatedValue{
    static final String STRING_IS_NOT_INTEGER = "String is not an integer"; 
    ValueValidation<Integer> validation;
    
    
    
    public IntegerValidatedValue( Function<Integer,String> validator ) {
        super();
        validation= new ValueValidation<>(this, validator);
    }
    
    /*
    public IntegerValidatedValue( Function<Integer,String> validator,Observable... dependents) {
        super();
        validation= new ValueValidation<>(this, validator,dependents);
    }*/
    
    private Integer getValueFromString(String str){
        Integer val = null;
        try{
            val = Integer.parseInt(str);
        }catch(Exception e){
        }
        return val;
    }       
    
    @Override
    public String testForInvalidStringRepresentation(String str) {
        return getValueFromString(str)!=null ? null :STRING_IS_NOT_INTEGER;
    }

    @Override
    public void setValueAsString(String str) {
        if(testForInvalidStringRepresentation(str) == null)
            setValue(getValueFromString(str));
    }

    @Override
    public ValueValidation valueValidation() {
        return validation;
    } 
    
    @Override
    public Validation validation(){
        return validation;
    }

    @Override
    public String getValueAsString() {
        return String.valueOf(this.getValue());
    }
}
