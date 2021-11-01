/*
 * WidgetBase.java
 */
package base.framework.widget;

import base.config.Config;
import base.config.TXT;
import base.config.Widgettxt;
import base.config.menu.Tabpanelconfig;
import java.util.HashMap;
import java.util.Iterator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * abstract super class for Widgets with basic functionality
 * @author Franky Laseure
 */
public abstract class WidgetBase extends StackPane {
    
    //XML & config
    protected Tabpanelconfig controlconfig;
    public void setConfig(Tabpanelconfig controlconfig) {
        this.controlconfig = controlconfig;
        base = new Base(controlconfig.getWidgetconfig());
        String iconpath = Config.getConfigmap() + controlconfig.getIcon();
        icon = new Image(iconpath, defaultimagesize, defaultimagesize, true, false);
        widgettxt = TXT.getWidgettxt(controlconfig.getId());
    }
    
    /**
     * @return Tabpanelconfig
     */
    public Tabpanelconfig getConfig() { return controlconfig; }
    protected Base base;

    //control
    private BooleanProperty waiting = new SimpleBooleanProperty(true);
    public final Boolean getWaiting(){return waiting.get();}
    public final void setWaiting(Boolean value){waiting.set(value);}
    public BooleanProperty waitingProperty() {return waiting;}
    
    //layout
    public static final String defaultstyleClass = "widgetbase";
    protected String widgetstyleClass;
    public void setWidgetstyleClass(String styleClass) { 
        widgetstyleClass = styleClass; 
        this.getStyleClass().add(styleClass);
    }
    protected Widgettxt widgettxt;
    protected Image icon;
    public static final Integer defaultimagesize = 20;
    
    /**
     * constructor
     */
    public WidgetBase() {
        setWidgetstyleClass(defaultstyleClass);
    }

    /**
     * widget load, replaces normal constructor
     */
    public abstract void load();
    
}
