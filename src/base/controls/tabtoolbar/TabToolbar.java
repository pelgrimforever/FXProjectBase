package base.controls.tabtoolbar;

import javafx.beans.property.StringProperty;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


/**
 * TabPane with a Toolbar as content
 * @author Franky Laseure
 */
public class TabToolbar extends TabPane {
    
    public static final String style = "tabtoolbar";
    
    /**
     * constructor
     */
    public TabToolbar() {
        this.setSide(Side.TOP);
        this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.getStyleClass().add(style);
    }    
    
    /**
     * add new Tabl to the TabPane
     * @param controlname Tab control name
     * @param label Tab label
     * @return Tab
     */
    public Tab addTab(String controlname, StringProperty label) {
        Tab tab = new Tab();
        tab.getStyleClass().add(style);
        tab.setId(controlname);
        tab.textProperty().bind(label);
        this.getTabs().add(tab);
        return tab;
    }
}
