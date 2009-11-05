/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.internal;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;

import org.gatherdata.alert.core.internal.AlertServiceImpl;
import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.impl.MutableActionPlan;
import org.gatherdata.alert.core.spi.AlertService;
import org.gatherdata.alert.core.spi.AlertServiceDao;
import org.gatherdata.commons.spi.BaseStorageServiceTest;
import org.junit.Test;

public class AlertServiceImplTest extends BaseStorageServiceTest<ActionPlan, AlertServiceDao, AlertServiceImpl> {

    @Override
    protected AlertServiceDao createMockDao() {
        return createMock(AlertServiceDao.class);
    }

    @Override
    protected AlertServiceImpl createStorageServiceImpl() {
        return new AlertServiceImpl();
    }

    @Override
    protected void injectDaoIntoService(AlertServiceDao dao, AlertServiceImpl service) {
        service.dao = dao;
    }

    @Override
    protected ActionPlan createMockEntity() {
        return new MutableActionPlan();
    }


}
