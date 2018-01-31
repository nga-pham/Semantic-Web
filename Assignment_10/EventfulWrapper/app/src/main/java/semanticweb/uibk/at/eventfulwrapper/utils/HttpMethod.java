package semanticweb.uibk.at.eventfulwrapper.utils;

/**
 * Created by ngapham on 24.01.18.
 * Enum with possible HTTP Methods of a Request.
 */

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    DELETE("DELETE");

    private String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
