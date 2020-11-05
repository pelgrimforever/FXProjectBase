/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.widget;

import base.framework.logincontroler.*;
import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author Franky Laseure
 */
public class WidgetEvent extends Event {
    
    public static final EventType<WidgetEvent> CLOSEEVENT = new EventType(Event.ANY, "CLOSEEVENT");
    public static final EventType<WidgetEvent> SETFULLSCREENEVENT = new EventType(Event.ANY, "SETFULLSCREENEVENT");
    public static final EventType<WidgetEvent> SETNORMALSCREENEVENT = new EventType(Event.ANY, "SETNORMALSCREENEVENT");

    private WidgetFrame widgetframe;
    public WidgetFrame getWidgetFrame() { return widgetframe; }
    public void setWidgetFrame(WidgetFrame widgetframe) { this.widgetframe = widgetframe; }
    
    public WidgetEvent(EventType<WidgetEvent> eventtype){
        super(eventtype);
    }
    
}
