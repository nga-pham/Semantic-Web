package semanticweb.uibk.at.eventfulapi.model;

import java.util.UUID;

/**
 * Created by ngapham on 26.01.18.
 * Base class for all models
 */

public class Thing {
    UUID identifier;
    String name;
    String url;
    Action potentialAction;
}
