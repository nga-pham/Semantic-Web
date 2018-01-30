package semanticweb.uibk.at.eventfulwrapper.view;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import com.evdb.javaapi.data.SearchResult;
import com.evdb.javaapi.data.request.EventSearchRequest;
import com.evdb.javaapi.operations.EventOperations;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import semanticweb.uibk.at.eventfulwrapper.R;
import semanticweb.uibk.at.eventfulwrapper.control.Eventful;
import semanticweb.uibk.at.eventfulwrapper.control.EventfulImpl;
import semanticweb.uibk.at.eventfulwrapper.control.HttpClient;
import semanticweb.uibk.at.eventfulwrapper.control.HttpClientImpl;
import semanticweb.uibk.at.eventfulwrapper.model.EntryPoint;
import semanticweb.uibk.at.eventfulwrapper.model.SearchAction;
import semanticweb.uibk.at.eventfulwrapper.utils.Authentication;
import semanticweb.uibk.at.eventfulwrapper.utils.EndpointBaseType;
import semanticweb.uibk.at.eventfulwrapper.utils.OAuthConfig;

/**
 * Created by ngapham on 27.01.18.
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
    Button btnSearch;
    EditText edtKeyword, edtLocation, edtCategory, edtLanguage;
    TextView txtTest;

    HttpClient client = new HttpClientImpl();


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
                // Annotate it
                annotated_Json_string = searchAction.toString();


//                txtTest.setText(annotated_Json_string);

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

//                SearchResultFragment recogFrament = new SearchResultFragment();
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
        btnAnnotate = (Button) v.findViewById(R.id.btnAnotate);
        btnSearch = (Button) v.findViewById(R.id.btnSearch);

        edtKeyword = (EditText) v.findViewById(R.id.edtKeyword);
        edtLocation = (EditText) v.findViewById(R.id.edtLocation);
        edtCategory = (EditText) v.findViewById(R.id.edtCategory);
        edtLanguage = (EditText) v.findViewById(R.id.edtLanguage);

        txtTest = (TextView) v.findViewById(R.id.edtAnotateText);

        annotated_Json_string = new String();
    }
}
