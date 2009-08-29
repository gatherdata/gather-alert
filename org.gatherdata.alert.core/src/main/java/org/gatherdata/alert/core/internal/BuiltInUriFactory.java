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
