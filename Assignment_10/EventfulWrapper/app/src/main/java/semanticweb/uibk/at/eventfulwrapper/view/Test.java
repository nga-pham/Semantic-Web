package semanticweb.uibk.at.eventfulwrapper.view;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;

import semanticweb.uibk.at.eventfulwrapper.control.Eventful;
import semanticweb.uibk.at.eventfulwrapper.control.EventfulImpl;
import semanticweb.uibk.at.eventfulwrapper.control.HttpClient;
import semanticweb.uibk.at.eventfulwrapper.control.HttpClientImpl;
import semanticweb.uibk.at.eventfulwrapper.control.JsonConverter;
import semanticweb.uibk.at.eventfulwrapper.control.JsonConverterImpl;

/**
 * Created by ngapham on 30.01.18.
 */

public class Test {

    // Input information
    private static final String URL = "http://api.eventful.com/json/events/search?";
    private static final String APP_KEY = "KKcxSwGD2J4Vn9LH";

    public static void main(String[] args) {
        String keyword = "books";
        String location = "San Diego";

        Eventful eventful = new EventfulImpl();
        String result = "";
//        result = eventful.testURL(keyword, location);
       try {
           result = eventful.searchEvent(keyword, location);

        } catch (IOException e) {
            System.out.println("error");
        }

        System.out.println(result);
    }
}
