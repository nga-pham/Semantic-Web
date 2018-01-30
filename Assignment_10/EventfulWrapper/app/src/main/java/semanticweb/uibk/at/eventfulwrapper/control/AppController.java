package semanticweb.uibk.at.eventfulwrapper.control;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ngapham on 24.01.18.
 * Singleton design:
 */

public class AppController {

    private static AppController mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;

    private AppController(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    // if an instance is already create, it will return it.
    // if no instance was created, it will create a new one then return it.
    public static synchronized AppController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppController(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public  void addToRequestQueue(final Request request) {
        getRequestQueue().add(request);
    }

    public  void addToRequestQueueWithTag(final Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    /*public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }*/

}

