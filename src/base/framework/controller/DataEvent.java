/*
 * DataEvent.java
 */
package base.framework.controller;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Customized Data driven Event
 * used in datahandlers/data objects generated for database tables
 * user in GUI components to get events when this data has changed
 * @author Franky Laseure
 */
public class DataEvent extends Event {
    
    public static final EventType<DataEvent> DATA_SELECTED = new EventType(ANY, "DATA_SELECTED");
    public static final EventType<DataEvent> DATA_EDIT = new EventType(ANY, "DATA_EDIT");
    public static final EventType<DataEvent> DATA_CHANGED = new EventType(ANY, "DATA_CHANGED");
    public static final EventType<DataEvent> DATA_DELETED = new EventType(ANY, "DATA_DELETED");
    public static final EventType<DataEvent> DATA_INSERTED = new EventType(ANY, "DATA_INSERTED");
    public static final EventType<DataEvent> DATA_PREVIOUS = new EventType(ANY, "DATA_PREVIOUS");
    public static final EventType<DataEvent> DATA_NEXT = new EventType(ANY, "DATA_NEXT");
    
    private Object dataobject = null;
    
    /**
     * constructor, using default the DATA_CHANGED EventType
     * @param dataobject Object linked to event
     */
    public DataEvent(Object dataobject) {
        this(DATA_CHANGED, dataobject);
    }
    
    /**
     * constructor, for selected EventType
     * @param eventType constant
     * @param dataobject Object linked to event
     */
    public DataEvent(EventType<? extends Event> eventType, Object dataobject) {
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
    public DataEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Object dataobject) {
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
