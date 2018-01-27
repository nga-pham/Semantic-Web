package semanticweb.uibk.at.eventfulapi.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import semanticweb.uibk.at.eventfulAPIsample.R;

/**
 * Created by ngapham on 26.01.18.
 */

public class RecognizeFragment extends Fragment {

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
        txtResult.setText(getData.getString("result"));

    }

    private void initControls(View v) {
        txtResult = v.findViewById(R.id.txtResult);
    }
}
