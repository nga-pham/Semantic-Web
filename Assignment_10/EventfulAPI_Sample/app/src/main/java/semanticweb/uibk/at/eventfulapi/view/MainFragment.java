package semanticweb.uibk.at.eventfulapi.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import semanticweb.uibk.at.eventfulapi.R;
import semanticweb.uibk.at.eventfulapi.control.AppController;

/**
 * Created by ngapham on 26.01.18.
 */

public class MainFragment extends Fragment {

    // Input information
    private static final String URL = "http://api.eventful.com/json/events/search?";
    private static final String APP_KEY = "KKcxSwGD2J4Vn9LH";

    // Output information
    JSONObject jsonObj;
    String result_event;

    // Widgets
    Button btnSearch;
    EditText edtKeyword, edtLocation;
    TextView txtTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container,
                false);

        initControls(rootView);
        onClickButton();

        return rootView;
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
                String url = URL + "app_key=" + APP_KEY +
                                "&keywords=" + edtKeyword.getText().toString() +
                                "&location=" + edtLocation.getText().toString();
                Toast.makeText(getActivity().getApplicationContext(), url, Toast.LENGTH_LONG).show();

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Parsing json object response
                        // response will be a json object

                        try {
                            jsonObj = (JSONObject) response.getJSONObject("events");
                            result_event = jsonObj.getString("event");

//                            txtTest.setText(result_event);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            result_event = "ERROR";
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result_event = "ERROR";
                    }
                });

                // Adding request to request queue
                AppController.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjReq);

                txtTest.setText(result_event);

                // Transfer data to new fragment with bundle
                Bundle args = new Bundle();
                args.putString("result", result_event);

                RecognizeFragment recogFrament = new RecognizeFragment();
                recogFrament.setArguments(args);

                FragmentTransaction fTransaction = getActivity().getFragmentManager().beginTransaction();
                fTransaction.addToBackStack(null);
                fTransaction.replace(R.id.main_container, recogFrament);
                fTransaction.commit();
            }
        });
    }

    private void initControls(View v) {
        btnSearch = (Button) v.findViewById(R.id.btnSearch);
        edtKeyword = (EditText) v.findViewById(R.id.edtKeyword);
        edtLocation = (EditText) v.findViewById(R.id.edtLocation);
        txtTest = (TextView) v.findViewById(R.id.txtTest);

        result_event = new String();
    }
}
