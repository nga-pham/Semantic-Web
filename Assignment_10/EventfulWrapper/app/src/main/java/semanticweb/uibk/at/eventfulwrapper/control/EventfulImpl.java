package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.IOException;

import javax.json.JsonArray;
import javax.json.JsonObject;

import semanticweb.uibk.at.eventfulwrapper.utils.Authentication;
import semanticweb.uibk.at.eventfulwrapper.utils.EndpointBaseType;
import semanticweb.uibk.at.eventfulwrapper.model.Event;


/**
 * Created by ngapham on 27.01.18.
 */

public class EventfulImpl implements Eventful {
    HttpClient mClient;
    JsonConverter converter;

    public EventfulImpl() {
        mClient = new HttpClientImpl();
        converter = new JsonConverterImpl();
    }

    @Override
    public String searchEvent(String keyword, String location) throws IOException {
        // Search for list of Events base on criteria
        // return JsonObject
        String search_url = testURL(keyword, location);
        JsonObject events_object = converter.convertToJsonObject(mClient.get(search_url), "events");

        // Retrieve list of Events as JsonArray
        JsonArray events_array = converter.convertToJsonArray(events_object, "event");

        // Convert to 
        String result = "";

        for (int i = 0; i < events_array.size(); i++) {
            JsonObject current_object = events_array.getJsonObject(i);

            result = converter.convertToJsonLD(current_object);
        }
        return result;
    }

    @Override
    public String formalizeURL(String name) {
        return name.replaceAll(" ", "+");
    }

    @Override
    public String testURL(String keyword, String location) {
        String search_url = EndpointBaseType.BASE_URL.getValue()
                + EndpointBaseType.EVENT.getValue() + EndpointBaseType.SEARCH.getValue();
        String url = search_url + Authentication.APP_KEY_LABEL.getValue() + Authentication.APP_KEY.getValue()
                + "&keywords=" + formalizeURL(keyword)
                + "&location=" + formalizeURL(location);
        return url;
    }
}
