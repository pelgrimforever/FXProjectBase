/*
 * TwoStateIconbox.java
 */
package base.controls;

import base.config.UIsettings;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Iconbox with 2 images, representing 2 states
 * @author Franky Laseure
 */
public class TwoStateIconbox extends StackPane {
    
    private Image iconstate1;
    private Image iconstate2;
    private Iconbox iconview1;
    private Iconbox iconview2;
    
    private BooleanProperty activestate = new SimpleBooleanProperty(false);
    public final Boolean getActivestate(){return activestate.get();}
    public final void setActivestate(Boolean value){activestate.set(value);}
    public BooleanProperty activestateProperty() {return activestate;}
    
    /**
     * constructor
     * @param iconstate1 icon for not active state
     * @param iconstate2 icon for active statie
     */
    public TwoStateIconbox(Image iconstate1, Image iconstate2) {
        this.iconstate1 = iconstate1;
        this.iconstate2 = iconstate2;
        iconview1 = new Iconbox(iconstate1);
        iconview2 = new Iconbox(iconstate2);
        iconview2.visibleProperty().bind(activestate);
        
        this.getChildren().addAll(iconview1, iconview2);
    }
    
}
