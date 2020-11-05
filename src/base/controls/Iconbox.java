/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Franky Laseure
 */
public class Iconbox extends ImageView {
    
    private Image icon;
    
    public void setIcon(Image icon) {
        setIcon(icon, icon.getHeight());
    }
    
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
    public void setIconsize(double iconheight) {
        iconsize = iconheight;
        this.setFitHeight(iconsize);
    }
    public double getIconsize() { return iconsize ;}
    
    private ObjectProperty<Rectangle> clipnode = new SimpleObjectProperty();
    private final Rectangle getClipnode(){return clipnode.get();}
    private final void setClipnode(Rectangle value){clipnode.set(value);}
    private ObjectProperty<Rectangle> waitingProperty() {return clipnode;}
    
    public Iconbox(Image icon) {
        this(icon, icon.getHeight());
    }
    
    public Iconbox(Image icon, double iconheight) {
        setIcon(icon, iconheight);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.clipProperty().bind(clipnode);
    }
    
}
