package semanticweb.uibk.at.eventfulwrapper.model;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by ngapham on 26.01.18.
 */

public class Website extends CreativeWork {

    // Properties
    String url;
    List<SearchAction> potentialAction;

    protected static final String URL_LABEL = "url";
    protected static final String URL_VALUE = "http://api.eventful.com";
    protected static final String POTENTIAL_ACTION_LABEL = "potentialAction";

    public Website() {
        potentialAction = new ArrayList<SearchAction>();
    }

    // Getter and Setter
    @Override
    public void setUrl(String url) {
        this.url = URL_VALUE;
    }

    public void setPotentialAction(List<SearchAction> potentialAction) {
        this.potentialAction = potentialAction;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public List<SearchAction> getPotentialAction() {
        return potentialAction;
    }

    public JsonObject convertToJsonLD() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(CONTEXT_LABEL, CONTEXT_VALUE)
                .add(URL_LABEL, URL_VALUE);
        builder.add(TYPE_LABEL, "WebSite");

        if (null == this.getPotentialAction()) {
            // "potentialAction" : "required"
            builder.add(POTENTIAL_ACTION_LABEL + INPUT_LABEL, INPUT_VALUE);
        } else {
            JsonObjectBuilder potential_action_builder = Json.createObjectBuilder();
            JsonArrayBuilder new_array = Json.createArrayBuilder();
            for (SearchAction item : this.getPotentialAction()) {
                JsonObject current_action = item.convertToJsonLD();
                new_array.add(current_action);
            }
            builder.add(POTENTIAL_ACTION_LABEL, new_array);

        }

        JsonObject jsonSearchAction = builder.build();

        return jsonSearchAction;
    }

    // print
    @Override
    public String toString() {
        return convertToJsonLD().toString();
    }
}
