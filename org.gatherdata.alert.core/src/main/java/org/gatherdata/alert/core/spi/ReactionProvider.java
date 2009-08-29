package org.gatherdata.alert.core.spi;

import java.net.URI;
import java.util.List;

import org.gatherdata.alert.core.model.Reaction;

public interface ReactionProvider {

    List<Reaction> getAll();

    Reaction get(URI id);

}
