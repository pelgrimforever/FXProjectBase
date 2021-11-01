package base.controls.numberpicker;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * Form Number picker
 * @author Franky Laseure
 */
public class NumberPicker extends VBox {
    
    private final IntegerProperty number = new SimpleIntegerProperty(0);
    public Integer getNumber() { return number.get(); }
    public void setNumber(Integer newValue) { number.set(newValue); }
    public IntegerProperty numberProperty() { return number; }    
    
    private final IntegerProperty min = new SimpleIntegerProperty(0);
    public Integer getMin() { return min.get(); }
    public void setMin(Integer newValue) { min.set(newValue); }
    public IntegerProperty minProperty() { return min; }    
    
    private final IntegerProperty max = new SimpleIntegerProperty(100);
    public Integer getMax() { return max.get(); }
    public void setMax(Integer newValue) { max.set(newValue); }
    public IntegerProperty maxProperty() { return max; }    
    
    //Event listeners
    private EventHandler upactionevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            if(getNumber()<getMax()) {
                setNumber(getNumber()+1);
            }
        }
    };
    
    private EventHandler downactionevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            if(getNumber()>getMin()) {
                setNumber(getNumber()-1);
            }
        }
    };
    
    //controls
    private TextField numberfield = new TextField();
    
    //layout
    private static final String buttonstyle = "numberpickerbutton";
    private static final int width = 30;
    private static final int buttonheight = 10;
    
    /**
     * constructor
     * default values:
     * - min: 0
     * - max: 100
     */
    public NumberPicker() {
        this(0, 100);
    }

    /**
     * constructor
     * @param min minimum numeric value
     * @param max maximum numeric value
     */
    public NumberPicker(int min, int max) {
        setMin(min);
        if(max>min) { setMax(max); }
        else { setMax(min); }
        
        //controls
        Rectangle up = new Rectangle(width, buttonheight);
        up.setOnMouseClicked(upactionevent);
        numberfield.setEditable(false);
        numberfield.setMaxWidth(width);
        numberfield.textProperty().bind(numberProperty().asString());
        Rectangle down = new Rectangle(width, buttonheight);
        down.setOnMouseClicked(downactionevent);
        
        //layout
        up.getStyleClass().add(buttonstyle);
        down.getStyleClass().add(buttonstyle);
        this.getChildren().addAll(up, numberfield, down);
    }
    
}
