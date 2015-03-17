package com.example.blizoo.blizoodiplom;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.blizoo.blizoodiplom.adapters.NearbyClientsContractsAdapter;
import com.example.blizoo.blizoodiplom.models.NearbyClientsContracts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class NearbyClientsContractsFragment extends Fragment {


    // array which will hold information about every clients
    private ArrayList<NearbyClientsContracts> mClientContractsArrayList;
    private ListView mClientsListView;

    // adapter for loading all clients and show they in list view
    private NearbyClientsContractsAdapter mClientContractAdapter;
    private ProgressBar mPbClientsContract;
    private SwipeRefreshLayout mSwipeClientsRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {

        View view = inflater
                .inflate(R.layout.fragment_nearby_client_contracts, container, false);

        initializeLayoutElements(view, bundle);

        getClientsInHubRange();
        //((MainActivity) getActivity()).selectNavigationDrawer(1);

        return view;

    }

    /**
     * Method which initialize all layout element, also set to them onClickListeners methods
     */
    private void initializeLayoutElements(View view, Bundle bundle) {

        mClientsListView = (ListView) view.findViewById(R.id.client_contract_listview);
        mPbClientsContract = (ProgressBar) view.findViewById(R.id.client_contract_progressbar);
        mClientContractsArrayList = new ArrayList<NearbyClientsContracts>();
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

        //initialize swipe refresh layout,set to him color scheme (which colors to use when refresh
        //button appear to screen).After this set to layout listener.At body of this listener we set
        //initialize values to sendPostRequest and paginationStartNumber, because when user refresh
        //the page and values are not reset adapter may not refresh and in list view will show old
        // values and the new one and make duplicate values in the list.
        mSwipeClientsRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.client_swipe_refresh_layout);
        mSwipeClientsRefreshLayout.setColorSchemeResources(R.color.blue, R.color.green_text);
        mSwipeClientsRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

             /*   mSendPostRequest = true;
                mPaginationStartPage = 1;
                getLecturersRequest();*/
            }
        });


    }

    /**
     * This method perform API call to get all client which are in hub range
     */
    public void getClientsInHubRange() {

        //show the progress bar
        mPbClientsContract.setVisibility(View.VISIBLE);

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        final String longetute = "43.21624";
        final String latetute = "27.921407";
        final String radius = "2000";
        String url = "http://192.168.2.28" + "clientsInHubRange.php";
        Log.d("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    mPbClientsContract.setVisibility(View.GONE);

                    Log.e("RESPONSE", response);
                    JSONArray data = new JSONArray(response);



                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        NearbyClientsContracts client = new NearbyClientsContracts();

                        client.setName(object.getString("name"));
                        client.setFamily(object.getString("lat"));
                        client.setPhone(object.getString("lng"));
                        client.setClientId(object.getString("id"));
                        mClientContractsArrayList.add(client);
                        Log.d("NAME", mClientContractsArrayList.toString());

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
    }
}
