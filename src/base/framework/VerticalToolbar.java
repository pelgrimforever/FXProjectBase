/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework;

import base.config.Config;
import base.config.menu.Menutabpanelconfig;
import base.controls.RotateIconbox;
import base.framework.widget.WidgetFrame;
import java.util.HashMap;
import java.util.Iterator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Franky Laseure
 */
public class VerticalToolbar extends VBox {
    
    public static final double iconsize = 30;
    
    public static final String style = "verticaltabtoolbar";
    
    private HashMap<String, SequentialTransition> buttonanimations = new HashMap<String, SequentialTransition>();
    
    public VerticalToolbar() {
        this.getStyleClass().add(style);
        this.setStyle(style);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefWidth(iconsize + 20);
    }
    
    
    public void addButton(WidgetFrame widgetframe, EventHandler activebuttonevent) {
        Menutabpanelconfig controlconfig = widgetframe.getWidget().getConfig();
        String buttonid = "AB" + controlconfig.getLabelname();
        
        //check if button is already in toolbar
        Iterator buttons = this.getChildren().iterator();
        RotateIconbox button = getButton(buttonid);
        if(button==null) {
            //button = new Button();
            //button.setId(buttonid);
            String iconpath = Config.getConfigmap() + controlconfig.getIcon();
            Image image = new Image(iconpath, iconsize, iconsize, true, false);
            RotateIconbox iconbox = new RotateIconbox(image);
            iconbox.setOnMouseClicked(activebuttonevent);
            iconbox.waitingProperty().bind(widgetframe.getWidget().waitingProperty());
            iconbox.setId(buttonid);
            VBox.setMargin(iconbox, new Insets(10, 0, 0, 0));

            this.getChildren().add(iconbox);
        }
    }
    
    public void deleteButton(WidgetFrame widgetframe) {
        Menutabpanelconfig controlconfig = widgetframe.getWidget().getConfig();
        String buttonid = "AB" + controlconfig.getLabelname();
        this.getChildren().remove(getButton(buttonid));
    }
    
    private RotateIconbox getButton(String buttonid) {
        Iterator buttons = this.getChildren().iterator();
        RotateIconbox button = null;
        RotateIconbox buttonnext;
        while(buttons.hasNext()) {
            buttonnext = (RotateIconbox)buttons.next();
            if(buttonnext.getId().equals(buttonid)) {
                button = buttonnext;
                break;
            }
        }
        return button;
    }
    
}
