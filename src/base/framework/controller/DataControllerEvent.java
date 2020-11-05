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
public class DataControllerEvent extends Event {
    
    public static final EventType<DataControllerEvent> DATA_CHANGED = new EventType(ANY, "DATA_CHANGED");
    
    private Object dataobject = null;
    
    public DataControllerEvent(Object dataobject) {
        this(DATA_CHANGED, dataobject);
    }
    
    public DataControllerEvent(EventType<? extends Event> arg0, Object dataobject) {
        super(arg0);
        this.dataobject = dataobject;
    }
    
    public DataControllerEvent(Object arg0, EventTarget arg1, EventType<? extends Event> arg2, Object dataobject) {
        super(arg0, arg1, arg2);
        this.dataobject = dataobject;
    }  
    
    public Object getDataobject() {
        return dataobject;
    }
}
