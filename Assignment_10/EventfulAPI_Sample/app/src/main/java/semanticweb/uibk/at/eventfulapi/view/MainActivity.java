package semanticweb.uibk.at.eventfulapi.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import semanticweb.uibk.at.eventfulapi.R;

public class MainActivity extends Activity {

    // Manage all the main Fragments
    protected FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getView();
//        onClickButton();

        // call the main fragment
        hostFragmentInContainer(savedInstanceState, R.id.main_container,
                new MainFragment());
    }

    private void hostFragmentInContainer(Bundle savedInstanceState,
                                         int containerId, Fragment newFragment) {
        if (savedInstanceState == null) {
            this.mFragmentManager = getFragmentManager();
            this.mFragmentManager.beginTransaction()
                    .add(R.id.main_container, newFragment).commit();
        }
    }


}
