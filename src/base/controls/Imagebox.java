/*
 * Imagebox.java
 */
package base.controls;

import base.config.UIsettings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Imagebox, implementation of ImageView
 * @author Franky Laseure
 */
public class Imagebox extends ImageView {
    
    private double width;
    private double height;
    
    /**
     * constructor, set image in original size
     * @param image Image
     */
    public Imagebox(Image image) {
        this(image, image.getWidth(), image.getHeight());
    }
    
    /**
     * constructor, set image and override width and height
     * @param image Image
     * @param width image display width
     * @param height image display height
     */
    public Imagebox(Image image, double width, double height) {
        this.height = height;
        this.width = width;
        updateImage(image);
        getClipnode().setArcWidth(UIsettings.getImagearc());
        getClipnode().setArcHeight(UIsettings.getImagearc());
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.clipProperty().bind(clipnode);
        this.setPreserveRatio(true);
    }
    
    /**
     * change image, scale to previous image with/height
     * @param image Image
     */
    public void updateImage(Image image) {
        this.setImage(image);
        if(image!=null) {
            if(image.getWidth()/image.getHeight() > width/height) {
                this.setFitWidth(width);
            } else {
                this.setFitHeight(height);
            }
            setClipnode(new Rectangle(image.getWidth(), image.getHeight()));
        } else {
            setClipnode(new Rectangle(width, height));
        }
    }
    
    private ObjectProperty<Rectangle> clipnode = new SimpleObjectProperty();
    private final Rectangle getClipnode(){return clipnode.get();}
    private final void setClipnode(Rectangle value){clipnode.set(value);}
    private ObjectProperty<Rectangle> clipnodeProperty() {return clipnode;}
    
}
