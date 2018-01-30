package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import semanticweb.uibk.at.eventfulwrapper.model.Event;
import semanticweb.uibk.at.eventfulwrapper.utils.ClassBaseType;

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
    public JsonObject convertToJsonObject(InputStream inputStream, String params) {
        JsonObject object = null;
        object = convertToJsonObject(inputStream).getJsonObject(params);
        return object;
    }

    @Override
    public JsonArray convertToJsonArray(JsonObject jsonObject, String params) {
        return jsonObject.getJsonArray(params);
    }

/*    @Override
    public String convertToJson(Event event) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(ClassBaseType.CONTEXT_LABEL.getValue(), ClassBaseType.CONTEXT_VALUE.getValue())
                .add(ClassBaseType.TYPE_LABEL.getValue(), ClassBaseType.TYPE_EVENT_LABEL.getValue());

        if (null == event.getIdentifier() || "".equalsIgnoreCase(event.getIdentifier())) {
            builder.add("id", "null");
        } else {
            builder.add("id", event.getIdentifier());
        }

        if (null == event.getName() || "".equalsIgnoreCase(event.getName())) {
            builder.add("title", "null");
        } else {
            builder.add("title", event.getName());
        }

        if (null == event.getDescription() || "".equalsIgnoreCase(event.getDescription())) {
            builder.add("description", "null");
        } else {
            builder.add("description", event.getDescription());
        }

        JsonObject event_object = builder.build();

        return event_object.toString();
    }*/

    @Override
    public String convertToJsonLD(JsonObject target_object) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(ClassBaseType.CONTEXT_LABEL.getValue(), ClassBaseType.CONTEXT_VALUE.getValue())
                .add(ClassBaseType.TYPE_LABEL.getValue(), ClassBaseType.TYPE_EVENT_LABEL.getValue());

        if (null == target_object.getString("id") || "".equalsIgnoreCase(target_object.getString("id"))) {
            builder.add("id", "null");
        } else {
            builder.add("id", target_object.getString("id"));
        }

        if (null == target_object.getString("title") || "".equalsIgnoreCase(target_object.getString("title"))) {
            builder.add("title", "null");
        } else {
            builder.add("title", target_object.getString("title"));
        }

        if (null == target_object.getString("description") || "".equalsIgnoreCase(target_object.getString("description"))) {
            builder.add("description", "null");
        } else {
            builder.add("description", target_object.getString("description"));
        }

        if (null == target_object.getString("url") || "".equalsIgnoreCase(target_object.getString("url"))) {
            builder.add("url", "null");
        } else {
            builder.add("url", target_object.getString("url"));
        }

        JsonObject event_object = builder.build();

        return event_object.toString();
    }
}
