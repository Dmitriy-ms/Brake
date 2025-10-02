/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets;


import ru.volgatest.brake.Widgets.ValidatedField.LongValidatedValue;
import ru.volgatest.brake.Widgets.ValidatedField.TextInputValidatedField;

import java.util.function.Function;

/**
 * @author VTARMN013
 */
public class LimitedLongField extends TextInputValidatedField {
    static public final long NAME_WIDTH = 240;
    long min;
    long max;
    public Function<Long, String> validator = (val) -> {
        if (val > max) return "Value too big";
        if (val < min) return "Value too small";
        return null;
    };
    public LongValidatedValue value = new LongValidatedValue(validator);

    public LimitedLongField(long minValue, long maxValue, long initValue) {
        super(null);
        this.min = minValue;
        this.max = maxValue;

        value.setValue(initValue);
        this.setValidatedValue(value);
    }

    public LimitedLongField(String nameString, String unit, long minValue, long maxValue, long initValue) {
        super(nameString, unit);
        this.min = minValue;
        this.max = maxValue;

        value.setValue(initValue);
        this.setValidatedValue(value);

        if (nameString != null) {
            this.setNameWidth((int) NAME_WIDTH);
        }
    }



    public long getValue() {
        return value.getValue();
    }
}