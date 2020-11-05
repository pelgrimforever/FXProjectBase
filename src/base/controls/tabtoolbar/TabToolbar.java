/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.tabtoolbar;

import javafx.beans.property.StringProperty;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author Franky Laseure
 */
public class TabToolbar extends TabPane {
    
    public static final String style = "tabtoolbar";
    
    //http://docs.oracle.com/javafx/2/deployment/javafx_javascript.htm#BCEBGCGD
    public TabToolbar() {
        this.setSide(Side.TOP);
        this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.getStyleClass().add(style);
    }    
    
    public Tab addTab(String controlname, StringProperty label) {
        Tab tab = new Tab();
        tab.getStyleClass().add(style);
        tab.setId(controlname);
        tab.textProperty().bind(label);
        this.getTabs().add(tab);
        return tab;
    }
}
