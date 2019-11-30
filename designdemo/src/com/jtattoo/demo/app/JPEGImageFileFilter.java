/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtattoo.demo.app;
/**
 *
 * @author Lenovo
 */
import Snapshot.*;
import java.io.File;
import javax.swing.filechooser.FileFilter;
/*
 * This class implements a generic file name filter that allows the listing/selection
 * of JPEG files.
 */
public class JPEGImageFileFilter extends FileFilter implements java.io.FileFilter {

    static String filter = "";

    public static String getFilter() {
        return filter;
    }

    public static void setFilter(String filter) {
        JPEGImageFileFilter.filter = filter;
    }

    public boolean accept(File f) {
//        if (f.getName().toLowerCase().endsWith(".jpeg")) {
//            return true;
//        }
//        if (f.getName().toLowerCase().endsWith(".jpg")) {
//            return true;
//        }
        if (f.isDirectory()) {
            return true;
        }
        if (f.getName().startsWith(filter)) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return "JPG files";
    }
}
