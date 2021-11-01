package base.controls.tabtoolbar;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * Toolbar used in TabToolbar
 * is the content of a tab
 * @author Franky Laseure
 */
public class Toolbar extends HBox {
    
    public static final String style = "toolbar";
    
    /**
     * constructor
     */
    public Toolbar() {
        this.getStyleClass().add(style);
        this.setAlignment(Pos.BOTTOM_LEFT);
    }
    
    /**
     * add a control to the toolbar
     * @param control ToolbarControl
     */
    public void addControl(ToolbarControl control) {
        HBox.setMargin(control, new Insets(4, 0, 4, 4));
        this.getChildren().add(control);
    }
    
}
