/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.controller;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 *
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
    
    public DataEvent(Object dataobject) {
        this(DATA_CHANGED, dataobject);
    }
    
    public DataEvent(EventType<? extends Event> arg0, Object dataobject) {
        super(arg0);
        this.dataobject = dataobject;
    }
    
    public DataEvent(Object arg0, EventTarget arg1, EventType<? extends Event> arg2, Object dataobject) {
        super(arg0, arg1, arg2);
        this.dataobject = dataobject;
    }  
    
    public Object getDataobject() {
        return dataobject;
    }
    
}
