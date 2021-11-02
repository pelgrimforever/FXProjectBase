package base.examples.widgets.demo1;

import base.controls.DIRFilter;
import base.controls.FILEFilter;
import base.controls.popupwindow.MessageWindow;
import base.framework.widget.WidgetBase;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javax.swing.JFileChooser;

/**
 * Demo Widget
 * @author Franky Laseure
 */
public class Demo1 extends WidgetBase {
    
    private Controller controller = new Controller();
    
    //event listeners
    private EventHandler directorybuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            JFileChooser filechooser = new JFileChooser(directorytextbox.getText());
            filechooser.addChoosableFileFilter(new DIRFilter());
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int chooserresult = filechooser.showDialog(null, "Select");
            if(chooserresult==filechooser.APPROVE_OPTION) {
                File selectedfile = filechooser.getSelectedFile();
                String newfilename = selectedfile.getAbsolutePath();
                directorytextbox.setText(newfilename);
            }
        }
    };
    
    private EventHandler filebuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            JFileChooser filechooser = new JFileChooser("XML");
            filechooser.addChoosableFileFilter(new FILEFilter("xml"));
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int chooserresult = filechooser.showDialog(null, widgettxt.txt("BUTTON_XMLCHOOSER"));
            if(chooserresult==filechooser.APPROVE_OPTION) {
                File selectedfile = filechooser.getSelectedFile();
                String newfilename = selectedfile.getAbsolutePath();
                filetextbox.setText(newfilename);
            }
        }
    };

    private EventHandler messagebuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            new MessageWindow(message.getText());
        }
    };
    //data listeners

    //controls

    //layout
    private VBox contentbox = new VBox();
    private HBox directorypanel = new HBox();    
    private HBox filepanel = new HBox();    
    
    public Demo1() {
    }
    
    private TextField directorytextbox = new TextField();
    private TextField filetextbox = new TextField();
    private TextField message = new TextField();
    
    @Override
    public void load() {
        //data listeners
        
        //controls
        Button directorybutton = new Button(widgettxt.txt("BUTTON_BROWSE"));
        directorybutton.setOnAction(directorybuttonactionevent);
        directorytextbox.setPrefColumnCount(50);
        
        Button xmlbutton = new Button(widgettxt.txt("BUTTON_XML"));
        xmlbutton.setOnAction(filebuttonactionevent);
        filetextbox.setPrefColumnCount(50);
        
        HBox messagepanel = new HBox();
        Button messagebutton = new Button(widgettxt.txt("BUTTON_MESSAGEWINDOW"));
        messagebutton.setOnAction(messagebuttonactionevent);
        messagepanel.getChildren().addAll(message, messagebutton);

        //layout
        ScrollPane listpanel = new ScrollPane();

        directorypanel.getChildren().addAll(directorybutton, directorytextbox);
        filepanel.getChildren().addAll(xmlbutton, filetextbox);
        
        contentbox.getChildren().addAll(directorypanel, filepanel, messagepanel);
        
        listpanel.setContent(contentbox);
        listpanel.setPrefSize(2000, 1500);
        
        this.getChildren().add(listpanel);
        this.setWaiting(false);
    }
    
}
