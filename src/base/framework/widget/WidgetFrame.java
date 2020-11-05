/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.widget;

import base.config.UIsettings;
import base.config.menu.Menutabpanelconfig;
import base.controls.CrosslineIconbox;
import base.controls.TwoStateIconbox;
import base.controls.WaitIconbox;
import base.controls.tabtoolbar.ToolbarButton;
import general.exception.WidgetException;
import java.util.HashMap;
import java.util.Iterator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 *
 * @author Franky Laseure
 */
public class WidgetFrame extends BorderPane {
    
    //Events
    private WidgetFrame self;
    
    private HashMap<String, EventHandler<Event>> eventhandlers = 
            new HashMap<String, EventHandler<Event>>();
    public void addEventHandler(String objectid, EventHandler<Event> eventhandler) {
        eventhandlers.put(objectid, eventhandler);
    }
    
    protected void triggerevents(Event event) {
        Iterator<EventHandler<Event>> eventhandlersI = eventhandlers.values().iterator();
        while(eventhandlersI.hasNext()) {
            eventhandlersI.next().handle(event);
        }
    }

    private EventHandler closeevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            WidgetEvent event = new WidgetEvent(WidgetEvent.CLOSEEVENT);
            event.setWidgetFrame(self);
            triggerevents(event);
        }
    };

    private EventHandler fullscreenbuttonevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            setFullscreenstate(!getFullscreenstate());
            WidgetEvent event;
            if(getFullscreenstate()) {
                event = new WidgetEvent(WidgetEvent.SETFULLSCREENEVENT);
            } else {
                event = new WidgetEvent(WidgetEvent.SETNORMALSCREENEVENT);
            }
            triggerevents(event);
        }
    };
    
    //Control
    private Menutabpanelconfig controlconfig;
    private WidgetBase widget;
    public WidgetBase getWidget() { return widget; }
    private boolean fullscreen = false;
    
    private BooleanProperty fullscreenstate = new SimpleBooleanProperty(false);
    public final Boolean getFullscreenstate(){return fullscreenstate.get();}
    public final void setFullscreenstate(Boolean value){fullscreenstate.set(value);}
    public BooleanProperty fullscreenstateProperty() {return fullscreenstate;}
    
    //layout
    private static final String style = "widgetframe";
    private static final String headerstyle = "widgetframeheader";
    private WaitIconbox iconbox;
    private CrosslineIconbox closeiconbox;
    private TwoStateIconbox minmaxiconbox;
    
    public WidgetFrame(Menutabpanelconfig controlconfig) throws WidgetException {
        this.self = this;
        this.controlconfig = controlconfig;
        this.getStyleClass().add(style);
        if(controlconfig!=null && widget==null) {
            try {
                widget = (WidgetBase)Class.forName(controlconfig.getClassname()).newInstance();
                widget.setConfig(controlconfig);
                widget.load();
            }
            catch(ClassNotFoundException e) {
                throw new WidgetException(e);
            }
            catch(InstantiationException e) {
                throw new WidgetException(e);
            }
            catch(IllegalAccessException e) {
                throw new WidgetException(e);
            }
        }
//TOP        
        //TOP controls
        iconbox = new WaitIconbox(widget.icon, widget.defaultimagesize);
        iconbox.waitingProperty().bind(widget.waitingProperty());
        minmaxiconbox = new TwoStateIconbox(UIsettings.getExpandimage(), UIsettings.getExpandimage(), widget.defaultimagesize);
        minmaxiconbox.setOnMouseClicked(fullscreenbuttonevent);
        minmaxiconbox.activestateProperty().bind(fullscreenstate);
        closeiconbox = new CrosslineIconbox(widget.icon, widget.defaultimagesize);
        closeiconbox.setOnMouseClicked(closeevent);
        
        Label title = new Label(widget.controlconfig.getLabel());
        title.setAlignment(Pos.CENTER);
        
        //TOP layout
        StackPane top = new StackPane();
        top.getStyleClass().add(headerstyle);
        top.setPadding(new Insets(5));
        
        HBox leftbuttonpanel = new HBox();
        leftbuttonpanel.setAlignment(Pos.CENTER_LEFT);
        leftbuttonpanel.getChildren().addAll(iconbox, minmaxiconbox);
        
        HBox rightbuttonpanel = new HBox();
        rightbuttonpanel.setAlignment(Pos.CENTER_RIGHT);
        rightbuttonpanel.getChildren().addAll(closeiconbox);

        AnchorPane buttonpanel = new AnchorPane();
        buttonpanel.getChildren().addAll(leftbuttonpanel, rightbuttonpanel);
        AnchorPane.setLeftAnchor(leftbuttonpanel, 5.0);
        AnchorPane.setRightAnchor(rightbuttonpanel, 5.0);
        
        top.getChildren().addAll(title, buttonpanel);
        
//WidgetFrame
        this.setTop(top);
        this.setCenter(widget);
        this.requestLayout();
    }
    
}
