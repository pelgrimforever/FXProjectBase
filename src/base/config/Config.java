/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.config;

import base.config.menu.Menuconfig;
import base.servlets.DataHandler;
import general.exception.XMLException;
import file.FileextentionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Franky Laseure
 */
public class Config {

    private static Config config = null;
    
    public static FileextentionFilter JPGFILTER = new FileextentionFilter("jpg");
    //public-read var IMAGEPREFIX:String = "file:";
    public static String IMAGEPREFIX = "file://";

    public static String configmap;
    private static final String CONFIGMAP = "config/";
    public static String DEFAULTWEBSERVICE;
    public static Element UIelements;
    
    public String server;
    public String projectpath;
    private static float defautscale = 1f;
    public float scale;
    public final String CONFIGFILE = "config.xml";
    private Document configxml = null;

    public String DEFAULT_LANGUAGE;
    public String TRANSLATION_FILENAME;
    public String MENU_FILENAME;
    public boolean hasMenu() { return !MENU_FILENAME.equals(""); }
    public String DEFAULT_SCREEN;

    public Menuconfig MENU;

    private Element root;
    
    public static Config getInstance() {
        return config;
    }

    public static Config buildInstance(String server, String projectpath) {
        config = new Config(server, projectpath, defautscale);
        return config;
    }
    
    public static Config buildInstance(String server, String projectpath, float scale) {
        config = new Config(server, projectpath, scale);
        return config;
    }
    
    private Config(String server, String projectpath, float scale) {
        //set url variables
        this.server = server;
        this.projectpath = projectpath;
        //set UI scale
        this.scale = scale;
        
        //read config xml
        configmap = server + projectpath + CONFIGMAP;
        try {
            if(configxml==null) configxml = readXML(CONFIGFILE);
            //initialize Config
            initcontants();
            init_configobjects();
        }
        catch(XMLException e) {
            
        }
    }
    
    private void initcontants() {
        root = configxml.getRootElement();

        //initialize
        DEFAULT_LANGUAGE = root.getChild("defaultlanguage").getValue();
        String translationmap = root.getChild("translationmap").getValue();
        TRANSLATION_FILENAME = translationmap + DEFAULT_LANGUAGE + ".xml";
        MENU_FILENAME = root.getChild("fxmenuxml").getValue();
        DEFAULT_SCREEN = root.getChild("defaultscreen").getValue();
        DataHandler.SERVER = root.getChild("server").getValue();
        DEFAULTWEBSERVICE = root.getChild("defaultwebservice").getValue();
        DataHandler.DEFAULTWEBSERVICE = DataHandler.SERVER + DEFAULTWEBSERVICE;
        UIelements = root.getChild("UIelements");
    }        

    private void init_configobjects() throws XMLException {
        TXT.getInstance(readXML(TRANSLATION_FILENAME));
        if(!MENU_FILENAME.equals("")) {
            Menuconfig.getInstance(readXML(MENU_FILENAME));
        }
        UIsettings.getInstance(scale, root.getChild("UIsettings"));
        //DataHandler.config = this;
    }
    
    public static Document readXML(String xmlfile) throws XMLException {
        Document xmldoc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            SAXBuilder saxbuilder = new SAXBuilder();
            if(configmap.startsWith("http:")) {
                xmldoc = saxbuilder.build(new URL(configmap + xmlfile));                
            } else {
                xmldoc = saxbuilder.build(new File(configmap + xmlfile));
            }
        }
        catch(JDOMException jde) {
            throw new XMLException(jde);
        }
        catch(IOException ioe) {
            throw new XMLException(ioe);
        }
        return xmldoc;
    }
    
}
