/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.tabtoolbar;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author Franky Laseure
 */
public class Toolbar extends HBox {
    
    public static final String style = "toolbar";
    
    public Toolbar() {
        this.getStyleClass().add(style);
        this.setAlignment(Pos.BOTTOM_LEFT);
    }
    
    public void addControl(ToolbarControl control) {
        HBox.setMargin(control, new Insets(4, 0, 4, 4));
        this.getChildren().add(control);
    }
    
}
