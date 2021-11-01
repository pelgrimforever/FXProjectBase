/*
 * AppController.java
 */
package base;

import base.controls.popupwindow.MessageWindow;
import base.controls.popupwindow.PopupStage;
import base.controls.popupwindow.PopupWindow;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * FX Application Controller
 * implemented as Singleton
 * @author Franky Laseure
 */
public class AppController {
    
    private static AppController appcontroller = null;
    
    private static Stage appstage;
    public static void setStage(Stage stage) { appstage = stage; }
    
    /**
     * get Instance
     * @return AppController
     */
    public static AppController getInstance() {
        if(appcontroller!=null) appcontroller = new AppController();
        return appcontroller;
    }
    
    /**
     * show message in Popup Message window
     * @param message text message
     */
    public static void showMessage(String message) {
        MessageWindow mw = new MessageWindow(message);
    }

    /**
     * Open popup window text message
     * @param title: popup title
     * @param content: popup message
     */
    public static void openPopupStage(String title, Node content) {
        PopupStage popupstage = new PopupStage(title, null, content);
    }
    
    /**
     * Open popup window with custom content
     * @param title popup title
     * @param okbuttontext poup ok button text
     * @param content GUI content object
     */
    public static void openPopupStage(String title, String okbuttontext, Node content) {
        PopupStage popupstage = new PopupStage(title, okbuttontext, content);
    }
    
    /**
     * show popup window dependend on parent
     * @param parent parent window
     * @param content popup content
     */
    public static void showPopup(Parent parent, Node content) {
        PopupWindow popup = new PopupWindow(parent, content);
    }
    
}
