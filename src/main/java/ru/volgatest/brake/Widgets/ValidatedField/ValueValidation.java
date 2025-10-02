/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.ValidatedField;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author VTARMN013
 */
public class  ValueValidation<E> extends Validation{ //, E extends Number extends SimpleIntegerProperty
    
    //SimpleStringProperty invalidDescription = new SimpleStringProperty();
    Supplier<String> validator = null;
    ObservableList<Observable> dependenciesList;
        
    
    public void setPermanentValidationDependencies(Observable... dependencies){
        if(dependencies == null) return;
        if(dependenciesList == null) dependenciesList = FXCollections.observableArrayList(r -> new Observable[]{r});
        dependenciesList.addAll(dependenciesList);
        dependenciesList.addListener((Observable li)-> refreshValidation());    // TODO change to WeakInvalidationListener
    }
    public void refreshValidation(){
        if(validator!=null){setInvalid(validator.get());}
    }
    
    
    public ValueValidation(ObservableValue<E> value, Function<E,String> validator){
        if(validator!=null){
            value.addListener((obs)->setInvalid(validator.apply(value.getValue())));
            this.validator = ()->validator.apply(value.getValue());
        }
    }
    
/*  public ValueValidation(ObservableValue<E> val, Function<E,String> validator, Observable... dependents){ 
        if(validator!=null){
            val.addListener((obs)->invalidDescription.setValue(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }
        for(var observable: dependents){
            observable.addListener((il)->invalidDescription.setValue(validator.apply(val.getValue())));
        }
    }  */
    
    public ValueValidation(SimpleIntegerProperty val, Function<Integer,String> validator){
        if(validator!=null){
            val.addListener((obs)->setInvalid(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }
    }
    /*
    public ValueValidation(SimpleIntegerProperty val, Function<Integer,String> validator, Observable... dependents){ 
        if(validator!=null){
             val.addListener((obs)->invalidDescription.setValue(validator.apply(val.getValue())));     
             this.validator = ()->validator.apply(val.getValue());
        }
        for(var observable: dependents){
            observable.addListener((il)->invalidDescription.setValue(validator.apply(val.getValue())));
        }
    }
    */
    public ValueValidation(SimpleLongProperty val, Function<Long,String> validator){
        if(validator!=null){
            val.addListener((obs)->setInvalid(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }
    }
    /*
    public ValueValidation(SimpleLongProperty val, Function<Long,String> validator, Observable... dependents){ 
        if(validator!=null){
            val.addListener((obs)->invalidDescription.setValue(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }
        for(var observable: dependents){
            observable.addListener((il)->invalidDescription.setValue(validator.apply(val.getValue())));
        }
    }
        */
    public ValueValidation(SimpleFloatProperty val, Function<Float,String> validator){ 
        if(validator!=null){
            val.addListener((obs)->setInvalid(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }
    }  
    /*
    public ValueValidation(SimpleFloatProperty val, Function<Float,String> validator, Observable... dependents){ 
        if(validator!=null){
            val.addListener((obs)->invalidDescription.setValue(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }  
        for(var observable: dependents){
            observable.addListener((il)->invalidDescription.setValue(validator.apply(val.getValue())));
        }
    }
        
    */
    public ValueValidation(SimpleDoubleProperty val, Function<Double,String> validator){
        if(validator!=null){
            val.addListener((obs)->setInvalid(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }
    }
    /*
    public ValueValidation(SimpleDoubleProperty val, Function<Double,String> validator, Observable... dependents){ 
        if(validator!=null){
            val.addListener((obs)->invalidDescription.setValue(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }
        for(var observable: dependents){
            observable.addListener((il)->invalidDescription.setValue(validator.apply(val.getValue())));
        }
    }
    */
    
    /*
    public ValueValidation(SimpleBooleanProperty val, Function<Boolean,String> validator){ 
        if(validator!=null){
            val.addListener((obs)->invalidDescription.setValue(validator.apply(val.getValue())));
            this.validator = ()->validator.apply(val.getValue());
        }
    }  
    */
    /*
    public ValueValidation(SimpleBooleanProperty val, Function<Boolean,String> validator, Observable... dependents){ 
        if(validator!=null){
            val.addListener((obs)->invalidDescription.setValue(validator.apply(val.getValue())));
        }
        for(var observable: dependents){
            observable.addListener((il)->invalidDescription.setValue(validator.apply(val.getValue())));
        }
    }
   
/*
    public void setExternalValidator(Function<E,String> validator){
        this.validator = validator;
    }*/
    
    

    
    /*
    @Override
    public void addListener(InvalidationListener il) {
        invalidDescription.addListener(il);
    }
    
    @Override
    public void removeListener(InvalidationListener il) {
        invalidDescription.removeListener(il);
    }
    public BooleanBinding isValidBinding(){
        return invalidDescription.isEmpty();
    }
    */
}
