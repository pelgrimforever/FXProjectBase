/*
 * RotateIconbox.java
 */
package base.controls;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Rotating Iconbox, used in VerticalToolbar
 * @author Franky Laseure
 */
public class RotateIconbox extends StackPane {
    
    private Image icon;
    private Iconbox iconview;
    private Iconbox rotateiconview;
    
    private BooleanProperty waiting = new SimpleBooleanProperty(true);
    public final Boolean getWaiting(){return waiting.get();}
    public final void setWaiting(Boolean value){waiting.set(value);}
    public BooleanProperty waitingProperty() {return waiting;}
    
    SequentialTransition buttonanimation;
            
    public RotateIconbox(Image icon) {
        this.icon = icon;
        iconview = new Iconbox(icon);
        iconview.visibleProperty().bind(waiting.not());
        rotateiconview = new Iconbox(icon);
        rotateiconview.visibleProperty().bind(waiting);

        this.getChildren().addAll(iconview, rotateiconview);
        getanimation();
    }
    
    private void getanimation() {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), rotateiconview);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAutoReverse(false);   

        buttonanimation = new SequentialTransition();
        buttonanimation.getChildren().add(rotateTransition);
        buttonanimation.setCycleCount(Timeline.INDEFINITE);
        buttonanimation.setAutoReverse(false);
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
