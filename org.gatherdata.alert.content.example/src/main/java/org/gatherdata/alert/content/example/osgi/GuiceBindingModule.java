/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.content.example.osgi;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.Attributes.properties;
import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.TypeLiterals.iterable;

import java.util.Map;

import org.gatherdata.alert.core.model.mock.MockActionPlanFactory;
import org.gatherdata.alert.core.spi.AlertService;
import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.util.AbstractWatcher;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class GuiceBindingModule extends AbstractModule {

    @Inject
    AlertService alertService;

    @Override
    protected void configure() {
        bind(AlertService.class).toProvider(service(AlertService.class).single());
    }

}
