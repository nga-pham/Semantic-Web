package semanticweb.uibk.at.eventfulwrapper.view;

import java.io.IOException;
import java.util.HashMap;

import javax.json.JsonObject;

import semanticweb.uibk.at.eventfulwrapper.control.Eventful;
import semanticweb.uibk.at.eventfulwrapper.control.EventfulImpl;
import semanticweb.uibk.at.eventfulwrapper.control.HttpClientImpl;
import semanticweb.uibk.at.eventfulwrapper.control.JsonConverterImpl;

/**
 * Created by ngapham on 30.01.18.
 */

public class Test {

    public static void main(String[] args) {
        HashMap<String, String> map_criteria = new HashMap<String, String>();
        map_criteria.put("keywords", "books");
        map_criteria.put("location", "san diego");

        // Search for Events

        Eventful eventful = new EventfulImpl();
        String result = "";
//        String search_url = eventful.createSearchUrl(map_criteria, "event");

        System.out.println("-----URL-----");
        System.out.println(eventful.createSearchUrl(map_criteria, "event", "search"));

        System.out.println("-----ANNOTATED SEARCH (EVENT) REQUEST-----");

        System.out.println(eventful.searchEventRequest(map_criteria));

        System.out.println("-----ANNOTATED SEARCH (EVENT) RESULT-----");

       try {
           result = eventful.searchEventResult(map_criteria);

        } catch (IOException e) {
            System.out.println("IOException");
        }
        System.out.println(result);

        // Find Event by id
        map_criteria.clear();
       map_criteria.put("id", "E0-001-000278174-6");
        System.out.println("-----URL-----");
        System.out.println(eventful.createSearchUrl(map_criteria, "event", "get"));

        System.out.println("-----ANNOTATED 'GET EVENT BY ID' RESULT-----");
        try {
            System.out.println(eventful.findById("E0-001-000278174-6", "event", "get"));
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
