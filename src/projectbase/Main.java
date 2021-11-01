/*
 * Main.java
 */
package projectbase;

import base.AppController;
import base.config.Config;
import base.config.TXT;
import base.framework.TabToolbarApp;
import java.io.IOException;
import java.util.Map;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXProjectBase, Demo application
 * @author Franky Laseure
 */
public class Main extends Application {
    
    //server properties
    public static String server = "";
    public static final String projectpath = "";

    //visual properties
    private static boolean isEmbedded;
    public static boolean getisEmbedded() { return isEmbedded; }
    private static Rectangle2D visualbounds;
    ObservableList<Screen> screens;
    
    private static Map applicationArguments;
    
    private static HostServices services;

    @Override
    public void start(Stage primaryStage) {
        try {
            //no http service is defined for this demo, the current directory is taken
            server = new java.io.File(".").getCanonicalPath() + "/";
        }
        catch(IOException e) {
        }
        //VISUAL PROPERTIES
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("camera.png")));
        //check if application is embedded
        isEmbedded = primaryStage.getWidth()>0;        
        visualbounds = Screen.getPrimary().getVisualBounds();
        screens = Screen.getScreens();
        AppController.setStage(primaryStage);
        
        //load online config
        Config config = Config.buildInstance(server, projectpath);

        primaryStage.titleProperty().bind(TXT.applicationTitleProperty());
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, visualbounds.getWidth(), visualbounds.getHeight());
        //load resources
        scene.getStylesheets().add(this.getClass().getResource("default.css").toExternalForm());        
        scene.getStylesheets().add(this.getClass().getResource("widgets.css").toExternalForm());        
        scene.getStylesheets().add(this.getClass().getResource("basecontrols.css").toExternalForm());        
        
        TabToolbarApp tabtoolbarcontrol = new TabToolbarApp();
        root.getChildren().add(tabtoolbarcontrol);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
