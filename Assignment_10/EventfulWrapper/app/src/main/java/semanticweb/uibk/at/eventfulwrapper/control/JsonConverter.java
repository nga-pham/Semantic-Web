package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import semanticweb.uibk.at.eventfulwrapper.model.Event;

/**
 * Created by ngapham on 29.01.18.
 */

public interface JsonConverter {
    JsonObject convertToJsonObject(InputStream inputStream);

    JsonObject convertToJsonObject(InputStream inputStream, String params);

    JsonArray convertToJsonArray(JsonObject jsonObject, String params);

//    String convertToJson(Event event);

    String convertToJsonLD (JsonObject target_object);
}

