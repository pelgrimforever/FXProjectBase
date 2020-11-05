/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.popupwindow;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Popup;

/**
 *
 * @author Franky Laseure
 */
public class PopupWindow extends Popup {
    
    public PopupWindow(Parent parent, Node content) {
        setAutoHide(true);
        setAutoFix(true);
        setHideOnEscape(true);
        getContent().add(content);
        
        this.show(parent, 100, 100);
    }
    
}
