

package org.jfree;

import org.jfree.ui.about.ProjectInfo;

/**
 * This class contains static information about the JCommon class library.
 *
 * @author David Gilbert
 */
public final class JCommon {

    /** Information about the project. */
    public static final ProjectInfo INFO = JCommonInfo.getInstance();
    
    /**
     * Hidden constructor.
     */
    private JCommon() {
        super();
    }
    
    /**
     * Prints information about JCommon to standard output.
     *
     * @param args  no arguments are honored.
     */
    public static void main(final String[] args) {
        System.out.println(JCommon.INFO.toString());
    }

}
