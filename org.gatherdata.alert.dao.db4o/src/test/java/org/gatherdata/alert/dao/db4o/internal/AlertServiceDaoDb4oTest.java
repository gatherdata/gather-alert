/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.dao.db4o.internal;

import static org.gatherdata.alert.builder.LanguageScriptBuilder.expressedIn;
import static org.gatherdata.alert.builder.PlannedNotificationBuilder.address;
import static org.gatherdata.alert.builder.RuleSetBuilder.rules;

import static org.gatherdata.commons.junit.ContainsAll.containsAll;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.gatherdata.alert.builder.ActionPlanBuilder;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.RuleSet;
import org.gatherdata.alert.core.model.impl.ActionPlanSupport;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
import org.gatherdata.alert.core.spi.AlertServiceDao;
import org.gatherdata.alert.dao.db4o.internal.AlertServiceDaoDb4o;
import org.gatherdata.commons.spi.BaseStorageDaoTest;
import org.junit.After;
import org.junit.Test;

import com.db4o.ObjectContainer;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class AlertServiceDaoDb4oTest extends BaseStorageDaoTest<ActionPlan, AlertServiceDao> {

    Random rnd = new Random();
    int mockCount = 0;

    @Inject
    ObjectContainer db4o;
    
    @Override
    protected AlertServiceDao createStorageDaoImpl() {
        AlertServiceDaoDb4o dao = new AlertServiceDaoDb4o();
        
        // guice up the instance
        Injector injector = Guice.createInjector(new Db4TestingModule());
        injector.injectMembers(this);
        injector.injectMembers(dao);

        return dao;
    }
    
    @After
    public void shutdownDatabase() {
        db4o.close();
    }
    
    @Override
    protected void beginTransaction() {
        // TODO Auto-generated method stub
        
    }

	@Override
	protected void rollbackTransaction() {
		db4o.rollback();
	}

    @Override
    protected void endTransaction() {
        db4o.commit();
    }

    @Override
    protected ActionPlan createMockEntity() {
        mockCount++;
        ActionPlan mockEntity = ActionPlanBuilder.plan()
        	.named("barWithinFoo" + mockCount)
        	.describedAs("any occurrence of 'bar' within 'foo'" + rnd.nextInt(1000))
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
    
    @Test
    public void shouldBeDeeplyEqualToOriginalEntity() {
        ActionPlan originalEntity = createMockEntity();
        ActionPlan savedEntity = dao.save(originalEntity);
        ActionPlan retrievedEntity = dao.get(originalEntity.getUid());

        assertTrue(ActionPlanSupport.deepEquals(originalEntity, savedEntity));
        assertTrue(ActionPlanSupport.deepEquals(originalEntity, retrievedEntity));
    }

    
    @Test
    public void shouldUpdateExistingEntityWithWorkingCopy() {
        ActionPlan originalEntity = createMockEntity();
        ActionPlan savedEntity = dao.save(originalEntity);
        ActionPlan retrievedEntity = dao.get(originalEntity.getUid());

        assertTrue(ActionPlanSupport.deepEquals(originalEntity, savedEntity));
        assertTrue(ActionPlanSupport.deepEquals(originalEntity, retrievedEntity));
        
        MutableActionPlan workingCopy = new MutableActionPlan();
        workingCopy.copy(retrievedEntity);
        
        assertTrue(ActionPlanSupport.deepEquals(workingCopy, retrievedEntity));
    }
    
    @Test
    public void shouldGetAllSavedEntitiesExplicitCheck() {
        final int EXPECTED_NUMBER_OF_ENTITIES = new Random().nextInt(100);
        List<ActionPlan> entitiesToSave = new ArrayList<ActionPlan>();

        beginTransaction();
        for (int i=0; i< EXPECTED_NUMBER_OF_ENTITIES; i++) {
        	ActionPlan entityToSave = createMockEntity();
            entitiesToSave.add(entityToSave);
            dao.save(entityToSave);
        }
        endTransaction();
                
        assertThat(dao.getCount(), equalTo((Integer)EXPECTED_NUMBER_OF_ENTITIES));
        
        beginTransaction();
        Iterable<ActionPlan> allEntitiesList = (Iterable<ActionPlan>) dao.getAll();
        for (ActionPlan plan : allEntitiesList) {
        	assertTrue(entitiesToSave.contains(plan));
        }
        endTransaction();
    }
    
}
