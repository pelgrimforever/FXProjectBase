package base.examples.widgets.example1;

import base.controls.Labelbox;
import base.framework.widget.WidgetBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Demo Widget
 * @author Franky Laseure
 */
public class Example1 extends WidgetBase {
    
    private static final String txt1 = "TXT1";
    
    private Controller controller = new Controller();
    
    //event listeners
    
    //data listeners

    //controls

    //layout
    private VBox contentbox = new VBox();
    private GridPane gridpanel = new GridPane();    
    
    public Example1() {
    }
    
    @Override
    public void load() {
        //data listeners
        
        //controls
        Labelbox l1 = new Labelbox();
        l1.setText(widgettxt.txt(txt1));
        
        //layout
        ScrollPane listpanel = new ScrollPane();
        
        contentbox.getChildren().addAll(l1);
        
        listpanel.setContent(contentbox);
        listpanel.setPrefSize(2000, 1500);
        
        this.getChildren().add(listpanel);
        populatelist();
    }
    
    private void populatelist() {
        this.setWaiting(true);
        gridpanel.getChildren().clear();
        
        this.setWaiting(false);
    }
    
}
