/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base.config;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Font;
import org.jdom2.Element;

/**
 *
 * @author Franky Laseure
 */
public class UIsettings {
    
    private static float calculatedscale = 1f;
    private static Element uisettingselement;
    
    private static FloatProperty scale = new SimpleFloatProperty();
    public static final Float getScale(){return scale.get();}
    public static final void setScale(Float value){scale.set(value);}
    public static FloatProperty scaleProperty() {return scale;}

    private static StringProperty fontname = new SimpleStringProperty();
    public static final String getFontname(){return fontname.get();}
    public static final void setFontname(String value){fontname.set(value);}
    public static StringProperty fontnameProperty() {return fontname;}

    private static DoubleProperty fontsize = new SimpleDoubleProperty();
    public static final Double getFontsize(){return fontsize.get();}
    public static final void setFontsize(Double value){fontsize.set(value);}
    public static DoubleProperty fontsizeProperty() {return fontsize;}

    private static DoubleProperty buttonfontsize = new SimpleDoubleProperty();
    public static final Double getButtonfontsize(){return buttonfontsize.get();}
    public static final void setButtonfontsize(Double value){buttonfontsize.set(value);}
    public static DoubleProperty buttonfontsizeProperty() {return buttonfontsize;}

    private static DoubleProperty tabfontsize = new SimpleDoubleProperty();
    public static final Double getTabfontsize(){return tabfontsize.get();}
    public static final void setTabfontsize(Double value){tabfontsize.set(value);}
    public static DoubleProperty tabfontsizeProperty() {return tabfontsize;}

    private static DoubleProperty panelpadding = new SimpleDoubleProperty();
    public static final Double getPanelpadding(){return panelpadding.get();}
    public static final void setPanelpadding(Double value){panelpadding.set(value);}
    public static DoubleProperty panelpaddingProperty() {return panelpadding;}

    private static DoubleProperty panelarc = new SimpleDoubleProperty();
    public static final Double getPanelarc(){return panelarc.get();}
    public static final void setPanelarc(Double value){panelarc.set(value);}
    public static DoubleProperty panelarcProperty() {return panelarc;}

    private static DoubleProperty imagearc = new SimpleDoubleProperty();
    public static final Double getImagearc(){return imagearc.get();}
    public static final void setImagearc(Double value){imagearc.set(value);}
    public static DoubleProperty imagearcProperty() {return imagearc;}

    private static final ObjectProperty<Font> normalfont = new SimpleObjectProperty<Font>(Font.getDefault());
    public static Font getNormalfont() { return normalfont.get(); }
    public static void setNormalfont(Font newValue) { normalfont.set(newValue); }
    public static ObjectProperty<Font> normalfontProperty() { return normalfont; }    
    
    private static final ObjectProperty<Font> buttonfont = new SimpleObjectProperty<Font>(Font.getDefault());
    public static Font getButtonfont() { return buttonfont.get(); }
    public static void setButtonfont(Font newValue) { buttonfont.set(newValue); }
    public static ObjectProperty<Font> buttonfontProperty() { return buttonfont; }    
    
    private static final ObjectProperty<Font> tabfont = new SimpleObjectProperty<Font>(Font.getDefault());
    public static Font getTabfont() { return tabfont.get(); }
    public static void setTabfont(Font newValue) { tabfont.set(newValue); }
    public static ObjectProperty<Font> tabfontProperty() { return tabfont; }    

    private static StringProperty waiticon = new SimpleStringProperty();
    public static final String getWaiticon(){return waiticon.get();}
    public static final void setWaiticon(String value){waiticon.set(value);}
    public static StringProperty waiticonProperty() {return waiticon;}
    
    private static final ObjectProperty<Image> waitimage = new SimpleObjectProperty<Image>();
    public static Image getWaitimage() { return waitimage.get(); }
    public static void setWaitimage(Image newValue) { waitimage.set(newValue); }
    public static ObjectProperty<Image> waitimageProperty() { return waitimage; }    

    private static StringProperty expandicon = new SimpleStringProperty();
    public static final String getExpandicon(){return expandicon.get();}
    public static final void setExpandicon(String value){expandicon.set(value);}
    public static StringProperty expandiconProperty() {return expandicon;}
    
    private static final ObjectProperty<Image> expandimage = new SimpleObjectProperty<Image>();
    public static Image getExpandimage() { return expandimage.get(); }
    public static void setExpandimage(Image newValue) { expandimage.set(newValue); }
    public static ObjectProperty<Image> expandimageProperty() { return expandimage; }    
    
    public static void getInstance(float newscale, Element xmlelement) {
        calculatedscale = newscale;
        uisettingselement = xmlelement;
        setScale(calculatedscale);

        setFontname(uisettingselement.getChild("fontname").getValue());
        setFontsize(getScale() * Double.valueOf(uisettingselement.getChild("fontsize").getValue()));
        setButtonfontsize(getScale() * Double.valueOf(uisettingselement.getChild("buttonfontsize").getValue()));
        setTabfontsize(getScale() * Double.valueOf(uisettingselement.getChild("tabfontsize").getValue()));

        setPanelpadding(Double.valueOf(uisettingselement.getChild("panelpadding").getValue()));
        setPanelarc(Double.valueOf(uisettingselement.getChild("panelarc").getValue()));

        setImagearc(Double.valueOf(uisettingselement.getChild("imagearc").getValue()));

        setNormalfont(new Font(getFontname(), getFontsize()));
        setButtonfont(new Font(getFontname(), getButtonfontsize()));
        setTabfont(new Font(getFontname(), getTabfontsize()));

        setWaiticon(Config.UIelements.getChild("waiticon").getValue());
        setWaitimage(new Image(Config.IMAGEPREFIX + Config.configmap + getWaiticon()));
        setExpandicon(Config.UIelements.getChild("expandicon").getValue());
        setExpandimage(new Image(Config.IMAGEPREFIX + Config.configmap + getExpandicon()));
    }
}
