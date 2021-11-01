/*
 * Base.java
 */
package base.framework.widget;

import base.config.UIsettings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.jdom2.Element;

/**
 * General Base functionality
 * XML functionality
 * Image conversion
 * @author Franky Laseure
 */
public class Base {
    
    private Element root;
    
    /**
     * constructor, set XML root Element
     * @param xmlroot widget XML root element
     */
    public Base(Element xmlroot) {
        root = xmlroot;
    }
    
    /**
     * get key1 element in root element
     * @param key1 xml tab
     * @return Element
     */
    public Element xmlvalue(String key1) {
        return xmlvalue(key1, null, null);
    }

    /**
     * get key1/key2 elements in root element, 
     * where key1 = parent of key2
     * null values for keys are ignored
     * @param key1 xml tab
     * @param key2 xml tab
     * @return Element
     */
    public Element xmlvalue(String key1, String key2) {
        return xmlvalue(key1, key2, null);
    }

    /**
     * get key1/key2/key3 elements in root element, 
     * where key1 = parent of key2 = parent of key3
     * null values for keys are ignored
     * @param key1 xml tab
     * @param key2 xml tab
     * @param key3 xml tab
     * @return Element
     */
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

    /**
     * get element for key
     * @param element XML Element
     * @param key tag name
     * @return Element
     */
    public Element findXMLkey(Element element, String key) {
        if(element!=null && key!=null) { return element.getChild(key); }
        else { return null; }
    }    
 
    /**
     * Initialize ImageView with Image
     * @param icon Image
     * @param imagesize rectangular size
     * @return ImageView
     */
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
