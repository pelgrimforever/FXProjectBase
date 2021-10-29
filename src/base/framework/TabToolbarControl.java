/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.framework;

import base.framework.login.Loginpanel;
import base.config.Config;
import base.config.UIsettings;
import base.config.menu.Menuconfig;
import base.config.menu.Menutabconfig;
import base.config.menu.Menutabcontrolconfig;
import base.config.menu.Menutabpanelconfig;
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
 *
 * @author Franky Laseure
 */
public class TabToolbarControl extends BorderPane {
    
    public static final String style = "tabtoolbarcontrol";
    
    private HashMap<String, ToolbarControl> controls = new HashMap<String, ToolbarControl>();
    private HashMap<String, Menutabcontrolconfig> controlconfigs = new HashMap<String, Menutabcontrolconfig>();
    
    private ArrayList<WidgetFrame> widgetframes = new ArrayList<WidgetFrame>();
    private WidgetFrame activewidget = null;
    
    //Layout
    private BorderPane toplayout = new BorderPane();
    private VerticalToolbar activebuttonpanel = new VerticalToolbar();
    
    public TabToolbarControl() {
        this.getStyleClass().add(style);
        this.setPadding(new Insets(UIsettings.getPanelpadding()));
        
//TOP        
        //TOP controls
        TabToolbar tabtoolbar = new TabToolbar();
        Menutabconfig menutabconfig;
        Menutabcontrolconfig menutabcontrolconfig;
        Iterator<Menutabconfig> menutabconfigs = Menuconfig.getMenutabconfigs().iterator();
        Iterator<Menutabcontrolconfig> menutabcontrolconfigs;
        Tab tab;
        Toolbar toolbar;
        ToolbarButton toolbarbutton;
        String iconpath;
        String id;
        while(menutabconfigs.hasNext()) {
            menutabconfig = menutabconfigs.next();
            Iterator<Menutabcontrolconfig> menucontrolconfigs = menutabconfig.getControlconfigs().iterator();
            
            tab = tabtoolbar.addTab(menutabconfig.getLabelname(), menutabconfig.labelProperty());
            toolbar = new Toolbar();
            tab.setContent(toolbar);
            while(menucontrolconfigs.hasNext()) {
                menutabcontrolconfig = menucontrolconfigs.next();
                if(menutabcontrolconfig.getControltype().equals(Menutabcontrolconfig.TYPE_PANEL)) {
                    id = menutabcontrolconfig.getLabelname();
                    iconpath = Config.getConfigmap() + menutabcontrolconfig.getIcon();
                    toolbarbutton = new ToolbarButton(id, menutabcontrolconfig.labelProperty(), iconpath, toolbarbuttonevent);
                    toolbar.addControl(toolbarbutton);
                    controlconfigs.put(id, menutabcontrolconfig);
                    controls.put(id, toolbarbutton);
                }
                if(menutabcontrolconfig.getControltype().equals(Menutabcontrolconfig.TYPE_ACTION)) {
                }
                if(menutabcontrolconfig.getControltype().equals(Menutabcontrolconfig.TYPE_TABCONTROL)) {
                }
            }
        }
        
        Loginpanel loginform = new Loginpanel();
        
        //TOP Layout control
        toplayout.setStyle(style);
        toplayout.setCenter(tabtoolbar);
        toplayout.setRight(loginform);
        toplayout.setPadding(new Insets(0, 0, 4, 0));
        
//LEFT
        activebuttonpanel.setPadding(new Insets(0, 4, 0, 0));
        
        //fill TabToolbarControl
        this.setTop(toplayout);
        this.setLeft(activebuttonpanel);
    }
 
    private EventHandler toolbarbuttonevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            ToolbarButton button = (ToolbarButton)e.getSource();
            Menutabpanelconfig controlconfig = (Menutabpanelconfig)controlconfigs.get(button.getId());
            activatePanel(controlconfig);
        }
    };
            
    private EventHandler activebuttonevent = new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            RotateIconbox button = (RotateIconbox)e.getSource();
            String controlid = button.getId().substring(2);
            Menutabpanelconfig controlconfig = (Menutabpanelconfig)controlconfigs.get(controlid);
            activatePanel(controlconfig);
        }
    };
    
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
    
    private void showFullscreen(boolean fullscreen) {
        if(fullscreen) {
            this.setTop(null);
            this.setLeft(null);                
        } else {
            this.setTop(toplayout);
            this.setLeft(activebuttonpanel);                
        }
    }
    
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
    
    private boolean activatePanel(Menutabpanelconfig controlconfig) {
        WidgetFrame widgetframe = searchWidget(controlconfig.getLabelname());
        boolean activated = false;
        if(widgetframe==null) {
            try {
                widgetframe = new WidgetFrame(controlconfig);
                widgetframes.add(widgetframe);
            }
            catch(WidgetException e) {
                
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
