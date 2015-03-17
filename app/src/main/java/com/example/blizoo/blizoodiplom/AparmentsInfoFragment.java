package com.example.blizoo.blizoodiplom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.blizoo.blizoodiplom.adapters.ApartmentsInfoAdapter;
import com.example.blizoo.blizoodiplom.adapters.NearbyClientsContractsAdapter;
import com.example.blizoo.blizoodiplom.models.ApartmentsInfo;
import com.example.blizoo.blizoodiplom.models.NearbyClientsContracts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AparmentsInfoFragment extends Fragment {


    // array which will hold information about every clients
    private ArrayList<ApartmentsInfo> mApartmentsArrayList;
    private GridView mApartmentsGridView;
    private String stringId;
    // adapter for loading all clients and show they in list view
    private ApartmentsInfoAdapter mArpartmentsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {

        View view = inflater
                .inflate(R.layout.fragment_aparments_info, container, false);

        initializeLayoutElements(view, bundle);

        getClientsInHubRange();
        //((MainActivity) getActivity()).selectNavigationDrawer(1);

        return view;

    }

    /**
     * Method which initialize all layout element, also set to them onClickListeners methods
     */
    private void initializeLayoutElements(View view, Bundle bundle) {

        mApartmentsGridView = (GridView) view.findViewById(R.id.apartment_info_gridview);
        mApartmentsArrayList = new ArrayList<ApartmentsInfo>();

    }

    /**
     * This method perform API call to get all client which are in hub range
     */
    public void getClientsInHubRange() {

        Bundle args = getArguments();
        stringId = args.getString("id");

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = "http://192.168.2.28/" + "apartamentsView.php";
        Log.d("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    Log.e("RESPONSE", response);
                    JSONArray data = new JSONArray(response);

                    JSONObject object = data.getJSONObject(0);

                    String apartmentCount = object.getString("apartaments_count");
                    int convertCount = Integer.parseInt(apartmentCount);

                    for (int i = 0; i < convertCount; i++) {

                        ApartmentsInfo apartment = new ApartmentsInfo();
                        apartment.setNumber(Integer.toString(convertCount - i));
                        apartment.setApartmentOfSelectedClient(object.getString("apartament_number"));
                        mApartmentsArrayList.add(apartment);
                        Log.d("NAME", mApartmentsArrayList.toString());


                        mArpartmentsAdapter = new ApartmentsInfoAdapter(getActivity(), mApartmentsArrayList);
                        mApartmentsGridView.setNumColumns(3);
                        mApartmentsGridView.setAdapter(mArpartmentsAdapter);

                    }


                } catch (JSONException e) {
                    Log.d("JSON Exception", e.toString());
                    Toast.makeText(getActivity(),
                            "Грешка при зареждане на данните!",
                            Toast.LENGTH_LONG).show();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("ERROR", "Error [" + error + "]");
                Toast.makeText(getActivity(),
                        "Няма връзка със сървара!", Toast.LENGTH_LONG)
                        .show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", stringId);


                return params;

            }

        };
        rq.add(stringRequest);
    }
}
