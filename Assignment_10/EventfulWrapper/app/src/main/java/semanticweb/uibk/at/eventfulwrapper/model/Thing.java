package semanticweb.uibk.at.eventfulwrapper.model;

/**
 * Created by ngapham on 26.01.18.
 * Base class for all models
 */

public class Thing {

    // Properties
    String identifier;
    String name;
    String description;
    String url;
    Action potentialAction;

    protected static final String CONTEXT_LABEL = "@context";
    protected static final String CONTEXT_VALUE = "http://schema.org";
    protected static final String INPUT_VALUE = "required";
    protected static final String INPUT_LABEL = "-input";
    protected static final String TYPE_LABEL = "@type";

    // Getter and Setter
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
