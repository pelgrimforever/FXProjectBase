/*
 * Menutabcontrolconfig.java
 */
package base.config.menu;

import base.config.TXT;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jdom2.Element;

/**
 * Menu tab controls settings for pre-defined menu in FXProjectBase
 * @author Franky Laseure
 */
public class Menutabcontrolconfig {
    
    protected Element configxml;

    public static final String TYPE_PANEL = "panel";
    public static final String TYPE_ACTION = "action";
    public static final String TYPE_TABCONTROL = "tabcontrol";

    protected String controltype;
    public String getControltype() {
        return controltype;
    }
    
    private StringProperty labelname = new SimpleStringProperty();
    public final String getLabelname(){return labelname.get();}
    public final void setLabelname(String value){labelname.set(value);}
    public StringProperty labelnameProperty() {return labelname;}
    
    private StringProperty label = new SimpleStringProperty();
    public final String getLabel(){return label.get();}
    public final void setLabel(String value){label.set(value);}
    public StringProperty labelProperty() {return label;}
    
    private StringProperty icon = new SimpleStringProperty();
    public final String getIcon(){return icon.get();}
    public final void setIcon(String value){icon.set(value);}
    public StringProperty iconProperty() {return icon;}
    
    private StringProperty tab = new SimpleStringProperty();
    public final String getTab(){return tab.get();}
    public final void setTab(String value){tab.set(value);}
    public StringProperty tabProperty() {return tab;}
    
    /**
     * constructor
     * @param menutabcontrolelement 
     */
    public Menutabcontrolconfig(Element menutabcontrolelement) {
        this.configxml = menutabcontrolelement;
        //initialise constants
        labelname.setValue(configxml.getChildText("id"));
        label.setValue(TXT.getText(labelname.getValue()));
        icon.setValue(configxml.getAttribute("icon").getValue());
        tab.setValue(configxml.getAttribute("menutab").getValue());        
    }
    
}
