/*
 * CrosslineIconbox.java
 */
package base.controls;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Icon box with crossline through the icon
 * @author Franky Laseure
 */
public class CrosslineIconbox extends StackPane {
    
    private Image icon;
    private double imagesize;
    private Iconbox iconview;
    private Iconbox iconwaitview;
    
    /**
     * @param icon Image
     * @param imagesize image size
     */
    public CrosslineIconbox(Image icon, double imagesize) {
        this.icon = icon;
        this.imagesize = imagesize;
        iconview = new Iconbox(icon);
        double iconsize = iconview.getIconsize();
        Line crossline = new Line(0, imagesize, imagesize, 0);
        crossline.setStrokeWidth(2);
        crossline.setStroke(Color.BLACK);
        this.getChildren().addAll(iconview, crossline);
    }
    
}
