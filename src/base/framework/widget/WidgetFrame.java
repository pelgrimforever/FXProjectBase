/*
 * WidgetFrame.java
 */
package base.framework.widget;

import base.config.UIsettings;
import base.config.menu.Tabpanelconfig;
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
 * WidgetFrame class
 * Wrapper for a Component to fit in this framework
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

    /**
     * send event to all subscribers
     * @param event WidgetEvent constant
     */
    protected void triggerevents(Event event) {
        Iterator<EventHandler<Event>> eventhandlersI = eventhandlers.values().iterator();
        while(eventhandlersI.hasNext()) {
            eventhandlersI.next().handle(event);
        }
    }

    /**
     * send CLOSEEVENT to all subscribers
     */
    private EventHandler closeevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            WidgetEvent event = new WidgetEvent(WidgetEvent.CLOSEEVENT);
            event.setWidgetFrame(self);
            triggerevents(event);
        }
    };

    /**
     * send SETFULLSCREENEVENT / SETNORMALSCREENEVENT to all subscribers
     */
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
    private Tabpanelconfig controlconfig;
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

    /**
     * constructor
     * the widget Component is here instantiated and loaded into the WidgetFrame
     * @param controlconfig Tabpanelconfig
     * @throws WidgetException All exceptions are thrown as a WidgetException
     */
    public WidgetFrame(Tabpanelconfig controlconfig) throws WidgetException {
        this.self = this;
        this.controlconfig = controlconfig;
        this.getStyleClass().add(style);
        if(controlconfig!=null && widget==null) {
            try {
                widget = (WidgetBase)Class.forName(controlconfig.getClassname()).newInstance();
                widget.setConfig(controlconfig);
                widget.load();
            }
            catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new WidgetException(e);
            }
        }
        //TOP controls
        iconbox = new WaitIconbox(widget.icon, widget.defaultimagesize);
        iconbox.waitingProperty().bind(widget.waitingProperty());
        minmaxiconbox = new TwoStateIconbox(UIsettings.getExpandimage(), UIsettings.getExpandimage());
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
