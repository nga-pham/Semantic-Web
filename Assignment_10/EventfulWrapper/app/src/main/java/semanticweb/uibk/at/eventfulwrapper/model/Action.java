package semanticweb.uibk.at.eventfulwrapper.model;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by ngapham on 26.01.18.
 */

public class Action extends Thing {

    // Properties
    Person agent;
    EntryPoint target;

    protected static final String TYPE_NAME = "SearchAction";
    protected static final String TYPE_LABEL = "@type";
    protected static final String AGENT_LABEL = "agent";
    protected static final String TARGET_LABEL = "target";

    // Getter and Setter
    public Person getAgent() {
        return agent;
    }

    public void setAgent(Person agent) {
        this.agent = agent;
    }

    public EntryPoint getTarget() {
        return target;
    }

    public void setTarget(EntryPoint target) {
        this.target = target;
    }

    // Convert into JSON Object
    public JsonObject convertToJsonObject()
    {
        JsonObject jsonAction = Json.createObjectBuilder()
            .add(TYPE_LABEL, TYPE_NAME)
                .build();
        return  jsonAction;
    }

    // print
    @Override
    public String toString() {
        return convertToJsonObject().toString();
    }
}
