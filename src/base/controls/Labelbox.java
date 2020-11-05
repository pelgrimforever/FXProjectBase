/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls;

import javafx.scene.control.Label;

/**
 *
 * @author Franky Laseure
 */
public class Labelbox extends Label {
    
    public static final String style = "widgetlabel";
    
    public Labelbox() {
        this.getStyleClass().add(style);
    }
}
