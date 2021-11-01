/*
 * AbstractDataController.java
 */
package base.framework.controller;

import java.util.HashMap;
import java.util.Iterator;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * add Data Event functionality to a Component controller
 * @author Franky Laseure
 */
public abstract class AbstractDataController {
    
    private HashMap<String, EventHandler<DataControllerEvent>> eventhandlers = new HashMap<String, EventHandler<DataControllerEvent>>();
    
    /**
     * add eventhandler (subscribe)
     * @param objectid subscriber id
     * @param eventhandler Subscriber
     */
    public void addEventHandler(String objectid, EventHandler<DataControllerEvent> eventhandler) {
        eventhandlers.put(objectid, eventhandler);
    }
    
    /**
     * remove eventhandler (unsubscribe)
     * @param objectid subscriber id
     */
    public void removeEventHandler(String objectid) {
        eventhandlers.remove(objectid);
    }
    
    /**
     * send event to all "subscribed" eventhandlers
     * @param event DataControllerEvent
     */
    protected void triggerevents(DataControllerEvent event) {
        Iterator<EventHandler<DataControllerEvent>> eventhandlersI = eventhandlers.values().iterator();
        while(eventhandlersI.hasNext()) {
            eventhandlersI.next().handle(event);
        }
    }
    
}
