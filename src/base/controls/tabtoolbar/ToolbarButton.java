/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.controls.tabtoolbar;

import base.config.UIsettings;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Franky Laseure
 */

public class ToolbarButton extends ToolbarControl {
    
    public static final double defaultimagesize = 30;
    public static final String style = "toolbarbutton";
    
    private double imagesize = defaultimagesize;
    
    public ToolbarButton(String id, StringProperty label, String iconpath, EventHandler panelbuttonselected) {
        this(id, label, iconpath, panelbuttonselected, defaultimagesize);
    }
    
    public ToolbarButton(String id, StringProperty label, String iconpath, EventHandler panelbuttonselected, double size) {
        //configure control
        super("TOOLBARBUTTON" + id, label);
        this.imagesize = size;
        this.getStyleClass().add(style);
        this.setPadding(new Insets(10));
        //configure button
        Image image = new Image(iconpath, imagesize, imagesize, true, false);
        ImageView imageview = new ImageView(image);
        Rectangle rectangle = new Rectangle(imagesize, imagesize);
        rectangle.setArcWidth(UIsettings.getImagearc());
        rectangle.setArcHeight(UIsettings.getImagearc());
        imageview.setClip(rectangle);
        imageview.setCache(true);
        GridPane.setHalignment(imageview, HPos.CENTER);
        this.add(imageview, 0, 0);
        //event handlers
        this.setId(id);
        this.setOnMouseClicked(panelbuttonselected);
    }
    
}
