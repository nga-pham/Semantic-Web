package semanticweb.uibk.at.eventfulwrapper.model;

import java.util.UUID;

/**
 * Created by ngapham on 26.01.18.
 * Base class for all models
 */

public class Thing {

    // Properties
    UUID identifier;
    String name;
    String url;
    Action potentialAction;

    protected static final String CONTEXT_LABEL = "@context";
    protected static final String CONTEXT_VALUE = "http://schema.org";
    protected static final String INPUT_VALUE = "required";
    protected static final String INPUT_LABEL = "-input";

    // Getter and Setter
    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
