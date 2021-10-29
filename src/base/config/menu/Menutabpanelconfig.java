/*
 * Menutabpanelconfig.java
 */
package base.config.menu;

import base.config.Config;
import general.exception.XMLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Menu tab panel settings for pre-defined menu in FXProjectBase
 * @author Franky Laseure
 */
public class Menutabpanelconfig extends Menutabcontrolconfig {
    
    public Element getWidgetconfig() { return configxml; }
    
    private StringProperty id = new SimpleStringProperty();
    public final String getId(){return id.get();}
    public final void setId(String value){id.set(value);}
    public StringProperty IdProperty() {return id;}
    
    private String classname;
    public final String getClassname() { return classname; }
    
    /**
     * constructor
     * @param menutabpanelconfig
     * @throws XMLException 
     */
    public Menutabpanelconfig(Element menutabpanelconfig) throws XMLException {
        super(menutabpanelconfig);
        controltype = TYPE_PANEL;
        if(configxml.getChild("id")!=null) {
            id.setValue(configxml.getChild("id").getValue());
            classname = configxml.getChild("classname").getValue();
        }
    }
    
}
