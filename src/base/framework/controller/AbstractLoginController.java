/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.controller;

import base.framework.logincontroler.ControllerEvent;
import java.util.HashMap;
import java.util.Iterator;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author Franky Laseure
 */
public abstract class AbstractLoginController {
    
    private HashMap<String, EventHandler<Event>> eventhandlers = 
            new HashMap<String, EventHandler<Event>>();
    public void setLoginEventHandler(String objectid, EventHandler<Event> eventhandler) {
        eventhandlers.put(objectid, eventhandler);
    }
    
    protected void triggerevents(Event event) {
        Iterator<EventHandler<Event>> eventhandlersI = eventhandlers.values().iterator();
        while(eventhandlersI.hasNext()) {
            eventhandlersI.next().handle(event);
        }
    }
    
}
