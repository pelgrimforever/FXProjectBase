/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import base.controls.popupwindow.MessageWindow;
import base.controls.popupwindow.PopupStage;
import base.controls.popupwindow.PopupWindow;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Franky Laseure
 */
public class AppController {
    
    private static AppController appcontroller = null;
    
    private static Stage appstage;
    public static void setStage(Stage stage) { appstage = stage; }
    
    public static AppController getInstance() {
        if(appcontroller!=null) appcontroller = new AppController();
        return appcontroller;
    }
    
    public static void showMessage(String message) {
        MessageWindow mw = new MessageWindow(message);
    }

    public static void openPopupStage(String title, Node content) {
        PopupStage popupstage = new PopupStage(title, null, content);
    }
    
    public static void openPopupStage(String title, String okbuttontext, Node content) {
        PopupStage popupstage = new PopupStage(title, okbuttontext, content);
    }
    
    public static void showPopup(Parent parent, Node content) {
        PopupWindow popup = new PopupWindow(parent, content);
    }
    
}
