/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;

import java.util.function.Supplier;

/**
 *
 * @author VTARMN013
 */
public class Validation implements Observable{
    SimpleStringProperty invalidDescription = new SimpleStringProperty();
    Supplier<String> prefix;
    
    Validation validationToBound;
    InvalidationListener bindListener = il-> {
        if(validationToBound!=null){
            invalidDescription.setValue(validationToBound.getInvalidDescription());
        }
    };
    
    public void setPrefix(Supplier<String> prefix){
        this.prefix = prefix;
    }
    public String getInvalidDescription(){
        return invalidDescription.getValue();
    }
    public boolean isValid(){
        return invalidDescription.getValue()==null;
    }

    public void setValid(){
        invalidDescription.setValue(null);
    }
    
    public BooleanBinding validationBinding(){
        return invalidDescription.isEmpty();
    }
    
    public void setInvalid(String description){
        if(description == null)setValid();
        else invalidDescription.setValue(prefix==null?description:prefix.get()+description);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidDescription.addListener(listener);//new WeakInvalidationListener(
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidDescription.removeListener(listener);
    }
    public void bindTo(Validation validation){
        invalidDescription.bind(validation.invalidDescription);
    }
    public void unbind(){
        invalidDescription.unbind();
    }
}
