package semanticweb.uibk.at.eventfulwrapper.utils;

/**
 * Created by ngapham on 24.01.18.
 * Enum with basic authentication types.
 */

public enum Authentication {
    APP_KEY_LABEL("app_key="),
    APP_KEY("KKcxSwGD2J4Vn9LH");

    private String value;

    Authentication(String app_key) {
        this.value = app_key;
    }

    public String getValue() {
        return value;
    }
}
