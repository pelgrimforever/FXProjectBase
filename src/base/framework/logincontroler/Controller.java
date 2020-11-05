/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.logincontroler;

import base.framework.controller.AbstractLoginController;
import base.servlets.Securitycheck;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Franky Laseure
 */
public class Controller extends AbstractLoginController {
    
    private final ObjectProperty<Data> data = new SimpleObjectProperty<Data>(new Data());
    public Data getData() { return data.get(); }
    public void setData(Data newValue) { data.set(newValue); }
    public ObjectProperty<Data> dataProperty() { return data; }    
    
    public Controller() {        
    }
    
    public void gotoLoginform() {
        ControllerEvent event = new ControllerEvent(ControllerEvent.BUTTONLOGINEVENT);
        super.triggerevents(event);
    }
    
    public void checkLogin() {
        loginService loginservice = new loginService();
        loginservice.data = getData();
        loginservice.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                Boolean result = (Boolean)t.getSource().getValue();
                ControllerEvent event = new ControllerEvent(ControllerEvent.LOGINEVENT);
                event.setSuccess(result);
                triggerevents(event);
            }
        });
        loginservice.start();
    }
    
    private static class loginService extends Service<Boolean> {
        public Data data;
        protected Task createTask() {
             return new Task<Boolean>() {
                 protected Boolean call() throws Exception {
                     return Securitycheck.checkLogin(data.getUsername(), data.getPassword());
                 }
             };
        }
    }
    
    public void logout() {
        getData().setPassword("");
        Securitycheck.logout();
        ControllerEvent event = new ControllerEvent(ControllerEvent.LOGOUTEVENT);
        triggerevents(event);
    }
    
    public void gotoRegisterform() {
        ControllerEvent event = new ControllerEvent(ControllerEvent.BUTTONREGISTEREVENT);
        super.triggerevents(event);
    }
    
    public void register() {
        registerService registerservice = new registerService();
        registerservice.data = getData();
        registerservice.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                Boolean result = (Boolean)t.getSource().getValue();
                ControllerEvent event = new ControllerEvent(ControllerEvent.REGISTEREVENT);
                event.setSuccess(result);
                triggerevents(event);
            }
        });
        registerservice.start();
    }

    private static class registerService extends Service<Boolean> {
        public Data data;
        protected Task createTask() {
             return new Task<Boolean>() {
                 protected Boolean call() throws Exception {
                     return Securitycheck.register(data.getUsername(), data.getPassword());
                 }
             };
        }
    }
    
}