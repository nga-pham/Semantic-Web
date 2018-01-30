package semanticweb.uibk.at.eventfulwrapper.utils;

/**
 * Created by ngapham on 24.01.18.
 */

public enum EndpointBaseType {
    BASE_URL("http://api.eventful.com/json/"),
    EVENT("events/"),
    SEARCH("search?");

    private String value;

    EndpointBaseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
