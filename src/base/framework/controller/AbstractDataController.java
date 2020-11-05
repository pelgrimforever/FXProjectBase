/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.controller;

import java.util.HashMap;
import java.util.Iterator;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author Franky Laseure
 */
public abstract class AbstractDataController {
    
    private HashMap<String, EventHandler<DataControllerEvent>> eventhandlers = 
            new HashMap<String, EventHandler<DataControllerEvent>>();
    
    public void addEventHandler(String objectid, EventHandler<DataControllerEvent> eventhandler) {
        eventhandlers.put(objectid, eventhandler);
    }
    
    public void removeEventHandler(String objectid) {
        eventhandlers.remove(objectid);
    }
    
    protected void triggerevents(DataControllerEvent event) {
        Iterator<EventHandler<DataControllerEvent>> eventhandlersI = eventhandlers.values().iterator();
        while(eventhandlersI.hasNext()) {
            eventhandlersI.next().handle(event);
        }
    }
    
}
