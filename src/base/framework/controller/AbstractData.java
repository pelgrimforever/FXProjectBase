/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.controller;

import java.util.HashMap;
import java.util.Iterator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author Franky Laseure
 */
public abstract class AbstractData {

    private final BooleanProperty clientsidecreated = new SimpleBooleanProperty(false);

    public boolean isClientsidecreated() {
        return clientsidecreated.get();
    }

    public void setClientsidecreated(boolean value) {
        clientsidecreated.set(value);
    }

    public BooleanProperty clientsidecreatedProperty() {
        return clientsidecreated;
    }
    
    private HashMap<Integer, EventHandler<DataEvent>> eventhandlers = 
            new HashMap<Integer, EventHandler<DataEvent>>();
    
    public void addEventHandler(int objectid, EventHandler<DataEvent> eventhandler) {
        eventhandlers.put(objectid, eventhandler);
    }
    
    public void removeEventHandler(int objectid) {
        eventhandlers.remove(objectid);
    }
    
    public void removeAllEventHandlers() {
        eventhandlers.clear();
    }
    
    public void triggerevents(DataEvent event) {
        Iterator<EventHandler<DataEvent>> eventhandlersI = eventhandlers.values().iterator();
        while(eventhandlersI.hasNext()) {
            eventhandlersI.next().handle(event);
        }
    }
    
}
