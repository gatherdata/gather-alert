/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.dao.neo4j;

import static org.gatherdata.alert.builder.LanguageScriptBuilder.expressedIn;
import static org.gatherdata.alert.builder.PlannedNotificationBuilder.address;
import static org.gatherdata.alert.builder.RuleSetBuilder.rules;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.gatherdata.alert.builder.ActionPlanBuilder;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.spi.AlertServiceDao;
import org.gatherdata.alert.dao.neo4j.internal.NeoAlertServiceDao;
import org.gatherdata.commons.db.neo4j.NeoServices;
import org.gatherdata.commons.spi.BaseStorageDaoTest;
import org.junit.After;
import org.junit.Test;
import org.neo4j.api.core.EmbeddedNeo;
import org.neo4j.api.core.Transaction;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class NeoAlertServiceDaoTest extends BaseStorageDaoTest<ActionPlan, AlertServiceDao> {

    @Inject
    NeoServices neo;
        
    Random rnd = new Random();

    private Transaction transaction;
    
    @Override
    protected AlertServiceDao createStorageDaoImpl() {
        AlertServiceDao dao = new NeoAlertServiceDao();
        
        // guice up the instance
        Injector injector = Guice.createInjector(new NeoTestingModule());
        injector.injectMembers(this);
        injector.injectMembers(dao);
        
        return dao;
    }
    
    @After
    public void shutdownNeo() {
        String neoStoreDir = ((EmbeddedNeo)neo.neo()).getStoreDir();
        neo.manualShutdown();
        try {
            FileUtils.cleanDirectory(new File(neoStoreDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected ActionPlan createMockEntity() {
        ActionPlan mockEntity = ActionPlanBuilder.plan()
            .named("barWithinFoo" + rnd.nextInt(1000))
            .describedAs("any occurrence of 'bar' within 'foo'")
            .applyingRules(
                    rules("text/xml")
                        .rule(expressedIn("js").script("/bar/.test(body)"))
                )
            .notifying(
                    address("mailto:sysadmin@kollegger.name").message(expressedIn("vm").script("test gather-alert message"))
                )
        .build();
        return mockEntity;
    }

    @Override
    protected void beginTransaction() {
        this.transaction = neo.neo().beginTx();
    }

    @Override
    protected void endTransaction() {
        this.transaction.success();
        this.transaction.finish();
    }

}
