/*
 * AbstractLoginController.java
 */
package base.framework.controller;

import base.framework.logincontroler.ControllerEvent;
import java.util.HashMap;
import java.util.Iterator;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * add Logon Login EventHandler to Component controllers
 * @author Franky Laseure
 */
public abstract class AbstractLoginController {
    
    private HashMap<String, EventHandler<Event>> eventhandlers = new HashMap<String, EventHandler<Event>>();
    
    /**
     * add eventhandler (subscribe)
     * @param objectid subscriber id
     * @param eventhandler Subscribeer object
     */
    public void setLoginEventHandler(String objectid, EventHandler<Event> eventhandler) {
        eventhandlers.put(objectid, eventhandler);
    }
    
    /**
     * send event to all "subscribed" eventhandlers
     * @param event Event to send out
     */
    protected void triggerevents(Event event) {
        Iterator<EventHandler<Event>> eventhandlersI = eventhandlers.values().iterator();
        while(eventhandlersI.hasNext()) {
            eventhandlersI.next().handle(event);
        }
    }
    
}
