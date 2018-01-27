package semanticweb.uibk.at.eventfulwrapper.model;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by ngapham on 26.01.18.
 */

public class Website extends CreativeWork {

    // Properties
    String url;
    List<Action> potentialAction;

    protected static final String URL_LABEL = "url";
    protected static final String URL_VALUE = "http://eventful.com";
    protected static final String POTENTIAL_ACTION_LABEL = "potentialAction";

    public Website() {
        potentialAction = new ArrayList<Action>();
    }

    // Getter and Setter
    @Override
    public void setUrl(String url) {
        this.url = URL_VALUE;
    }

    public void setPotentialAction(List<Action> potentialAction) {
        this.potentialAction = potentialAction;
    }

    @Override
    public String getUrl() {
        return url;
    }
    
    public List<Action> getPotentialAction() {
        return potentialAction;
    }

    public JsonObject convertToJSON() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(CONTEXT_LABEL, CONTEXT_VALUE)
                .add(URL_LABEL, URL_VALUE);

        if (null == this.getPotentialAction()) {
            // "potentialAction" : "required"
            builder.add(POTENTIAL_ACTION_LABEL + INPUT_LABEL, INPUT_VALUE);
        } else {
            String potential_action_str = "[";
            for(Action item : potentialAction) {
                potential_action_str += item.toString() + ',';
            }
            // trim the last ','
            potential_action_str = potential_action_str.substring(0, potential_action_str.lastIndexOf(',') - 1);
            potential_action_str += ']';
        }

        JsonObject jsonSearchAction = builder.build();

        return jsonSearchAction;
    }

    // print
    @Override
    public String toString() {
        return convertToJSON().toString();
    }
}
