package semanticweb.uibk.at.eventfulwrapper.model;

import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import semanticweb.uibk.at.eventfulwrapper.control.EventfulImpl;

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
    protected static final String QUERY_LABEL = "query-input";

    // Getter and Setter
    public String getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> list_criteria) {
        this.query = new EventfulImpl().createSearchQuery(list_criteria);
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

    public JsonObject convertToJsonLD() {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        /*if (null == this.getTarget().getUrlTemplate() || "".equalsIgnoreCase(this.getTarget().getUrlTemplate())) {
            // "target-input" : "required"
            builder.add(TARGET_LABEL + INPUT_LABEL, INPUT_VALUE);
        } else {
            String url_template = this.getTarget().getUrlTemplate();
            // "target" : "url Template"
            builder.add(TARGET_LABEL, url_template);
        }*/

        if (null == this.getQuery() || "".equalsIgnoreCase(this.getQuery())) {
            // "query-input" : "required"
            builder.add(QUERY_LABEL + INPUT_LABEL, INPUT_VALUE);

        } else {
            // "query" : "literal value"
            builder.add(QUERY_LABEL, this.getQuery());
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
