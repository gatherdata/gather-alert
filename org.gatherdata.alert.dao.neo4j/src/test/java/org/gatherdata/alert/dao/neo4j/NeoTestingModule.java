/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
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
