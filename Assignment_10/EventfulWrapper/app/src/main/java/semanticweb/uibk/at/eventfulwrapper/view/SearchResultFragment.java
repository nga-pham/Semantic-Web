package semanticweb.uibk.at.eventfulwrapper.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import semanticweb.uibk.at.eventfulwrapper.R;

/**
 * Created by ngapham on 27.01.18.
 */

public class SearchResultFragment extends Fragment {

    //Widgets
    TextView txtResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searchresult, container,
                false);;

        initControls(rootView);
        getListEvents();

        return rootView;
    }
    /*
    ** Get data transfered from Main Fragment
     */
    private void getListEvents() {
        Bundle getData = getArguments();
        String result = getData.getString("result");
        if (null != result) {
            txtResult.setText(result);
            Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                    .show();
        }

    }

    private void initControls(View v) {
        txtResult = v.findViewById(R.id.txtResult);
    }
}
