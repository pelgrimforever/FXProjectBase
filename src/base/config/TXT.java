/*
 * TXT.java
 */
package base.config;

import java.util.HashMap;
import java.util.Iterator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Application label translation object
 * all properties and function are implemented static, making them easy accessible in all GUI components
 * @author Franky Laseure
 */
public class TXT {
    
    private static Element root;
    private static Document configxml = null;

    private static StringProperty applicationTitle = new SimpleStringProperty();
    public static final String getApplicationTitle(){return applicationTitle.get();}
    public static final void setApplicationTitle(String value){applicationTitle.set(value);}
    public static StringProperty applicationTitleProperty() {return applicationTitle;}
    
    private static StringProperty applicationUsername = new SimpleStringProperty();
    public static final String getApplicationUsername(){return applicationUsername.get();}
    public static final void setApplicationUsername(String value){applicationUsername.set(value);}
    public static StringProperty applicationUsernameProperty() {return applicationUsername;}

    private static StringProperty applicationPassword = new SimpleStringProperty();
    public static final String getApplicationPassword(){return applicationPassword.get();}
    public static final void setApplicationPassword(String value){applicationPassword.set(value);}
    public static StringProperty applicationPasswordProperty() {return applicationPassword;}
    
    private static StringProperty applicationUserwelcome = new SimpleStringProperty();
    public static final String getApplicationUserwelcome(){return applicationUserwelcome.get();}
    public static final void setApplicationUserwelcome(String value){applicationUserwelcome.set(value);}
    public static StringProperty applicationUserwelcomeProperty() {return applicationUserwelcome;}

    private static StringProperty applicationButtonlogin = new SimpleStringProperty();
    public static final String getApplicationButtonlogin(){return applicationButtonlogin.get();}
    public static final void setApplicationButtonlogin(String value){applicationButtonlogin.set(value);}
    public static StringProperty applicationButtonloginProperty() {return applicationButtonlogin;}

    private static StringProperty applicationLoginfailed = new SimpleStringProperty();
    public static final String getApplicationLoginfailed(){return applicationLoginfailed.get();}
    public static final void setApplicationLoginfailed(String value){applicationLoginfailed.set(value);}
    public static StringProperty applicationLoginfailedProperty() {return applicationLoginfailed;}

    private static StringProperty applicationButtonregister = new SimpleStringProperty();
    public static final String getApplicationButtonregister(){return applicationButtonregister.get();}
    public static final void setApplicationButtonregister(String value){applicationButtonregister.set(value);}
    public static StringProperty applicationButtonregisterProperty() {return applicationButtonregister;}
    
    private static StringProperty applicationButtonregisternew = new SimpleStringProperty();
    public static final String getApplicationButtonregisternew(){return applicationButtonregisternew.get();}
    public static final void setApplicationButtonregisternew(String value){applicationButtonregisternew.set(value);}
    public static StringProperty applicationButtonregisternewProperty() {return applicationButtonregisternew;}

    private static StringProperty applicationButtonlogout = new SimpleStringProperty();
    public static final String getApplicationButtonlogout(){return applicationButtonlogout.get();}
    public static final void setApplicationButtonlogout(String value){applicationButtonlogout.set(value);}
    public static StringProperty applicationButtonlogoutProperty() {return applicationButtonlogout;}

    private static HashMap<String, Widgettxt> widgettxts = new HashMap();
    private static HashMap<String, Componenttxt> componenttxts = new HashMap();
    
    /**
     * initialize all settings
     * @param xml Translation xml document
     */
    public static void initialize(Document xml) {
        configxml = xml;
        root = configxml.getRootElement();
        applicationTitle.setValue(root.getChild("APPLICATION_TITLE").getValue());
        applicationUsername.setValue(root.getChild("APPLICATION_USERNAME").getValue());
        applicationPassword.setValue(root.getChild("APPLICATION_PASSWORD").getValue());
        applicationUserwelcome.setValue(root.getChild("APPLICATION_USERWELCOME").getValue());
        applicationButtonlogin.setValue(root.getChild("APPLICATION_BUTTONLOGIN").getValue());
        applicationLoginfailed.setValue(root.getChild("APPLICATION_LOGINFAILED").getValue());
        applicationButtonregister.setValue(root.getChild("APPLICATION_BUTTONREGISTER").getValue());
        applicationButtonregisternew.setValue(root.getChild("APPLICATION_BUTTONREGISTERNEW").getValue());
        applicationButtonlogout.setValue(root.getChild("APPLICATION_BUTTONLOGOUT").getValue());

        Element element;
        Iterator<Element> components = root.getChildren("COMPONENT").iterator();
        while(components.hasNext()) {
            element = components.next();
            componenttxts.put(element.getAttribute("id").getValue(), new Componenttxt(element));
        }
        Iterator<Element> widgets = root.getChildren("WIDGET").iterator();
        while(widgets.hasNext()) {
            element = widgets.next();
            widgettxts.put(element.getAttribute("id").getValue(), new Widgettxt(element));
        }
    }
    
    /**
     * get application label
     * @param name label name
     * @return label translation
     */
    public static String getText(String name) {
        return root.getChild(name).getValue();
    }

    /**
     * get component element with attribute id = componentid
     * @param componentid
     * @return XML element
     */
    public static Componenttxt getComponenttxt(String componentid) {
        return componenttxts.get(componentid);
    }

    /**
     * get widget element with attribute id = widgetid
     * @param widgetid
     * @return XML element
     */
    public static Widgettxt getWidgettxt(String widgetid) {
        return widgettxts.get(widgetid);
    }
    
}
