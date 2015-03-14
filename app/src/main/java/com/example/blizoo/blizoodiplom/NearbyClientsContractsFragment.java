package com.example.blizoo.blizoodiplom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.blizoo.blizoodiplom.adapters.NearbyClientsContractsAdapter;
import com.example.blizoo.blizoodiplom.models.NearbyClientsContracts;

import java.util.ArrayList;


public class NearbyClientsContractsFragment extends Fragment  {


    // array which will hold information about every clients
    private ArrayList<NearbyClientsContracts> mClientContractsArrayList;
    private ListView mClientsListView;

    // adapter for loading all clients and show they in list view
    private NearbyClientsContractsAdapter mClientContractAdapter;

    private SwipeRefreshLayout mSwipeClientsRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {

        View view = inflater
                .inflate(R.layout.fragment_nearby_client_contracts, container, false);

        initializeLayoutElements(view, bundle);


        //((MainActivity) getActivity()).selectNavigationDrawer(1);

        return view;

    }

    /**
     * Method which initialize all layout element, also set to them onClickListeners methods
     */
    private void initializeLayoutElements(View view, Bundle bundle) {

        mClientsListView = (ListView) view.findViewById(R.id.client_contract_listview);
        mClientContractsArrayList = new ArrayList<NearbyClientsContracts>();
        NearbyClientsContracts clientObject = new NearbyClientsContracts();
        clientObject.setName("Kris");
        clientObject.setFamily("Mris");
        clientObject.setPhone("333");
        mClientContractsArrayList.add(clientObject);
        mClientContractAdapter = new NearbyClientsContractsAdapter(getActivity(), mClientContractsArrayList);
        mClientsListView.setAdapter(mClientContractAdapter);

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
}
