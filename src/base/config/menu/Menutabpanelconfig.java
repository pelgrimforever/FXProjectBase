/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.config.menu;

import base.config.Config;
import general.exception.XMLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 *
 * @author Franky Laseure
 */
public class Menutabpanelconfig extends Menutabcontrolconfig {
    
    private String widgetconfigfilename;
    private Document widgetconfigxml = null;
    private Element widgetconfig;
    public Element getWidgetconfig() { return widgetconfig; }
    
    private StringProperty id = new SimpleStringProperty();
    public final String getId(){return id.get();}
    public final void setId(String value){id.set(value);}
    public StringProperty IdProperty() {return id;}
    
    private String classname;
    public final String getClassname() { return classname; }
    
    public Menutabpanelconfig(Element menutabpanelconfig) throws XMLException {
        super(menutabpanelconfig);
        controltype = TYPE_PANEL;
        widgetconfigfilename = configxml.getAttribute("config").getValue();
        widgetconfigxml = Config.readXML(widgetconfigfilename);
        widgetconfig = widgetconfigxml.getRootElement();
        if(widgetconfig.getChild("id")!=null) {
            id.setValue(widgetconfig.getChild("id").getValue());
        }
        classname = widgetconfig.getChild("classname").getValue();
    }
    
}
