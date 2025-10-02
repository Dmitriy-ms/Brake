/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.beans.property.SimpleLongProperty;

import java.util.function.Function;

/**
 *
 * @author VTARMN013
 */
public class LongValidatedValue extends SimpleLongProperty implements TextValidatedValue{
    static final String STRING_IS_NOT_LONG = "String is not an long";
    ValueValidation<Long> validation;
    
    
    
    public LongValidatedValue(Function<Long,String> validator ) {
        super();
        validation= new ValueValidation<>(this, validator);
    }
    
    /*
    public LongValidatedValue( Function<Long,String> validator,Observable... dependents) {
        super();
        validation= new ValueValidation<>(this, validator,dependents);
    }*/
    
    private Long getValueFromString(String str){
        Long val = null;
        try{
            val = Long.parseLong(str);
        }catch(Exception e){
        }
        return val;
    }       
    
    @Override
    public String testForInvalidStringRepresentation(String str) {
        return getValueFromString(str)!=null ? null : STRING_IS_NOT_LONG;
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
