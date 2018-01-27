package semanticweb.uibk.at.eventfulwrapper.model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by ngapham on 26.01.18.
 */

public class SearchAction extends Action {

    // Properties
    String query;
    String keyword;
    String location;

    protected static final String KEYWORD_LABEL = "keyword";
    protected static final String LOCATION_LABEL = "location";

    // Getter and Setter
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public JsonObject convertToJSON() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(CONTEXT_LABEL, CONTEXT_VALUE)
                .add(TYPE_LABEL, TYPE_NAME);

//        String url_template = this.getTarget().getUrlTemplate();
        if (null == this.getTarget().getUrlTemplate() || "".equalsIgnoreCase(this.getTarget().getUrlTemplate())) {
            // "target-input" : "required"
            builder.add(TARGET_LABEL + INPUT_LABEL, INPUT_VALUE);
        } else {
            String url_template = this.getTarget().getUrlTemplate();
            // "target" : "url Template"
            builder.add(TARGET_LABEL, url_template);
        }

        if (null == this.getKeyword() || "".equalsIgnoreCase(this.getKeyword())) {
            // "keyword-input" : "required"
            builder.add(KEYWORD_LABEL + INPUT_LABEL, INPUT_VALUE);
        } else {
            // "keyword" : "literal value"
            builder.add(KEYWORD_LABEL, this.getKeyword());
        }

        if (null == this.getLocation() || "".equalsIgnoreCase(this.getLocation())) {
            // "location-input" : "required"
            builder.add(LOCATION_LABEL+ INPUT_LABEL, INPUT_VALUE);

        } else {
            // "location" : "literal value"
            builder.add(LOCATION_LABEL, this.getLocation());
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
