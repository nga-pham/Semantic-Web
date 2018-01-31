package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import semanticweb.uibk.at.eventfulwrapper.utils.BaseProperty;

/**
 * Created by ngapham on 29.01.18.
 */

public class JsonConverterImpl implements JsonConverter {
    @Override
    public JsonObject convertToJsonObject(InputStream inputStream) {
        JsonObject object = null;
        object = Json.createReader(inputStream).readObject();

        return object;
    }

    @Override
    public JsonObject convertToJsonObject(InputStream inputStream, String entity) {
        JsonObject object = null;
        object = convertToJsonObject(inputStream).getJsonObject(entity);
        return object;
    }

    @Override
    public JsonArray convertToJsonArray(JsonObject jsonObject, String entity) {
        return jsonObject.getJsonArray(entity);
    }

    @Override
    public JsonObjectBuilder createBaseBuilder() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(BaseProperty.CONTEXT_LABEL.getValue(), BaseProperty.CONTEXT_VALUE.getValue());
        return builder;
    }

    @Override
    public JsonObject convertEventToJsonLD(JsonObject event_object) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(BaseProperty.TYPE_LABEL.getValue(), "Event");
        if (null == event_object.getString("id") || "".equalsIgnoreCase(event_object.getString("id"))) {
            builder.add("id", "null");
        } else {
            builder.add("id", event_object.getString("id"));
        }

        if (null == event_object.getString("title") || "".equalsIgnoreCase(event_object.getString("title"))) {
            builder.add("name", "null");
        } else {
            builder.add("name", event_object.getString("title"));
        }

        if (null == event_object.getString("description") || "".equalsIgnoreCase(event_object.getString("description"))) {
            builder.add("description", "null");
        } else {
            builder.add("description", event_object.getString("description"));
        }

        if (null == event_object.getString("url") || "".equalsIgnoreCase(event_object.getString("url"))) {
            builder.add("url", "null");
        } else {
            builder.add("url", event_object.getString("url"));
        }
/*
        if (null == event_object.getString("city_name") || "".equalsIgnoreCase(event_object.getString("city_name"))) {
            builder.add("location", "null");
        } else {
            JsonObjectBuilder location_builder = Json.createObjectBuilder();
            location_builder.add(BaseProperty.TYPE_LABEL.getValue(), "Thing");
            location_builder.add("name", event_object.getString("city_name"));
            builder.add("location", location_builder.build());
        }*/

        if (null == event_object.getString("start_time") || "".equalsIgnoreCase(event_object.getString("start_time"))) {
            builder.add("startDate", "null");
        } else {
            String new_date_format = event_object.getString("start_time").replaceAll(" ", "T") + "Z";
            builder.add("startDate", new_date_format);
        }

        /*if (null == target_object.getString("venue_address") || "".equalsIgnoreCase(target_object.getString("venue_address"))) {
            builder.add("address", "null");
        } else {
            builder.add("address", target_object.getString("venue_address"));
        }*/

        return builder.build();
    }

    @Override
    public JsonObjectBuilder createSearchActionBaseBuilder() {
        JsonObjectBuilder search_action_builder = createBaseBuilder();
        search_action_builder.add(BaseProperty.TYPE_LABEL.getValue(), "SearchAction");
        search_action_builder.add(BaseProperty.ACTION_STATUS_LABEL.getValue(), BaseProperty.ACTION_STATUS_COMPLETED.getValue());
        return search_action_builder;
    }

    @Override
    public JsonObject convertEventSearchAction(JsonArray events_array) {
        JsonObjectBuilder search_action_builder = createSearchActionBaseBuilder();

        // Add list of found Events into ItemList as JsonLDArray
        JsonObjectBuilder item_list_builder = Json.createObjectBuilder();
        item_list_builder.add(BaseProperty.TYPE_LABEL.getValue(), "ItemList");


        JsonArrayBuilder new_array = Json.createArrayBuilder();
        for (int i = 0; i < events_array.size(); i++) {
            JsonObject current_object = events_array.getJsonObject(i);
            new_array.add(convertEventToJsonLD(current_object));
        }
        item_list_builder.add("itemListElement", new_array);

        // Add ItemList into SearchAction as JsonLDObject
        search_action_builder.add(BaseProperty.RESULT_LABEL.getValue(), item_list_builder.build());

        return search_action_builder.build();
    }
}
