/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;


import ru.volgatest.brake.Widgets.ValidatedField.FloatValidatedValue;
import ru.volgatest.brake.Widgets.ValidatedField.TextInputValidatedField;

import java.util.function.Function;

/**
 * @author VTARMN013
 */
public class LimitedFloatField extends TextInputValidatedField {
    static public final int NAME_WIDTH = 240;
    static public final int NAME_HEIGHT = 20;
    float min;
    float max;

    public Function<Float, String> validator = (val) -> {
        if (val > max) return "Value too big";
        if (val < min) return "Value too small";
        return null;
    };
    public FloatValidatedValue value = new FloatValidatedValue(validator);

    public LimitedFloatField(String nameString, float minValue, float maxValue, float initValue) {
        super(nameString);
        this.min = minValue;
        this.max = maxValue;

        value.setValue(initValue);
        this.setValidatedValue(value);
    }

    public LimitedFloatField(String nameString, String unit, float minValue, float maxValue, float initValue) {
        super(nameString, unit);
        this.min = minValue;
        this.max = maxValue;

        value.setValue(initValue);
        this.setValidatedValue(value);

        if (nameString != null) {
            this.setNameWidth(NAME_WIDTH);
        }
    }

    public LimitedFloatField(String nameString, String unit, String symbol, float minValue, float maxValue, float initValue) {
        super(nameString, unit, symbol);
        this.min = minValue;
        this.max = maxValue;

        value.setValue(initValue);
        this.setValidatedValue(value);

        if (nameString != null) {
            this.setNameWidth(NAME_WIDTH);
            this.setSizeName(NAME_WIDTH,NAME_HEIGHT);
        }
    }

    public void setWidthName(int widthName) {
        this.name.setMaxWidth(widthName);
        this.name.setMinWidth(widthName);
    }

    public float getValue() {
        return value.getValue();
    }
}