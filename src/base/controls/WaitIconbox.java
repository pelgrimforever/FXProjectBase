/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Franky Laseure
 */
public class WaitIconbox extends StackPane {
    
    private Image icon;
    private double imagesize;
    private Iconbox iconview;
    private Iconbox iconwaitview;
    
    private BooleanProperty waiting = new SimpleBooleanProperty(true);
    public final Boolean getWaiting(){return waiting.get();}
    public final void setWaiting(Boolean value){waiting.set(value);}
    public BooleanProperty waitingProperty() {return waiting;}
    
    SequentialTransition buttonanimation;
            
    public WaitIconbox(Image icon, double imagesize) {
        this.icon = icon;
        this.imagesize = imagesize;
        iconview = new Iconbox(icon);
        double iconsize = iconview.getIconsize();
        iconwaitview = new Iconbox(UIsettings.getWaitimage());
        iconwaitview.setIconsize(iconsize * 0.8);
        iconwaitview.setLayoutX(iconsize * 0.1);
        iconwaitview.visibleProperty().bind(waiting);
        
        this.getChildren().addAll(iconview, iconwaitview);
        getanimation();
    }
    
    public void getanimation() {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), iconwaitview);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAutoReverse(false);   

        buttonanimation = new SequentialTransition();
        buttonanimation.getChildren().add(rotateTransition);
        buttonanimation.setCycleCount(Timeline.INDEFINITE);
        buttonanimation.setAutoReverse(true);
        buttonanimation.play();
        
        waiting.addListener(new ChangeListener<Boolean>(){
            public void changed(
                ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                    if(t1) {
                        buttonanimation.play();
                    } else {
                        buttonanimation.stop();
                    }
                }
        });;  
    }
    
}
