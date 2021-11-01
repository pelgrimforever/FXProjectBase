/*
 * Controller.java
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
 * Controller for Login Component
 * @author Franky Laseure
 */
public class Controller extends AbstractLoginController {
    
    private final ObjectProperty<Data> data = new SimpleObjectProperty<Data>(new Data());
    public Data getData() { return data.get(); }
    public void setData(Data newValue) { data.set(newValue); }
    public ObjectProperty<Data> dataProperty() { return data; }    
    
    public Controller() {        
    }
    
    /**
     * Display Login Form
     */
    public void gotoLoginform() {
        ControllerEvent event = new ControllerEvent(ControllerEvent.BUTTONLOGINEVENT);
        super.triggerevents(event);
    }
    
    /**
     * check login data and send result out with event
     */
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
    
    /**
     * send login call to server
     */
    private static class loginService extends Service<Boolean> {
        public Data data;
        protected Task createTask() {
             return new Task<Boolean>() {
                 protected Boolean call() throws Exception {
                     boolean result = false;
                     try {
                        result = Securitycheck.checkLogin(data.getUsername(), data.getPassword());
                     }
                     catch(Exception e) {
                         System.out.println("loginService " + e.getMessage());
                     }
                     return result;
                 }
             };
        }
    }
    
    /**
     * log user out
     */
    public void logout() {
        getData().setPassword("");
        Securitycheck.logout();
        ControllerEvent event = new ControllerEvent(ControllerEvent.LOGOUTEVENT);
        triggerevents(event);
    }
    
    /**
     * show register form
     */
    public void gotoRegisterform() {
        ControllerEvent event = new ControllerEvent(ControllerEvent.BUTTONREGISTEREVENT);
        super.triggerevents(event);
    }
    
    /**
     * send user registration to server
     */
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

    /**
     * async Service: register
     */
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
