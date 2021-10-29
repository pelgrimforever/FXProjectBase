/*
 * Iconbox.java
 */
package base.controls;

import base.config.UIsettings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Square Icon box based on ImageView
 * @author Franky Laseure
 */
public class Iconbox extends ImageView {
    
    private Image icon;
    
    /**
     * set icon, with square size = icon height
     * @param icon Image
     */
    public void setIcon(Image icon) {
        setIcon(icon, icon.getHeight());
    }

    /**
     * set icon with square size override
     * @param icon Image
     * @param iconheight icon height 
     */
    public void setIcon(Image icon, double iconheight) { 
        this.icon = icon;
        iconsize = iconheight;
        this.setImage(icon);
        this.setFitHeight(iconsize);
        setClipnode(new Rectangle(iconsize, iconsize));
        getClipnode().heightProperty().bind(icon.heightProperty());
        getClipnode().widthProperty().bind(icon.widthProperty());
        getClipnode().setArcWidth(UIsettings.getImagearc());
        getClipnode().setArcHeight(UIsettings.getImagearc());
    }
    
    private double iconsize;
    
    /**
     * change icon size
     * @param iconheight 
     */
    public void setIconsize(double iconheight) {
        iconsize = iconheight;
        this.setFitHeight(iconsize);
    }
    
    /**
     * @return icon size
     */
    public double getIconsize() { return iconsize ;}
    
    private ObjectProperty<Rectangle> clipnode = new SimpleObjectProperty();
    private final Rectangle getClipnode(){return clipnode.get();}
    private final void setClipnode(Rectangle value){clipnode.set(value);}
    private ObjectProperty<Rectangle> waitingProperty() {return clipnode;}
    
    /**
     * constructor
     * sets icon, with square size = icon height
     * @param icon Image
     */
    public Iconbox(Image icon) {
        this(icon, icon.getHeight());
    }
    
    /**
     * constructor
     * set icon with square size override
     * @param icon Image
     * @param iconheight icon height 
     */
    public Iconbox(Image icon, double iconheight) {
        setIcon(icon, iconheight);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.clipProperty().bind(clipnode);
    }
    
}
