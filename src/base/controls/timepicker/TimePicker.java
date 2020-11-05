/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.timepicker;

import base.controls.numberpicker.NumberPicker;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Franky Laseure
 */
public class TimePicker extends HBox {
    
    private final ObjectProperty<Time> time = new SimpleObjectProperty<Time>(null);
    public Time getTime() { return time.get(); }
    public void setTime(Time newValue) { 
        time.set(newValue); 
        setTime();
    }
    public ObjectProperty<Time> timeProperty() { return time; }    

    private final IntegerProperty hour = new SimpleIntegerProperty(0);
    public Integer getHour() { return hour.get(); }
    private void setHour(Integer newValue) { hour.set(newValue); }
    private IntegerProperty hourProperty() { return hour; }    
    
    private final IntegerProperty min = new SimpleIntegerProperty(0);
    public Integer getMin() { return min.get(); }
    private void setMin(Integer newValue) { min.set(newValue); }
    private IntegerProperty minProperty() { return min; }    
    
    private final IntegerProperty sec = new SimpleIntegerProperty(0);
    public Integer getSec() { return sec.get(); }
    private void setSec(Integer newValue) { sec.set(newValue); }
    private IntegerProperty secProperty() { return sec; }    
        
    //listeners
    private boolean ignoreevents = false;
    
    private ChangeListener<Time> timechanged = new ChangeListener<Time>() {
        @Override public void changed(ObservableValue<? extends Time> o, Time oldVal, Time newVal){
            if(!ignoreevents) {
                if(newVal==null) {
                    setHour(0);
                    setMin(0);
                    setSec(0);
                    createtime();
                } else {
                    setTime();
                }
            }
        }
    };
        
    private ChangeListener<Number> digitchanged = new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> o, Number oldVal, Number newVal){
            if(!ignoreevents) {
                createtime();
            }
        }
    };
    
    private void createtime() {
        Calendar cal = Calendar.getInstance();
        cal.set(0, 0, 0, getHour(), getMin(), getSec());
        time.set(new Time(cal.getTimeInMillis()));
    }
    
    private void setTime() {
        ignoreevents = true;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTime().getTime());
        setHour(cal.get(Calendar.HOUR_OF_DAY));
        setMin(cal.get(Calendar.MINUTE));
        setSec(cal.get(Calendar.SECOND));
        ignoreevents = false;
    }
    
    //controls
    private NumberPicker hourpicker = new NumberPicker(0, 23);
    private NumberPicker minpicker = new NumberPicker(0, 59);
    private NumberPicker secpicker = new NumberPicker(0, 59);
    
    public TimePicker() {
        
        //data listeners
        timeProperty().addListener(timechanged);
        hourProperty().addListener(digitchanged);
        minProperty().addListener(digitchanged);
        secProperty().addListener(digitchanged);
        
        //controls
        hourpicker.numberProperty().bindBidirectional(hourProperty());
        minpicker.numberProperty().bindBidirectional(minProperty());
        secpicker.numberProperty().bindBidirectional(secProperty());
        
        //layout
        Label hourseparator = new Label(":");
        Label minseparator = new Label(":");
        
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(hourpicker, hourseparator, minpicker, minseparator, secpicker);
    }
    
}
