/*
 * Menuconfig.java
 */
package base.config.menu;

import general.exception.XMLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Menu settings for pre-defined menu in FXProjectBase
 * optional defined at location in the config xml (fxmenuxml value)
 * @author Franky Laseure
 */
public class Menuconfig {
    
    private static Element root;
    private static Document configxml = null;

    private static ArrayList<Menutabconfig> menutabconfigs = new ArrayList();
    private static ArrayList<Menutabcontrolconfig> menutabcontrolconfigs = new ArrayList();
    
    /**
     * initialize menu settings
     * @param xml menu xml
     * @throws XMLException 
     */
    public static void initialize(Document xml) throws XMLException {
        configxml = xml;
        root = configxml.getRootElement();
        Menutabconfig menutabconfig;
        Iterator<Element> menutabs = root.getChild("menu").getChildren().iterator();
        //add menutab configuration
        while(menutabs.hasNext()) {
            menutabconfigs.add(new Menutabconfig(menutabs.next()));
        }
        
        Element tabcontrol;
        Iterator<Element> menutabcontrols = root.getChild("tabcontrol").getChildren().iterator();
        Menutabcontrolconfig menutabcontrolconfig;
        //add controls config for each menutab
        while(menutabcontrols.hasNext()) {
            menutabcontrolconfig = null;
            tabcontrol = menutabcontrols.next();
            String controltype = tabcontrol.getName();
            if(controltype.equals(Menutabcontrolconfig.TYPE_PANEL)) {
                menutabcontrolconfig = new Menutabpanelconfig(tabcontrol);
            }
            if(controltype.equals(Menutabcontrolconfig.TYPE_ACTION)) {

            }
            if(controltype.equals(Menutabcontrolconfig.TYPE_TABCONTROL)) {

            }
            if(menutabcontrolconfig!=null) {
                menutabcontrolconfigs.add(menutabcontrolconfig);
                for(int i=0; i<menutabconfigs.size(); i++) {
                    menutabconfig = menutabconfigs.get(i);
                    if(menutabconfig.getLabelname().equals(menutabcontrolconfig.getTab())) {
                        menutabconfig.addControlconfig(menutabcontrolconfig);
                    }
                }
            }
        }
    }
    
    /**
     * @return menu tabs array
     */
    public static ArrayList<Menutabconfig> getMenutabconfigs() {
        return menutabconfigs;
    }
    
}
