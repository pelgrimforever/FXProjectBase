/*
 * Menutabconfig.java
 */
package base.config.menu;

import base.config.TXT;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jdom2.Element;

/**
 * Menu tab settings for pre-defined menu in FXProjectBase
 * @author Franky Laseure
 */
public class Menutabconfig {
    
    private Element menutabelement;

    private StringProperty labelname = new SimpleStringProperty();
    public final String getLabelname(){return labelname.get();}
    public final void setLabelname(String value){labelname.set(value);}
    public StringProperty labelnameProperty() {return labelname;}
    
    private StringProperty label = new SimpleStringProperty();
    public final String getLabel(){return label.get();}
    public final void setLabel(String value){label.set(value);}
    public StringProperty labelProperty() {return label;}

    private ArrayList<Tabcontrolconfig> controlconfigs = new ArrayList<Tabcontrolconfig>();
    public ArrayList<Tabcontrolconfig> getControlconfigs() { return controlconfigs; }

    /**
     * constructor
     * @param menutabelement xml element for menu tab
     */
    public Menutabconfig(Element menutabelement) {
        this.menutabelement = menutabelement;
        //initialize constants
        labelname.setValue(menutabelement.getValue());
        label.setValue(TXT.getText(labelname.getValue()));        
    }
    
    /**
     * Menu tab GUI elements
     * @param controlconfig Tabcontrolconfig
     */
    public void addControlconfig(Tabcontrolconfig controlconfig) {
        controlconfigs.add(controlconfig);
    }
    
}
