/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.popupwindow;

import base.config.Componenttxt;
import base.config.TXT;
import base.config.UIsettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Franky Laseure
 */
public class MessageWindow extends Stage {
    
    private Componenttxt componenttxt = TXT.getComponenttxt("popupmessage");
    
    private MessageWindow self;
    
    /**
     * Eventhandler for register button: try to login on server
     */
    private EventHandler okbuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            self.close();
        }
    };
    
    public MessageWindow(String message) {
        self = this;
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(componenttxt.txt("WINDOW_TITLE"));
        //controls
        Button okbuton = new Button();
        okbuton.setText(componenttxt.txt("BUTTON_OK"));
        okbuton.setOnAction(okbuttonactionevent);

        //layout
        VBox root = new VBox();
        root.setSpacing(10);
        Scene scene = new Scene(root);
        //load resources
        //scene.getStylesheets().add(this.getClass().getResource("../../../default.css").toExternalForm());        
        //scene.getStylesheets().add(this.getClass().getResource("../../../widgets.css").toExternalForm());        
        
        root.getChildren().addAll(new Text(message), okbuton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(UIsettings.getPanelpadding()));
        this.setScene(scene);
        this.show();
    }
    
}
