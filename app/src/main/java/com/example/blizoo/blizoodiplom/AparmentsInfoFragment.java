package com.example.blizoo.blizoodiplom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.blizoo.blizoodiplom.adapters.ApartmentsInfoAdapter;
import com.example.blizoo.blizoodiplom.models.ApartmentsInfo;
import com.example.blizoo.blizoodiplom.models.NearbyClientsContracts;
import java.util.ArrayList;

public class AparmentsInfoFragment extends Fragment {


    // array which will hold information about every clients
    private ArrayList<ApartmentsInfo> mApartmentsArrayList;
    private GridView mApartmentsGridView;

    // adapter for loading all clients and show they in list view
    private ApartmentsInfoAdapter mArpartmentsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {

        View view = inflater
                .inflate(R.layout.fragment_aparments_info, container, false);

        initializeLayoutElements(view, bundle);

       //getClientsInHubRange();
        //((MainActivity) getActivity()).selectNavigationDrawer(1);

        return view;

    }

    /**
     * Method which initialize all layout element, also set to them onClickListeners methods
     */
    private void initializeLayoutElements(View view, Bundle bundle) {

        mApartmentsGridView = (GridView) view.findViewById(R.id.apartment_info_gridview);
        mApartmentsArrayList = new ArrayList<ApartmentsInfo>();

        for (int i = 0; i < 18; i++) {
            ApartmentsInfo apartment = new ApartmentsInfo();

            apartment.setNumber(Integer.toString(18-i));
            mApartmentsArrayList.add(apartment);
            Log.d("NAME",mApartmentsArrayList.toString());

        }

        mArpartmentsAdapter = new ApartmentsInfoAdapter(getActivity(), mApartmentsArrayList);
        mApartmentsGridView.setNumColumns(3);
        mApartmentsGridView.setAdapter(mArpartmentsAdapter);
    /*    NearbyClientsContracts clientObject = new NearbyClientsContracts();
        clientObject.setName("Kris");
      //  clientObject.setFamily("Mris");
       // clientObject.setPhone("333");
        mClientContractsArrayList.add(clientObject);
        mClientContractAdapter = new NearbyClientsContractsAdapter(getActivity(), mClientContractsArrayList);
        mClientsListView.setAdapter(mClientContractAdapter);*/

        // mProgressBar = (ProgressBar) view.findViewById(R.id.lectors_progressbar);
        // mEmptyView = (TextView) view.findViewById(android.R.id.empty);
        // mReloadLectorersButton = (Button) view.findViewById(R.id.reload_lecturers_button);



    }

    /**
     * This method perform API call to get all client which are in hub range
     */
  /*  public void getClientsInHubRange() {

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        final String longetute = "43.21624";
        final String latetute = "27.921407";
        final String radius = "2000";
        String url = "http://192.168.0.101/" + "clientsInHubRange.php";
        Log.d("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    Log.e("RESPONSE", response);
                    JSONArray data = new JSONArray(response);

                    // ArrayList<HashMap<String, String>> agentsList =
                    // new ArrayList<HashMap<String, String>>();

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        NearbyClientsContracts client = new NearbyClientsContracts();
                                *//*HashMap<String, String> map = new HashMap<String, String>();
								Iterator<?> iter = agent.keys();

								while (iter.hasNext()) {
									String key = (String) iter.next();
									String value = agent.getString(key);
									map.put(key, value);
								} *//*
                        client.setName(object.getString("name"));
                        client.setFamily(object.getString("lat"));
                        client.setPhone(object.getString("lng"));
                        mClientContractsArrayList.add(client);
                        Log.d("NAME",mClientContractsArrayList.toString());

                    }

                    mClientContractAdapter = new NearbyClientsContractsAdapter(getActivity(), mClientContractsArrayList);
                    mClientsListView.setAdapter(mClientContractAdapter);


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
                params.put("lat", latetute);
                params.put("lng", longetute);
                params.put("radius", radius);

                return params;

            }

        };
        rq.add(stringRequest);
    }*/
}
