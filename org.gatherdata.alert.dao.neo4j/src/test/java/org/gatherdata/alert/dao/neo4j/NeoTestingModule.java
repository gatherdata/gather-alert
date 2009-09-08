package org.gatherdata.alert.dao.neo4j;

import org.gatherdata.commons.db.neo4j.NeoServices;
import org.gatherdata.commons.db.neo4j.internal.NeoServicesImpl;
import org.neo4j.api.core.EmbeddedNeo;
import org.neo4j.api.core.NeoService;
import org.neo4j.util.NeoServiceLifecycle;
import org.neo4j.util.index.IndexService;
import org.neo4j.util.index.NeoIndexService;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

public class NeoTestingModule extends AbstractModule {

    private NeoServices singletonNeo;

    @Override
    protected void configure() {
        singletonNeo = createNeoService();
        singletonNeo.addNeoIndexService();
        
        bind(NeoServices.class).toInstance(singletonNeo);
    }
    
    public NeoServices createNeoService() {
        if (singletonNeo == null) {
            singletonNeo =  new NeoServicesImpl(new EmbeddedNeo("target/testingNeo"));
        }
        return singletonNeo;
    }

}
