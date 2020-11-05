/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework.widget;

import base.config.UIsettings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.jdom2.Element;

/**
 *
 * @author Franky Laseure
 */
public class Base {
    
    private Element root;
    
    public Base(Element xmlroot) {
        root = xmlroot;
    }
    
    public Element xmlvalue(String key1) {
        return xmlvalue(key1, null, null);
    }

    public Element xmlvalue(String key1, String key2) {
        return xmlvalue(key1, key2, null);
    }

    public Element xmlvalue(String key1, String key2, String key3) {
        Element element = null;
        boolean keynull = root==null;
        if(!keynull) element = findXMLkey(root, key1);
        keynull = keynull || key2==null;
        if(!keynull) element = findXMLkey(element, key2);
        keynull = keynull || key3==null;
        if(!keynull) element = findXMLkey(element, key3);
        return element;
    }

    public Element findXMLkey(Element element, String key) {
        if(element!=null && key!=null) { return element.getChild(key); }
        else { return null; }
    }    
 
    public ImageView createImageview(Image icon, Integer imagesize) {
        ImageView imageview = new ImageView(icon);
        Rectangle rectangle = new Rectangle(imagesize, imagesize);
        rectangle.setArcWidth(UIsettings.getImagearc());
        rectangle.setArcHeight(UIsettings.getImagearc());
        imageview.setClip(rectangle);
        imageview.setCache(true);
        return imageview;
    }
    
}
