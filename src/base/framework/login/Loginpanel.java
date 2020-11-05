/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.login;

import base.AppController;
import base.config.TXT;
import base.controls.popupwindow.MessageWindow;
import base.framework.logincontroler.Controller;
import base.framework.logincontroler.ControllerEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;


/**
 *
 * @author Franky Laseure
 */
public class Loginpanel extends StackPane {
    
    public static final String style = "logincontrol";

    private static final byte LOGIN = 0;
    private static final byte REGISTER = 1;
    private static final byte USER = 2;
    
    private final ObjectProperty<Controller> controller = new SimpleObjectProperty<Controller>(new Controller());
    public Controller getController() { return controller.get(); }
    public void setcontroller(Controller newValue) { controller.set(newValue); }
    public ObjectProperty<Controller> controllerProperty() { return controller; }    
    
    private Loginform loginform = new Loginform(controller);
    private Registerform registerform = new Registerform(controller);
    private Userview userview = new Userview(controller);
    
    public Loginpanel() {
        //controler
        loginform.controllerProperty().bind(controller);
        controller.get().setLoginEventHandler(this.getClass().getName(), logineventhandler);
        // layout
        this.getStyleClass().add(style);
        this.getChildren().addAll(loginform, registerform, userview);
        showform(LOGIN);
    }
    
    private EventHandler logineventhandler = new EventHandler<ControllerEvent>() {
        @Override public void handle(ControllerEvent e) {
            //BUTTON ACTIONS
            if(e.getEventType()==ControllerEvent.BUTTONREGISTEREVENT) {
                showform(REGISTER);
            }
            if(e.getEventType()==ControllerEvent.BUTTONLOGINEVENT) {
                showform(LOGIN);
            }
            //EVENTS
            if(e.getEventType()==ControllerEvent.LOGINEVENT) {
                if(e.getSuccess()) {
                    showform(USER);
                } else {
                    AppController.showMessage(TXT.getApplicationLoginfailed() + controller.get().getData().getUsername());
                }
            }
            if(e.getEventType()==ControllerEvent.LOGOUTEVENT) {
                showform(LOGIN)                ;
            }
            if(e.getEventType()==ControllerEvent.REGISTEREVENT) {
                if(e.getSuccess()) {
                    showform(USER);
                } else {
                    AppController.showMessage(TXT.getApplicationLoginfailed());
                }
            }
        }
    };
    
    public void showform(byte formconstant) {
        loginform.setVisible(LOGIN == formconstant);
        registerform.setVisible(REGISTER == formconstant);
        userview.setVisible(USER == formconstant);
    }
}
