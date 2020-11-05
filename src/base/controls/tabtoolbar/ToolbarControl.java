/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.tabtoolbar;

import base.config.UIsettings;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Franky Laseure
 */
public abstract class ToolbarControl extends GridPane {

    private Text labeltext = new Text();

    public ToolbarControl(String id, StringProperty label) {
        //grid
        setId(id);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 0, 0, 10));
        setAlignment(Pos.CENTER);
        
        //Control Label
        labeltext.textProperty().bind(label);
        //labeltext.fontProperty().bind(UIsettings.normalfontProperty());
        GridPane.setHalignment(labeltext, HPos.CENTER);
        this.add(labeltext, 0, 1);
    }
    
}
