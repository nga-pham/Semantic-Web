package semanticweb.uibk.at.eventfulwrapper.model;

/**
 * Created by ngapham on 26.01.18.
 */

public class EntryPoint extends Thing {

    // Properties
    String httpMethod;
    String urlTemplate;

    // Getter and Setter
    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }
}
