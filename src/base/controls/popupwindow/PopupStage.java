/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.popupwindow;

import base.config.UIsettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
public class PopupStage extends Stage {
    
    private PopupStage self;
    
    /**
     * Eventhandler for register button: try to login on server
     */
    public EventHandler okbuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            closestage();
        }
    };
    
    public void closestage() {
        this.close();
    }
    
    public PopupStage(String title, String closebuttontext, Node content) {
        self = this;
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle(title);
        //controls
        Button okbutton = new Button();
        if(closebuttontext!=null) {
            okbutton.setText(closebuttontext);
            okbutton.setOnAction(okbuttonactionevent);
        }

        //layout
        VBox root = new VBox();
        root.setSpacing(10);
        Scene scene = new Scene(root);
        //load resources
        //scene.getStylesheets().add(this.getClass().getResource("../../../default.css").toExternalForm());        
        //scene.getStylesheets().add(this.getClass().getResource("../../../widgets.css").toExternalForm());        
        
        root.getChildren().add(content);
        if(closebuttontext!=null) {
            root.getChildren().add(okbutton);
        }
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(UIsettings.getPanelpadding()));
        this.setScene(scene);
        this.show();
    }
    
}
