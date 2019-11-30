
package org.jfree.base;

import java.lang.reflect.Method;

import org.jfree.base.config.HierarchicalConfiguration;
import org.jfree.base.config.PropertyFileConfiguration;
import org.jfree.base.config.SystemPropertyConfiguration;
import org.jfree.base.modules.PackageManager;
import org.jfree.base.modules.SubSystem;
import org.jfree.util.Configuration;
import org.jfree.util.Log;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.ExtendedConfiguration;
import org.jfree.util.ExtendedConfigurationWrapper;

public abstract class AbstractBoot implements SubSystem {

    /** The configuration wrapper around the plain configuration. */
    private ExtendedConfigurationWrapper extWrapper;

    /** A packageManager instance of the package manager. */
    private PackageManager packageManager;
  
    /** Global configuration. */
    private Configuration globalConfig;

    /** A flag indicating whether the booting is currenly in progress. */
    private boolean bootInProgress;
    
    /** A flag indicating whether the booting is complete. */
    private boolean bootDone;

    /**
     * Default constructor.
     */
    protected AbstractBoot() {
    }

   
    public synchronized PackageManager getPackageManager() {
        if (this.packageManager == null) {
            this.packageManager = PackageManager.createInstance(this);
        }
        return this.packageManager;
    }

    
    public synchronized Configuration getGlobalConfig() {
        if (this.globalConfig == null) {
            this.globalConfig = loadConfiguration();
            start();
        }
        return this.globalConfig;
    }

   
    public final synchronized boolean isBootInProgress() {
        return this.bootInProgress;
    }

   
    public final synchronized boolean isBootDone() {
        return this.bootDone;
    }

    /**
     * Loads the configuration. This will be called exactly once.
     * 
     * @return The configuration.
     */
    protected abstract Configuration loadConfiguration();

    /**
     * Starts the boot process.
     */
    public final void start() {

        synchronized(this) {
            if (isBootInProgress() || isBootDone()) {
                return;
            }
            this.bootInProgress = true;
        }

        // boot dependent libraries ...
        final BootableProjectInfo info = getProjectInfo();
        if (info != null) {
            Log.info (info.getName() + " " + info.getVersion());
            final BootableProjectInfo[] childs = info.getDependencies();
            for (int i = 0; i < childs.length; i++) {
                final AbstractBoot boot = loadBooter(childs[i].getBootClass());
                if (boot != null) {
                    boot.start();
                }
            }
        }
        performBoot();

        synchronized(this) {
            this.bootInProgress = false;
            this.bootDone = true;
        }
    }

    /**
     * Performs the boot.
     */
    protected abstract void performBoot();

    /**
     * Returns the project info.
     *
     * @return The project info.
     */
    protected abstract BootableProjectInfo getProjectInfo();

    /**
     * Loads the specified booter implementation.
     * 
     * @param classname  the class name.
     * 
     * @return The boot class.
     */
    protected AbstractBoot loadBooter(final String classname) {
        if (classname == null) {
            return null;
        }
        try {
            final Class c = ObjectUtilities.getClassLoader(getClass()).loadClass(classname);
            final Method m = c.getMethod("getInstance", null);
            return (AbstractBoot) m.invoke(null, null);
        }
        catch(Exception e) {
            Log.info ("Unable to boot dependent class: " + classname);
            return null;
        }
    }

    /**
     * Creates a default configuration setup, which loads its settings from
     * the static configuration (defaults provided by the developers of the
     * library) and the user configuration (settings provided by the deployer).
     * The deployer's settings override the developer's settings.
     *
     * If the parameter <code>addSysProps</code> is set to true, the system
     * properties will be added as third configuration layer. The system properties
     * configuration allows to override all other settings.
     *
     * @param staticConfig the resource name of the developers configuration
     * @param userConfig the resource name of the deployers configuration
     * @param addSysProps a flag defining whether to include the system properties into
     * the configuration.
     * @return the configured Configuration instance.
     */
    protected Configuration createDefaultHierarchicalConfiguration
        (final String staticConfig, final String userConfig, final boolean addSysProps)
    {
        final HierarchicalConfiguration globalConfig = new HierarchicalConfiguration();

        if (staticConfig != null) {
          final PropertyFileConfiguration rootProperty = new PropertyFileConfiguration();
          rootProperty.load(staticConfig);
          globalConfig.insertConfiguration(rootProperty);
          globalConfig.insertConfiguration(getPackageManager().getPackageConfiguration());
        }
        if (userConfig != null)
        {
          final PropertyFileConfiguration baseProperty = new PropertyFileConfiguration();
          baseProperty.load(userConfig);
          globalConfig.insertConfiguration(baseProperty);
        }
        final SystemPropertyConfiguration systemConfig = new SystemPropertyConfiguration();
        globalConfig.insertConfiguration(systemConfig);
        return globalConfig;
    }

    /**
     * Returns the global configuration as extended configuration.
     *
     * @return the extended configuration.
     */
    public synchronized ExtendedConfiguration getExtendedConfig ()
    {
      if (extWrapper == null)
      {
        extWrapper = new ExtendedConfigurationWrapper(getGlobalConfig());
      }
      return extWrapper;
    }
}
