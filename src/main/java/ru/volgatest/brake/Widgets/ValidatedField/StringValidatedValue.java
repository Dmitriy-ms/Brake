/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.beans.property.SimpleStringProperty;

import java.util.function.Function;

/**
 *
 * @author VTARMN013
 */
public class StringValidatedValue  extends SimpleStringProperty implements TextValidatedValue{
    ValueValidation<String> validation;
        
    public StringValidatedValue( Function<String,String> validator) {
        super();
        this.setValue("");
        validation= new ValueValidation<>(this, validator);    
    }
    
    @Override
    public String testForInvalidStringRepresentation(String str) {
        return null;
    }

    @Override
    public void setValueAsString(String str) {
        if(testForInvalidStringRepresentation(str) == null)
            setValue(str);
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
        return this.getValue();
    }
}
