/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.login;

import base.config.TXT;
import base.controls.FocusedButton;
import base.framework.logincontroler.Controller;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Franky Laseure
 */
public class Registerform extends GridPane {
    
    private final ObjectProperty<Controller> controller = new SimpleObjectProperty<Controller>();
    public Controller getController() { return controller.get(); }
    public void setcontroller(Controller newValue) { controller.set(newValue); }
    public ObjectProperty<Controller> controllerProperty() { return controller; }    
    
    public static final String style = "logincontrol";
    
    private TextField textlogin = new TextField ();
    private TextField textpassword = new TextField ();
    
    /**
     * Eventhandler for register button: try to login on server
     */
    private EventHandler registeractionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.get().register();
        }
    };
    
    /**
     * Eventhandler for cancel button: go to register form
     */
    private EventHandler cancelactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.get().gotoLoginform();
        }
    };
    
    public Registerform(ObjectProperty<Controller> controller) {
        //controler
        this.controller.bind(controller);
        //grid layout
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(0, 10, 0, 10));
        //this.getStyleClass().add(style);
        
        //form fields
        Label labellogin = new Label();
        labellogin.textProperty().bind(TXT.applicationUsernameProperty());
        Label labelpassword = new Label();
        labelpassword.textProperty().bind(TXT.applicationPasswordProperty());
        //form buttons
        FocusedButton register = new FocusedButton();
        register.textProperty().bind(TXT.applicationButtonregisternewProperty());
        register.setOnAction(registeractionevent);
        FocusedButton cancel = new FocusedButton();
        cancel.textProperty().bind(TXT.applicationButtonloginProperty());
        cancel.setOnAction(cancelactionevent);
        
        //layout
        this.add(labellogin, 0, 0);
        this.add(textlogin, 1, 0);
        this.add(labelpassword, 0, 1);
        this.add(textpassword, 1, 1);
        HBox layoutbuttons = new HBox();
        layoutbuttons.setSpacing(10);
        layoutbuttons.getChildren().addAll(register, cancel);
        this.add(layoutbuttons, 1, 2);
    }
}
