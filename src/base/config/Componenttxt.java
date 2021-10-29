/*
 * Componenttxt.java
 */
package base.config;

import org.jdom2.Element;

/**
 * Component Text elements
 * defined in config/text/BE_NL.xml
 * BE_NL is an example of a translation file for labels
 * @author Franky Laseure
 */
public class Componenttxt {
    
    private Element componenttxt;

    /**
     * constructor, initializing the root element of the component
     * @param componenttxt 
     */
    public Componenttxt(Element componenttxt) {
        this.componenttxt = componenttxt;
    }
    
    /**
     * @param name label name
     * @return translation for label
     */
    public String txt(String name) {
        return componenttxt.getChild(name).getValue();
    }
    
}
