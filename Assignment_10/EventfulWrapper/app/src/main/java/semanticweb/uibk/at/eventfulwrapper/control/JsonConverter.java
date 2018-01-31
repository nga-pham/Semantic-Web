package semanticweb.uibk.at.eventfulwrapper.control;

import org.json.JSONArray;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import semanticweb.uibk.at.eventfulwrapper.model.Event;

/**
 * Created by ngapham on 29.01.18.
 */

public interface JsonConverter {
    JsonObject convertToJsonObject(InputStream inputStream);

    JsonObject convertToJsonObject(InputStream inputStream, String entity);

    JsonArray convertToJsonArray(JsonObject jsonObject, String entity);

    JsonObjectBuilder createBaseBuilder();

    JsonObject convertEventToJsonLD(JsonObject event_object);

    JsonObjectBuilder createSearchActionBaseBuilder();

    JsonObject convertEventSearchAction(JsonArray events_array);

}

