/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.beans.property.SimpleStringProperty;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author VTARMN013
 */
public class ValidSymbolsText extends SimpleStringProperty implements TextValidatedValue{
    static Pattern SPECIAL_SYMBOL_PATTERN = Pattern.compile ("\\W");
    static final String STRING_CONSIST_INVALID_SYMBOLS = "Contains invalid or empty symbols";
    ValueValidation<String> validation;

    public ValidSymbolsText( Function<String,String> validator) {
        super();
        this.setValue("");
        validation = new ValueValidation<>(this, validator);    
        
    }
    
    public static boolean checkInvalidSymbolsPresence(String str){
        assert str != null;
        Matcher matcher = SPECIAL_SYMBOL_PATTERN.matcher(str);
        boolean invalidSymbolsPresent = matcher.find();
        return invalidSymbolsPresent;
    }
    
    @Override
    public String testForInvalidStringRepresentation(String str) {
        if(str == null || str.isEmpty()){
            return "String is null";
        }
        if(Character.isDigit(str.charAt(0))){
            return "Can't starts with digit";
        }
        if(checkInvalidSymbolsPresence(str)){
            return STRING_CONSIST_INVALID_SYMBOLS;
        }
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
