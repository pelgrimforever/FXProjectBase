/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.datepicker;

import java.sql.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import text.Conversion;

/**
 *
 * @author Franky Laseure
 */
public class DateField extends HBox {
    
    //listeners
    private ChangeListener<Date> datechanged = new ChangeListener<Date>() {
        @Override public void changed(ObservableValue<? extends Date> o, Date oldVal, Date newVal){
            updateDatetext();
        }
    };
    
    //data
    private final ObjectProperty<Date> date = new SimpleObjectProperty<Date>();
    public Date getDate() { return date.get(); }
    public void setDate(Date newValue) { 
        date.set(newValue); 
        updateDatetext();
    }
    public ObjectProperty<Date> dateProperty() { return date; }    
        
    private void updateDatetext() {
        datetext.setText(Conversion.convertDateToString(this.dateProperty().get()));
    }
    
    //controls
    private TextField datetext = new TextField();
    private SimpleCalendar calendar = new SimpleCalendar();
    
    public DateField() {
        //controls
        datetext.setEditable(false);
        calendar.dateProperty().bindBidirectional(this.dateProperty());
        calendar.dateProperty().addListener(datechanged);
        
        //layout
        this.setSpacing(5);
        this.getChildren().addAll(datetext, calendar);
    }
    
}
