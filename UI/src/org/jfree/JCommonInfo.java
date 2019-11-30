
package org.jfree;

import java.util.Arrays;
import java.util.ResourceBundle;

import org.jfree.base.BaseBoot;
import org.jfree.base.Library;
import org.jfree.ui.about.Contributor;
import org.jfree.ui.about.Licences;
import org.jfree.ui.about.ProjectInfo;


public class JCommonInfo extends ProjectInfo {

    /** The singleton instance of the project info object. */
    private static JCommonInfo singleton;

    /**
     * Returns the single instance of this class.
     * 
     * @return The single instance of information about the JCommon library.
     */
    public static synchronized JCommonInfo getInstance() {
        if (singleton == null) {
            singleton = new JCommonInfo();
        }
        return singleton;
    }

    /**
     * Creates a new instance.
     */
    private JCommonInfo() {

        // get a locale-specific resource bundle...
        final String baseResourceClass = "org.jfree.resources.JCommonResources";
        final ResourceBundle resources 
            = ResourceBundle.getBundle(baseResourceClass);

        setName(resources.getString("project.name"));
        setVersion(resources.getString("project.version"));
        setInfo(resources.getString("project.info"));
        setCopyright(resources.getString("project.copyright"));

        setLicenceName("LGPL");
        setLicenceText(Licences.getInstance().getLGPL());

        setContributors(Arrays.asList(
            new Contributor[] {
                new Contributor("Anthony Boulestreau", "-"),
                new Contributor("Jeremy Bowman", "-"),
                new Contributor("J. David Eisenberg", "-"),
                new Contributor("Paul English", "-"),
                new Contributor(
                    "David Gilbert", "david.gilbert@object-refinery.com"
                ),
                new Contributor("Hans-Jurgen Greiner", "-"),
                new Contributor("Arik Levin", "-"),
                new Contributor("Achilleus Mantzios", "-"),
                new Contributor("Thomas Meier", "-"),
                new Contributor("Aaron Metzger", "-"),
                new Contributor("Thomas Morgner", "-"),
                new Contributor("Krzysztof Paz", "-"),
                new Contributor("Nabuo Tamemasa", "-"),
                new Contributor("Mark Watson", "-"),
                new Contributor("Matthew Wright", "-"),
                new Contributor("Hari", "-"),
                new Contributor("Sam (oldman)", "-")
            }
        ));

        addLibrary(
            new Library(
                "JUnit", "3.8", "IBM Public Licence", "http://www.junit.org/"
            )
        );

        setBootClass(BaseBoot.class.getName());
    }
}
