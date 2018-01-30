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

    /**
     * Requests url with HTTP GET and returns result object as JSONArray
     * @param url
     * @return list of JSONObject as JSONArray
     */
    JsonArray getList(String url, String params) throws IOException;

    /**
     * Requests url with HTTP POST and retrieves result object as JSONObject
     * @param url
     * @param params
     * @param object Requested object which can be sent as JSONObject
     * @return retrieved result (object that has been modified, normally)
     */
    Map post(String url, Map<String, String> params, Map object);
}
