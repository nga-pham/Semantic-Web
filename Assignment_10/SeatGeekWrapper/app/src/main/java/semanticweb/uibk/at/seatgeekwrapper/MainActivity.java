package semanticweb.uibk.at.seatgeekwrapper;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class MainActivity extends Activity {

    // Input information
    String url = "https://api.seatgeek.com/2/events/801255?client_id=MTAzNzEzNjJ8MTUxNjgzMjY2MC40NQ";

    // Output information
    JSONObject jsonObj;

    // Widgets
    Button btnSearch;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getView();
        onClickButton();
    }

    ////////////////////check internet connection
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void onClickButton() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent browserIntent =
                        new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);*/
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Parsing json object response
                        // response will be a json object
                        try {
                            jsonObj = (JSONObject) response.getJSONArray("performers").get(0);
                            String slug = jsonObj.getString("slug");
                            txtResult.setText(slug);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            txtResult.setText("Error");
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtResult.setText("ERROR");
                    }
                });

                // Adding request to request queue
                AppController.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
            }
        });
    }

    private void getView() {
        btnSearch = (Button) findViewById(R.id.btnSearch);
        txtResult = (TextView) findViewById(R.id.txtResult);
    }
}
