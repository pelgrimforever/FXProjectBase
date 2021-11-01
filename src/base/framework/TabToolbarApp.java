package base.framework;

import base.AppController;
import base.framework.login.Loginpanel;
import base.config.Config;
import base.config.UIsettings;
import base.config.menu.Menuconfig;
import base.config.menu.Menutabconfig;
import base.config.menu.Tabcontrolconfig;
import base.config.menu.Tabpanelconfig;
import base.controls.RotateIconbox;
import base.controls.tabtoolbar.TabToolbar;
import base.controls.tabtoolbar.Toolbar;
import base.controls.tabtoolbar.ToolbarButton;
import base.controls.tabtoolbar.ToolbarControl;
import general.exception.WidgetException;
import base.framework.logincontroler.ControllerEvent;
import base.framework.widget.WidgetEvent;
import base.framework.widget.WidgetFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * App TabToolbar Top framework panel
 * contains
 * - Menu in the form of a tab panel with toolbars
 * - Vertical toolbar showing the live - active widgets
 * - Widget panel
 * @author Franky Laseure
 */
public class TabToolbarApp extends BorderPane {
    
    public static final String style = "tabtoolbarcontrol";
    
    private HashMap<String, ToolbarControl> controls = new HashMap<String, ToolbarControl>();
    private HashMap<String, Tabcontrolconfig> controlconfigs = new HashMap<String, Tabcontrolconfig>();
    
    private ArrayList<WidgetFrame> widgetframes = new ArrayList<WidgetFrame>();
    private WidgetFrame activewidget = null;
    
    //Layout
    private BorderPane toplayout = new BorderPane();
    private VerticalToolbar activebuttonpanel = new VerticalToolbar();
    
    /**
     * constructor
     * translate Menu configuration into Tabs and Tabtoolbars
     * initiate vertical toolbar
     */
    public TabToolbarApp() {
        this.getStyleClass().add(style);
        this.setPadding(new Insets(UIsettings.getPanelpadding()));
        
        //TOP controls
        TabToolbar tabtoolbar = new TabToolbar();
        Menutabconfig menutabconfig;
        Tabcontrolconfig tabcontrolconfig;
        Iterator<Menutabconfig> menutabconfigs = Menuconfig.getMenutabconfigs().iterator();
        Iterator<Tabcontrolconfig> tabcontrolconfigs;
        Tab tab;
        Toolbar toolbar;
        ToolbarButton toolbarbutton;
        String iconpath;
        String id;
        while(menutabconfigs.hasNext()) {
            menutabconfig = menutabconfigs.next();
            Iterator<Tabcontrolconfig> menucontrolconfigs = menutabconfig.getControlconfigs().iterator();
            
            tab = tabtoolbar.addTab(menutabconfig.getLabelname(), menutabconfig.labelProperty());
            toolbar = new Toolbar();
            tab.setContent(toolbar);
            while(menucontrolconfigs.hasNext()) {
                tabcontrolconfig = menucontrolconfigs.next();
                if(tabcontrolconfig.getControltype().equals(Tabcontrolconfig.TYPE_PANEL)) {
                    id = tabcontrolconfig.getLabelname();
                    iconpath = Config.getConfigmap() + tabcontrolconfig.getIcon();
                    toolbarbutton = new ToolbarButton(id, tabcontrolconfig.labelProperty(), iconpath, toolbarbuttonevent);
                    toolbar.addControl(toolbarbutton);
                    controlconfigs.put(id, tabcontrolconfig);
                    controls.put(id, toolbarbutton);
                }
                if(tabcontrolconfig.getControltype().equals(Tabcontrolconfig.TYPE_ACTION)) {
                }
                if(tabcontrolconfig.getControltype().equals(Tabcontrolconfig.TYPE_TABCONTROL)) {
                }
            }
        }
        
        Loginpanel loginform = new Loginpanel();
        
        //TOP Layout control
        toplayout.setStyle(style);
        toplayout.setCenter(tabtoolbar);
        toplayout.setRight(loginform);
        toplayout.setPadding(new Insets(0, 0, 4, 0));
        
        //LEFT PANEL
        activebuttonpanel.setPadding(new Insets(0, 4, 0, 0));
        
        //fill TabToolbarApp
        this.setTop(toplayout);
        this.setLeft(activebuttonpanel);
    }
 
    /**
     * EventHandler for a button on a Toolbar in the tabpanel menu
     * launch the selected Widget
     */
    private EventHandler toolbarbuttonevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            ToolbarButton button = (ToolbarButton)e.getSource();
            Tabpanelconfig controlconfig = (Tabpanelconfig)controlconfigs.get(button.getId());
            activatePanel(controlconfig);
        }
    };
    
    /**
     * EventHandler for a button clicked on the Vertical Toolbar
     * put focus on the selected widget
     */
    private EventHandler activebuttonevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            RotateIconbox button = (RotateIconbox)e.getSource();
            String controlid = button.getId().substring(2);
            Tabpanelconfig controlconfig = (Tabpanelconfig)controlconfigs.get(controlid);
            activatePanel(controlconfig);
        }
    };
    
    /**
     * EventHandler for a control buttin in a widget
     * - WidgetEvent.CLOSEEVENT close the widget and remove it from the vertical toolbar
     * - WidgetEvent.SETFULLSCREENEVENT show the active widget in full screen
     * - WidgetEvent.SETNORMALSCREENEVENT set widget size back to original size
     */
    private EventHandler widgeteventhandler = new EventHandler<WidgetEvent>() {
        @Override public void handle(WidgetEvent e) {
            WidgetFrame widget = e.getWidgetFrame();
            //BUTTON ACTIONS
            if(e.getEventType()==WidgetEvent.CLOSEEVENT) {
                if(widget.getFullscreenstate()) {
                    showFullscreen(false);
                }
                WidgetFrame widgetframe;
                int index=0;
                for(index=0; index<widgetframes.size(); index++) {
                    widgetframe = (WidgetFrame)widgetframes.get(index);
                    if(widgetframe==widget) {
                        widgetframes.remove(index);
                        break;
                    }
                }
                activebuttonpanel.deleteButton(widget);
                //set next widget active
                showNextWidget(index);
            }
            if(e.getEventType()==WidgetEvent.SETFULLSCREENEVENT) {
                showFullscreen(true);
            }
            if(e.getEventType()==WidgetEvent.SETNORMALSCREENEVENT) {
                showFullscreen(false);
            }
        }
    };
    
    /**
     * show next widget on the vertical toolbar
     * @param deletedwidgetindex removed widget index on the toolbar
     */
    private void showNextWidget(int deletedwidgetindex) {
        WidgetFrame nextwidget = null;
        if(widgetframes.size()==0) {
            this.setCenter(null);
        } else {
            if(widgetframes.size()==deletedwidgetindex) {
                //deleted widget was last in row, take previous
                nextwidget = widgetframes.get(deletedwidgetindex-1);
            } else {
                //take current position
                nextwidget = widgetframes.get(deletedwidgetindex);
            }
            activatePanel(nextwidget.getWidget().getConfig());
        }
    }

    /**
     * toggle between fullscreen and normal widget size
     * @param fullscreen true/false
     */
    private void showFullscreen(boolean fullscreen) {
        if(fullscreen) {
            this.setTop(null);
            this.setLeft(null);                
        } else {
            this.setTop(toplayout);
            this.setLeft(activebuttonpanel);                
        }
    }
    
    /**
     * find widget with name in the active widget array
     * @param labelname widget name
     * @return 
     */
    private WidgetFrame searchWidget(String labelname) {
        WidgetFrame widgetframe = null;
        WidgetFrame loadedwidgetframe;
        Iterator<WidgetFrame> widgetframesI = widgetframes.iterator();
        while(widgetframesI.hasNext()) {
            loadedwidgetframe = widgetframesI.next();
            if(loadedwidgetframe.getWidget().getConfig().getLabelname().equals(labelname)) {
                widgetframe = loadedwidgetframe;
                break;
            }
        }
        return widgetframe;
    }
    
    /**
     * activate a widget
     * if it is not loaded, create a new WidgetFrame
     * @param controlconfig widget config
     * @return true if the widget was successfully opened
     */
    private boolean activatePanel(Tabpanelconfig controlconfig) {
        WidgetFrame widgetframe = searchWidget(controlconfig.getLabelname());
        boolean activated = false;
        if(widgetframe==null) {
            try {
                widgetframe = new WidgetFrame(controlconfig);
                widgetframes.add(widgetframe);
            }
            catch(WidgetException e) {
                AppController.showMessage(e.getDetailedMessage());
            }
        }
        if(widgetframe!=null) {
            widgetframe.addEventHandler("tabtoolbarcontrol", widgeteventhandler);
            this.setCenter(widgetframe);
            activewidget = widgetframe;
            activebuttonpanel.addButton(activewidget, activebuttonevent);
            activated = true;
        }
        return activated;
    }
    
}
