/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;


import ru.volgatest.brake.Widgets.ValidatedField.IntegerValidatedValue;
import ru.volgatest.brake.Widgets.ValidatedField.TextInputValidatedField;

import java.util.function.Function;

/**
 * @author VTARMN013
 */
public class LimitedIntegerField extends TextInputValidatedField {
    static public final int NAME_WIDTH = 240;
    static public final int NAME_HEIGHT = 20;

    int min;
    int max;

    public Function<Integer, String> validator = (val) -> {
        if (val > max) return "Value too big";
        if (val < min) return "Value too small";
        return null;
    };
    public IntegerValidatedValue value = new IntegerValidatedValue(validator);

    public LimitedIntegerField(int minValue, int maxValue, int initValue) {
        super(null);
        this.min = minValue;
        this.max = maxValue;

        value.setValue(initValue);
        this.setValidatedValue(value);
    }

    public LimitedIntegerField(String nameString, String unit, int minValue, int maxValue, int initValue) {
        super(nameString, unit);
        this.min = minValue;
        this.max = maxValue;

        value.setValue(initValue);
        this.setValidatedValue(value);

        if (nameString != null) {
            this.setNameWidth(NAME_WIDTH);
            this.setSizeName(NAME_WIDTH,NAME_HEIGHT);

        }
    }



    public int getValue() {
        return value.getValue();
    }
}