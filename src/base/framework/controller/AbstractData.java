/*
 * AbstractData.java
 */
package base.framework.controller;

import java.util.HashMap;
import java.util.Iterator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * add Date Event functionality and client dependent data to datahandlers (project/datahandlers/base/*)
 * @author Franky Laseure
 */
public abstract class AbstractData {

    private final BooleanProperty clientsidecreated = new SimpleBooleanProperty(false);

    /**
     * Data created on client side
     * @return true/false
     */
    public boolean isClientsidecreated() {
        return clientsidecreated.get();
    }

    /**
     * set Data created on cliend side value
     * @param value true/false
     */
    public void setClientsidecreated(boolean value) {
        clientsidecreated.set(value);
    }

    /**
     * @return BooleanProperty
     */
    public BooleanProperty clientsidecreatedProperty() {
        return clientsidecreated;
    }
    
    private HashMap<Integer, EventHandler<DataEvent>> eventhandlers = new HashMap<Integer, EventHandler<DataEvent>>();
    
    /**
     * add EventHandler (subscribe)
     * @param objectid subscriber id
     * @param eventhandler Subscriber object
     */
    public void addEventHandler(int objectid, EventHandler<DataEvent> eventhandler) {
        eventhandlers.put(objectid, eventhandler);
    }
    
    /**
     * remove EventHandler (unsubscribe)
     * @param objectid subscriber id
     */
    public void removeEventHandler(int objectid) {
        eventhandlers.remove(objectid);
    }
    
    /**
     * initialize EventHandlers array
     */
    public void removeAllEventHandlers() {
        eventhandlers.clear();
    }
    
    /**
     * send event to all "subscribed" eventhandlers
     * @param event Dataevent
     */
    public void triggerevents(DataEvent event) {
        Iterator<EventHandler<DataEvent>> eventhandlersI = eventhandlers.values().iterator();
        while(eventhandlersI.hasNext()) {
            eventhandlersI.next().handle(event);
        }
    }
    
}
