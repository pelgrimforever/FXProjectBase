/*
 * Login.java
 */
package base.framework.login;

import base.config.TXT;
import base.controls.FocusedButton;
import base.framework.logincontroler.Controller;
import base.framework.logincontroler.Data;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Login Form, part of Loginpanel
 * @author Franky Laseure
 */
public class Loginform extends GridPane {
    
    private final ObjectProperty<Controller> controller = new SimpleObjectProperty<Controller>();
    public Controller getController() { return controller.get(); }
    public void setcontroller(Controller newValue) { controller.set(newValue); }
    public ObjectProperty<Controller> controllerProperty() { return controller; }    
    
    public static final String style = "logincontrol";
    
    private TextField textlogin = new TextField ();
    private PasswordField textpassword = new PasswordField();
    
    /**
     * Eventhandler for login button: try to login on server
     */
    private EventHandler loginactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.get().checkLogin();
        }
    };
    
    /**
     * Eventhandler for register button: go to register form
     */
    private EventHandler registeractionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.get().gotoRegisterform();
        }
    };
    
    /**
     * constructor
     * @param controller Component controller
     */
    public Loginform(ObjectProperty<Controller> controller) {
        //controler
        this.controller.bind(controller);
        //grid layout
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(0, 10, 0, 10));
        //this.getStyleClass().add(style);
        
        //form fields
        Data data = getController().getData();
        data.usernameProperty().bindBidirectional(textlogin.textProperty());
        data.passwordProperty().bindBidirectional(textpassword.textProperty());
        //form labels
        Label labellogin = new Label();
        labellogin.textProperty().bind(TXT.applicationUsernameProperty());
        Label labelpassword = new Label();
        labelpassword.textProperty().bind(TXT.applicationPasswordProperty());
        //form buttons
        FocusedButton login = new FocusedButton();
        login.textProperty().bind(TXT.applicationButtonloginProperty());
        login.setOnAction(loginactionevent);
        FocusedButton register = new FocusedButton();
        register.textProperty().bind(TXT.applicationButtonregisterProperty());
        register.setOnAction(registeractionevent);
        
        //layout
        this.add(labellogin, 0, 0);
        this.add(textlogin, 1, 0);
        this.add(labelpassword, 0, 1);
        this.add(textpassword, 1, 1);
        HBox layoutbuttons = new HBox();
        layoutbuttons.setSpacing(10);
        layoutbuttons.getChildren().addAll(login, register);
        this.add(layoutbuttons, 1, 2);
    }
    
}
