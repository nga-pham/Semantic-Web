package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * Created by ngapham on 24.01.18.
 * Basic interface for HTTP client.
 */

public interface HttpClient {
    /**
     * Requests url with HTTP GET and returns result object as JSONObject
     * @param url requested url
     * @return retrieved result
     */
    InputStream get(String url) throws IOException;

    InputStream post(String url) throws IOException;
}
