package base.controls.popupwindow;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Popup;

/**
 * Popup window without title bar of close button
 * @author Franky Laseure
 */
public class PopupWindow extends Popup {
    
    /**
     * constructor, show Popup windiw
     * @param parent parent control
     * @param content Popup custom content
     */
    public PopupWindow(Parent parent, Node content) {
        setAutoHide(true);
        setAutoFix(true);
        setHideOnEscape(true);
        getContent().add(content);
        
        this.show(parent, 100, 100);
    }
    
}
