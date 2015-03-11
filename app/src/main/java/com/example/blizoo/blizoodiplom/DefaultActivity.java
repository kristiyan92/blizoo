package com.example.blizoo.blizoodiplom;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by ltd158 on 1/27/15.
 */
public class DefaultActivity extends ActionBarActivity {

    //Default debugging tag
    private static final String TAG = "Default activity";

    //Application session manager
  //  private SessionManager mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setApplicationLanguage();

    }

    @Override
    protected void onResume() {
        super.onResume();

        setApplicationLanguage();

    }

    /**
     * This function set the last choosen language by the user
     * If the language is not set the default language is "bg"
     */
    public void setApplicationLanguage() {

      /*  mSession = new SessionManager(this);

        if (mSession.getApplicationLanguage().equals("unset")) {

            String currentLanguage = Locale.getDefault().getLanguage();

            Log.d(TAG, "Language is: " + currentLanguage);

            if (currentLanguage.equals("bg")) {
                mSession.setApplicationLanguage("bg");
            } else {
                mSession.setApplicationLanguage("en");
            }

        }*/

    /*    Log.d(TAG, "Language now is: " + mSession.getApplicationLanguage());

        Locale loc = new Locale(mSession.getApplicationLanguage());
        Locale.setDefault(loc);
        Configuration config = new Configuration();
        config.locale = loc;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());*/

    }

    // This function check the response code from the API and return true if the
    // code is successful
    public boolean successResponse(JSONObject response) {
        boolean check = false;
        int responseCode = 500;
        String message = "";

        try {
            responseCode = response.getInt("HTTP_CODE");
            //message = response.getString("message");

            switch (responseCode) {
                case 200:
                    check = true;
                    break;
                case 401:
                    Toast.makeText(this, getResources().getString(R.string.invalid_request_params), Toast.LENGTH_LONG).show();
                case 402:
                case 403:
                case 404:
                case 405:
                case 406:
                case 407:
                case 408:
                case 410:

                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                    check = false;
                    break;
                case 500:



                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

}
