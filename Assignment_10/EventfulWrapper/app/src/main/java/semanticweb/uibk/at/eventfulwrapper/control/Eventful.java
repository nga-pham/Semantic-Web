package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

/**
 * Created by ngapham on 27.01.18.
 */

public interface Eventful {
    String searchEventRequest(HashMap<String, String> list_criteria);

    String searchEventResult(HashMap<String, String> list_criteria) throws IOException;

    String findById(String id, String entity, String end_point) throws IOException;

    String formalizeKeyword(String keyword);

    String createSearchQuery(HashMap<String, String> list_criteria);

    String createSearchUrl(HashMap<String, String> list_criteria, String entity, String end_point);
}
