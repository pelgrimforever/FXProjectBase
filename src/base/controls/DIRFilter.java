package base.controls;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Implementation of FileFilter 
 * allows only directories
 * @author pelgrim
 */
public class DIRFilter extends FileFilter {

    //Accept directories and xml files.
    @Override
    public boolean accept(File f) {
        return f.isDirectory();
    }
    //The description of this filter
    @Override
    public String getDescription() {
        return "DIR location";
    }

}

