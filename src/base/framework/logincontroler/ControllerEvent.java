/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.logincontroler;

import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author Franky Laseure
 */
public class ControllerEvent extends Event {
    
    public static final EventType<ControllerEvent> BUTTONLOGINEVENT = new EventType(Event.ANY, "BUTTONLOGINEVENT");
    public static final EventType<ControllerEvent> LOGINEVENT = new EventType(Event.ANY, "LOGINEVENT");
    public static final EventType<ControllerEvent> BUTTONREGISTEREVENT = new EventType(Event.ANY, "BUTTONREGISTEREVENT");
    public static final EventType<ControllerEvent> REGISTEREVENT = new EventType(Event.ANY, "REGISTEREVENT");
    public static final EventType<ControllerEvent> LOGOUTEVENT = new EventType(Event.ANY, "LOGOUTEVENT");

    private boolean success = false;
    public boolean getSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public ControllerEvent(EventType<ControllerEvent> eventtype){
        super(eventtype);
    }
    
}
