/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

/**
 *
 * @author VTARMN013
 */
public interface TextValidatedValue extends Validatable{
    public String testForInvalidStringRepresentation(String str);
    public void setValueAsString(String str);
    public String getValueAsString();
    public ValueValidation valueValidation();
}
