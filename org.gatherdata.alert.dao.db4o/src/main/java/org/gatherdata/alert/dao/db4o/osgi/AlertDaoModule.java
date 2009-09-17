package org.gatherdata.alert.dao.db4o.osgi;

import static org.ops4j.peaberry.Peaberry.service;

import java.io.File;

import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;
import com.db4o.osgi.Db4oService;
import com.db4o.typehandlers.TypeHandler4;
import com.db4o.typehandlers.TypeHandlerPredicate;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import org.gatherdata.alert.core.spi.AlertServiceDao;
import org.gatherdata.alert.dao.db4o.internal.AlertServiceDaoDb4o;
import org.gatherdata.commons.db.db4o.DateTimeHandler;
import org.gatherdata.commons.db.db4o.DateTimeHandlerPredicate;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.util.AbstractWatcher;

import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.Filters.ldap;

/**
 * The GuiceBindingModule specifies bindings to provided and
 * consumed services for this bundle using Google Guice with
 * Peaberry extensions for OSGi.
 *
 */
public class AlertDaoModule extends AbstractModule {
    
    public static final String DEFAULT_DATA_DIR = "alert" + File.separatorChar + "db4o";

    private static final int OBJECT_DEPTH = 3;

    @Inject
    Db4oService db4oService;
    
    @Override 
    protected void configure() {
        new File(DEFAULT_DATA_DIR).mkdirs();
        
        Configuration config = db4oService.newConfiguration();
        config.activationDepth(OBJECT_DEPTH);
        config.updateDepth(OBJECT_DEPTH);
        
        // ABKTODO: figure out what is wrong with the custom TypeHandler support
//        TypeHandlerPredicate dateTimePredicate = new DateTimeHandlerPredicate();
//        TypeHandler4 dateTimeHandler = new DateTimeHandler();
//        config.registerTypeHandler(dateTimePredicate, dateTimeHandler);
        
        ObjectContainer archiverObjectContainer = db4oService.openFile(config, DEFAULT_DATA_DIR + File.separatorChar + "db4o.db");

        // local binds
        bind(ObjectContainer.class).toInstance(archiverObjectContainer);

        // exports
        bind(export(AlertServiceDao.class)).toProvider(service(AlertServiceDaoDb4o.class).export());

        
    }
    
}
