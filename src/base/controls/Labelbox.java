/*
 * Labelbox.java
 */
package base.controls;

import javafx.scene.control.Label;

/**
 * Label customized with styling for this framwork widgets
 * @author Franky Laseure
 */
public class Labelbox extends Label {
    
    public static final String style = "widgetlabel";
    
    public Labelbox() {
        this.getStyleClass().add(style);
    }
}
