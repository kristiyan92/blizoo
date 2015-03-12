package com.example.blizoo.blizoodiplom;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import static android.view.View.OnClickListener;


public class ContactUsFragment extends Fragment implements OnClickListener {

    /*
     * THIS IS COMMENT FOR TESTING COMMIT
     */

    //Layout Elements
    private Button mSendMessageButton;
    private EditText mYourName, mYourEmail, mYourMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater
                .inflate(R.layout.contact_us_fragment, container, false);

        initializeLayoutElements(view);

        // Select the navigation drawer item on create (useful when you go back
        // to this fragment on back pressed)
        // ((BaseActivity) getActivity()).selectNavigationDrawer(1);

        return view;
    }


    @Override
    public void onClick(View view) {
        Integer identifierId = view.getId();

        switch (identifierId) {
            case R.id.send_message_button:
                // First processing email validation
                String getEmail = mYourEmail.getText().toString();
                if (isEmailValid(getEmail)) {
                    if (mYourName.getText().toString().length() > 0) {
                        if (mYourMessage.getText().toString().length() > 10) {
                            Toast.makeText(getActivity(),"Success", Toast.LENGTH_LONG).show();
                          //  sendEmailRequest();
                        } else {
                            //openToast(getString(R.string.biggerQuestion));
                        }
                    } else {
                        // openToast(getString(R.string.insertName));
                    }

                }

        }


    }


    /**
     *  This method initialize all variables which we use in this layout.
     * Here also we set onCLickListeners elements which are needed to us.
     *
     * @param view
     */
    private void initializeLayoutElements(View view) {
        mYourName = (EditText) view.findViewById(R.id.your_name_et);
        mYourEmail = (EditText) view.findViewById(R.id.your_email_et);
        mYourMessage = (EditText) view.findViewById(R.id.your_message_et);
        mSendMessageButton = (Button) view.findViewById(R.id.send_message_button);

        mSendMessageButton.setOnClickListener(this);
    }


    /*public void onResume() {
        super.onResume();
        String[] res = getResources().getStringArray(R.array.menu_items);
        getActivity().getActionBar().setTitle(res[2]);
    };*/




    /**
     * Function which check is entered email`s are write correctly Checking is
     * perform with help of android email address Patterns
     *
     * @param email
     * @return
     */
    private static boolean isEmailValid(String email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }


}
