/*
 * Userview.java
 */
package base.framework.login;

import base.config.TXT;
import base.framework.logincontroler.Controller;
import base.framework.logincontroler.Data;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * User properties Component
 * @author Franky Laseure
 */
public class Userview extends GridPane {
    
    private final ObjectProperty<Controller> controller = new SimpleObjectProperty<Controller>();
    public Controller getController() { return controller.get(); }
    public void setcontroller(Controller newValue) { controller.set(newValue); }
    public ObjectProperty<Controller> controllerProperty() { return controller; }    
    
    public static final String style = "logincontrol";
    
    /**
     * Eventhandler for logout button: logout on server
     */
    private EventHandler logoutactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.get().logout();
        }
    };
    
    /**
     * create Userview Form
     * @param controller Form controller
     */
    public Userview(ObjectProperty<Controller> controller) {
        //controler
        this.controller.bind(controller);
        //grid layout
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(0, 10, 0, 10));
        //this.getStyleClass().add(style);
        
        //form fields
        Data data = getController().getData();
        //form labels
        Label labellogin = new Label();
        labellogin.textProperty().bind(TXT.applicationUsernameProperty());
        Label labellogintext = new Label();
        labellogintext.textProperty().bind(data.usernameProperty());
        //form buttons
        Button logout = new Button();
        logout.textProperty().bind(TXT.applicationButtonlogoutProperty());
        logout.setOnAction(logoutactionevent);
        
        //layout
        this.add(labellogin, 0, 0);
        this.add(labellogintext, 1, 0);
        HBox layoutbuttons = new HBox();
        layoutbuttons.setSpacing(10);
        layoutbuttons.getChildren().add(logout);
        this.add(layoutbuttons, 1, 2);
    }
    
}
