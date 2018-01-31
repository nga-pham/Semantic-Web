package semanticweb.uibk.at.eventfulwrapper.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import semanticweb.uibk.at.eventfulwrapper.utils.HttpMethod;

/**
 * Created by ngapham on 26.01.18.
 */

public class HttpClientImpl  implements HttpClient {

    private static final String POST_PARAMS = "userName=Pankaj";

    public HttpClientImpl() {
    }

    @Override
    public InputStream get(String url) throws IOException {
        InputStream iStream = null;
        URL requested_url = new URL(url);
        HttpURLConnection con = (HttpURLConnection) requested_url.openConnection();
        con.setRequestMethod(HttpMethod.GET.getValue());
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { //Success connection
            iStream = con.getInputStream();

        } else {
            throw new RuntimeException("Unsuccessful connection via GET method");
        }
//        con.disconnect();

        return iStream;
    }

    @Override
    public InputStream post(String url) throws IOException {
        URL requested_url = new URL(url);
        HttpURLConnection con = (HttpURLConnection) requested_url.openConnection();
        con.setRequestMethod(HttpMethod.POST.getValue());

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        InputStream iStream = null;
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            iStream = con.getInputStream();
        } else {
            throw new RuntimeException("Unsuccessful connection via POST method");
        }
        return iStream;
    }

}
