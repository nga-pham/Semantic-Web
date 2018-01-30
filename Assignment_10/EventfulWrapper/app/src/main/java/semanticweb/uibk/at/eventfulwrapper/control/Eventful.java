package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

/**
 * Created by ngapham on 27.01.18.
 */

public interface Eventful {

    String searchEvent(String keyword, String location) throws IOException;

    String formalizeURL(String name);

    String testURL(String keyword, String location);
}
