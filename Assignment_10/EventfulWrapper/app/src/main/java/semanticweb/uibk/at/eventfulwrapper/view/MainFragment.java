package semanticweb.uibk.at.eventfulwrapper.view;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import semanticweb.uibk.at.eventfulwrapper.R;
import semanticweb.uibk.at.eventfulwrapper.model.EntryPoint;
import semanticweb.uibk.at.eventfulwrapper.model.SearchAction;

/**
 * Created by ngapham on 26.01.18.
 */

public class MainFragment extends Fragment {

    // Input information
    private static final String URL = "http://api.eventful.com/json/events/search?";
    private static final String APP_KEY = "KKcxSwGD2J4Vn9LH";

    // Output information
    JSONObject jsonObj;
    String annotated_Json_string;

    // Widgets
    Button btnAnnotate;
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
        btnAnnotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Annotate the Search functionality
                String keyword = edtKeyword.getText().toString();
                String location = edtLocation.getText().toString();
                String url = URL + "app_key=" + APP_KEY +
                        "&keywords=" + keyword +
                        "&location=" + location.replaceAll(" ", "+");
                // Create new SearchAction
                EntryPoint searchEntryPoint = new EntryPoint();
                searchEntryPoint.setUrlTemplate(url);
                SearchAction searchAction = new SearchAction();
                searchAction.setTarget(searchEntryPoint);
                searchAction.setKeyword(keyword);
                searchAction.setLocation(location.toUpperCase());
                // Print it
                annotated_Json_string = searchAction.toString();
                txtTest.setText(annotated_Json_string);

                /*String url = URL + "app_key=" + APP_KEY +
                                "&keywords=" + edtKeyword.getText().toString() +
                                "&location=" + edtLocation.getText().toString();
//                Toast.makeText(getActivity().getApplicationContext(), url, Toast.LENGTH_LONG).show();

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Parsing json object response
                        // response will be a json object


                        try {
                            jsonObj = (JSONObject) response.getJSONObject("events");
                            annotated_Json_string = jsonObj.getString("event");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            annotated_Json_string = "ERROR";
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        annotated_Json_string = "ERROR";
                    }
                });

                // Adding request to request queue
                AppController.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjReq);
*/
                // Transfer data to new fragment with bundle
//                Bundle args = new Bundle();
//                String TAG = "result";
//                args.putString(TAG, annotated_Json_string);
//                String test = args.getString(TAG);
//                txtTest.setText(test);

//                RecognizeFragment recogFrament = new RecognizeFragment();
//                recogFrament.setArguments(args);
//
//                FragmentTransaction fTransaction = getActivity().getFragmentManager().beginTransaction();
//                fTransaction.addToBackStack(null);
//                fTransaction.replace(R.id.main_container, recogFrament);
//                fTransaction.commit();


            }
        });
    }

    private void initControls(View v) {
        btnAnnotate = (Button) v.findViewById(R.id.btnAnnotate);
        edtKeyword = (EditText) v.findViewById(R.id.edtKeyword);
        edtLocation = (EditText) v.findViewById(R.id.edtLocation);
        txtTest = (TextView) v.findViewById(R.id.txtAnnotatedText);

        annotated_Json_string = new String();
    }
}
