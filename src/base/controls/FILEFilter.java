package base.controls;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author pelgrim
 */
public class FILEFilter extends FileFilter {

    private String extention = null;
    
    public FILEFilter() {
    }
    
    public FILEFilter(String extention) {
        this.extention = extention;
    }
    
    //Accept directories and xml files.
    @Override
    public boolean accept(File f) {
        boolean acceptfile = false;
        if(f.isFile()) {
            if(extention==null) {
                acceptfile = true;
            } else {
                acceptfile = f.getName().endsWith(extention);
            }
        }
        return acceptfile;
    }
    //The description of this filter
    @Override
    public String getDescription() {
        return "FILE location";
    }

}

