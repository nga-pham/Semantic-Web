package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.JsonArray;
import javax.json.JsonObject;

import semanticweb.uibk.at.eventfulwrapper.model.SearchAction;
import semanticweb.uibk.at.eventfulwrapper.model.Website;
import semanticweb.uibk.at.eventfulwrapper.utils.Authentication;
import semanticweb.uibk.at.eventfulwrapper.utils.BaseProperty;
import semanticweb.uibk.at.eventfulwrapper.utils.EndpointBaseType;


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
    public String searchEventRequest(HashMap<String, String> list_criteria) {
        // Create new SearchAction
        SearchAction searchAction = new SearchAction();
        searchAction.setQuery(list_criteria);
        // Add to Website
        List<SearchAction> list_action = new ArrayList<SearchAction>();
        list_action.add(searchAction);
        Website website = new Website();
        website.setPotentialAction(list_action);
        return website.toString();
    }

    @Override
    public String searchEventResult(HashMap<String, String> list_criteria) throws IOException {
        String result = "";
        // Search for list of Events base on criteria
        // return JsonObject
        String search_url = createSearchUrl(list_criteria, "event", "search");
        JsonObject events_object = converter.convertToJsonObject(mClient.get(search_url), "events");

        // Retrieve list of Events as JsonArray
        JsonArray events_array = converter.convertToJsonArray(events_object, "event");
        result = converter.convertEventSearchAction(events_array).toString();

        /*if (null != events_object) {
            // Retrieve list of Events as JsonArray
            JsonArray events_array = converter.convertToJsonArray(events_object, "event");

            // Convert to JSON-LD
            if (null != events_array) {
                converter.convertEventSearchAction(events_array).toString();
            }
        }*/

        return result;
    }

    @Override
    public String findById(String id, String entity, String end_point) throws IOException {
        // Search for list of Events base on criteria
        // return JsonObject
        HashMap<String, String> map_id = new HashMap<String, String>();
        map_id.put("id", id);
        String search_url = createSearchUrl(map_id, entity, end_point);
        JsonObject object = converter.convertToJsonObject(mClient.get(search_url));
        return converter.convertEventToJsonLD(object).toString();
    }

    @Override
    public String formalizeKeyword(String keyword) {
        return keyword.replaceAll(" ", "+");
    }

    @Override
    public String createSearchQuery(HashMap<String, String> list_criteria) {
        String query = Authentication.APP_KEY_LABEL.getValue() + Authentication.APP_KEY.getValue();

        Set set = list_criteria.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry current_criteria = (Map.Entry)iterator.next();
            query += "&" + formalizeKeyword(current_criteria.getKey().toString()) + "="
                    + formalizeKeyword(current_criteria.getValue().toString());
        }

        return query;
    }

    @Override
    public String createSearchUrl(HashMap<String, String> list_criteria, String entity, String end_point) {
        String endPoint = EndpointBaseType.BASE_URL.getValue();
        if (BaseProperty.TYPE_EVENT_VALUE.getValue().equalsIgnoreCase(entity)) {
            endPoint += EndpointBaseType.EVENT.getValue();
        } else if (BaseProperty.TYPE_USER_VALUE.getValue().equalsIgnoreCase(entity)) {
            endPoint += EndpointBaseType.USER.getValue();
        }
        if (BaseProperty.ENDPOINT_SEARCH_VALUE.getValue().equalsIgnoreCase(end_point)) {
            endPoint += EndpointBaseType.SEARCH.getValue();
        } else if (BaseProperty.ENDPOINT_GET_VALUE.getValue().equalsIgnoreCase(end_point)) {
            endPoint += EndpointBaseType.GET.getValue();
        }
        String url = endPoint + createSearchQuery(list_criteria);
        return url;
    }
}
