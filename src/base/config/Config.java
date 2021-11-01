/*
 * Config.java
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
 * FX GUI configuration
 * is implemented as Singleton
 * @author Franky Laseure
 */
public class Config {

    private static Config config = null;
    
    public static FileextentionFilter JPGFILTER = new FileextentionFilter("jpg");
    //public-read var IMAGEPREFIX:String = "file:";
    public static String IMAGEPREFIX = "file://";

    private static String configmap;
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
    
    /**
     * @return Config instance
     */
    public static Config getInstance() {
        return config;
    }

    /**
     * initialize Singleton instance with default scale
     * @param server server path
     * @param projectpath project path on server
     * @return Config instance
     */
    public static Config buildInstance(String server, String projectpath) {
        config = new Config(server, projectpath, defautscale);
        return config;
    }
    
    /**
     * initialize Singleton instance
     * @param server server path
     * @param projectpath project path on server
     * @param scale GUI scale
     * @return Config instance
     */
    public static Config buildInstance(String server, String projectpath, float scale) {
        config = new Config(server, projectpath, scale);
        return config;
    }
    
    /**
     * constructor
     * set path and scale parameters
     * read config xml file
     * initialize config constants and pre-configured menu settings
     * @param server server path
     * @param projectpath project path on server
     * @param scale GUI scale
     */
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
    
    /**
     * @return formatted config map (add file:// if it is not file on a http address)
     */
    public static String getConfigmap() {
        if(configmap.startsWith("http:")) {
            return configmap;
        } else {
            return IMAGEPREFIX + configmap;
        }
        
    }
    
    /**
     * initialize constants from config xml
     */
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

    /**
     * initialize menu settings from translation xml file
     * @throws XMLException 
     */
    private void init_configobjects() throws XMLException {
        TXT.initialize(readXML(TRANSLATION_FILENAME));
        if(!MENU_FILENAME.equals("")) {
            Menuconfig.initialize(readXML(MENU_FILENAME));
        }
        UIsettings.initialize(scale, root.getChild("UIsettings"));
        //DataHandler.config = this;
    }
    
    /**
     * read XML file
     * @param xmlfile file path
     * @return XML Document
     * @throws XMLException Exceptions reading xml file are thrown as XMLException
     */
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
        catch(JDOMException | IOException jde) {
            throw new XMLException(jde);
        }
        return xmldoc;
    }
    
}
