/*
 * DataControllerEvent.java
 */
package base.framework.controller;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Customized Data controller Event
 * user in Component controllers
 * @author Franky Laseure
 */
public class DataControllerEvent extends Event {
    
    public static final EventType<DataControllerEvent> DATA_CHANGED = new EventType(ANY, "DATA_CHANGED");
    
    private Object dataobject = null;
    
    /**
     * constructor, using default the DATA_CHANGED EventType
     * @param dataobject Object linked to event
     */
    public DataControllerEvent(Object dataobject) {
        this(DATA_CHANGED, dataobject);
    }
    
    /**
     * constructor, for selected EventType
     * @param eventType constant
     * @param dataobject Object linked to event
     */
    public DataControllerEvent(EventType<? extends Event> eventType, Object dataobject) {
        super(eventType);
        this.dataobject = dataobject;
    }
    
    /**
     * constructor
     * @param source the event source which sent the event
     * @param target the event target to associate with the event
     * @param eventType the event type
     * @param dataobject Object linked to event
     */
    public DataControllerEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Object dataobject) {
        super(source, target, eventType);
        this.dataobject = dataobject;
    }  
    
    /**
     * @return data object linked to Event
     */
    public Object getDataobject() {
        return dataobject;
    }
}
