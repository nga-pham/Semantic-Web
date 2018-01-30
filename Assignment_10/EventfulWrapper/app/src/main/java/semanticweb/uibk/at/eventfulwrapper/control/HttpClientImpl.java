package semanticweb.uibk.at.eventfulwrapper.control;

import android.content.Context;
import android.net.ConnectivityManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

import semanticweb.uibk.at.eventfulwrapper.utils.EndpointBaseType;
import semanticweb.uibk.at.eventfulwrapper.utils.HttpMethod;

/**
 * Created by ngapham on 26.01.18.
 */

public class HttpClientImpl  implements HttpClient {

//    private CloseableHttpClient httpClient;
//    private ObjectMapper mapper;

    public HttpClientImpl() {
//        this.httpClient = HttpClientBuilder.create().build();
//        this.mapper = new ObjectMapper();
    }

    /**
     * Check internet connection
     */

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public InputStream get(String url) throws IOException {
        InputStream iStream = null;
        URL requested_url = new URL(url);
        HttpURLConnection con = (HttpURLConnection) requested_url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { //Success connection
            iStream = con.getInputStream();

        } else {
        }
        return iStream;
    }

    @Override
    public JsonArray getList(String url, String params) throws IOException {
//        HttpGet httpGet = new HttpGet(url);
//        return getEntityAndReleaseConnection(httpGet, List.class);
        JsonArray array = null;
        URL requested_url = new URL(url);
        HttpURLConnection con = (HttpURLConnection) requested_url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { //Success connection
            JsonReader reader = Json.createReader(new InputStreamReader(con.getInputStream()));
            JsonObject object1 = reader.readObject();
//            array = object1.getJsonArray(params);
        } else {
        }
        return array;
    }

    @Override
    public Map post(String url, Map<String, String> params, Map object) {
        return null;
    }
/*
    private <T> T getEntityAndReleaseConnection(HttpRequestBase httpRequest, Class<T> objectClass) {
        try {
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity == null) {
                throw new RuntimeException("Error retrieving results from http request");
            }

            Object result = mapper.readValue(httpEntity.getContent(), Object.class);
            if (objectClass.isInstance(result)) {
                return (T)result;
            }
            throw new RuntimeException("Can't parse retrieved object: " + result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpRequest.releaseConnection();
        }
    }*/
}
