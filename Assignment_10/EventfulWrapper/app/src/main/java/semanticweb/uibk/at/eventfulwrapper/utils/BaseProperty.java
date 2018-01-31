package semanticweb.uibk.at.eventfulwrapper.utils;

/**
 * Created by ngapham on 30.01.18.
 */

public enum BaseProperty {
    CONTEXT_LABEL("@context"),
    CONTEXT_VALUE("http://schema.org"),
    TYPE_LABEL("@type"),
    TYPE_EVENT_VALUE("Event"),
    TYPE_USER_VALUE("User"),
    ENDPOINT_SEARCH_VALUE("Search"),
    ENDPOINT_GET_VALUE("Get"),
    ACTION_STATUS_LABEL("actionStatus"),
    ACTION_STATUS_COMPLETED("CompletedActionStatus"),
    RESULT_LABEL("result");

    private String value;

    BaseProperty(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
