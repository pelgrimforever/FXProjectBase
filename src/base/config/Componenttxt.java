/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.config;

import org.jdom2.Element;

/**
 *
 * @author Franky Laseure
 */
public class Componenttxt {
    
    private Element componenttxt;

    public Componenttxt(Element componenttxt) {
        this.componenttxt = componenttxt;
    }
    
    public String txt(String name) {
        return componenttxt.getChild(name).getValue();
    }
    
}
