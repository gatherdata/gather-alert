/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.internal;

import java.net.URI;
import java.net.URISyntaxException;

public class BuiltInUriFactory {

	public static URI createUriFor(String ssp, String fragment) {
		URI builtinUri = null;
		try {
	        builtinUri = new URI("builtin", ssp, fragment);
        } catch (URISyntaxException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return builtinUri;
    }

}
