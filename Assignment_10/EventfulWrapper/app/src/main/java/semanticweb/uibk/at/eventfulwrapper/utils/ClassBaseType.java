package semanticweb.uibk.at.eventfulwrapper.utils;

/**
 * Created by ngapham on 30.01.18.
 */

public enum ClassBaseType {
    CONTEXT_LABEL("@context"),
    CONTEXT_VALUE("http://schema.org"),
    TYPE_LABEL("@type"),
    TYPE_EVENT_LABEL("Event");

    private String value;

    ClassBaseType(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
