package com.example.blizoo.blizoodiplom;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class CoveringFragment extends Fragment {

    // Boolean key used to prevent always to send data to the server
    private Boolean mSendPostRequest = true;

    // Layout elements
    private TextView mDestinationAddress, mGpsLocation;
    private Button mDrawMyRoute;
    private RelativeLayout mRelativeLayout;

    //Create google maps
    private GoogleMap googleMap;
    //Create google maps view, and with him we can show google maps on the screen
    private MapView mMapView;
    //variable which will hold congress destination coordination
    private LatLng mCongressLocation = new LatLng(22.231,25.231);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.covering_fragment, container,
                false);

        initializeLayoutsElements(view);
        mMapView.onCreate(savedInstanceState);
        //call method which will create and initialize google maps
        createMap();
        //select navigation drawer position which answer to current activity
    //    ((MainActivity) getActivity()).selectNavigationDrawer(5);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        //get position of title from resources and set title to action bar
       // String[] res = getResources().getStringArray(R.array.menu_items);
       // getActivity().getActionBar().setTitle(res[5]);
    }



    /**
     * Make initialize of all layout elements and assign click listeners.
     * */
    private void initializeLayoutsElements(View view) {

        mMapView = (MapView) view.findViewById(R.id.map_view);

    }





    /**
     * Initialize and add google maps and set markers into maps if we have markers
     * at all.After setting marker with some params into maps create camera animation
     * and bring user to destination which we can set pragmatically
     */
    private void createMap() {

        //try to initialize google maps
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
            googleMap = mMapView.getMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create and add marker
        try {
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(mCongressLocation).title("EcommCongress"));

        } catch (Exception exception) {
            // We can't add Google Map Marker
            Toast.makeText(getActivity(), "Cannot add marker", Toast.LENGTH_LONG).show();
        }

        //set camera position to zoom 10
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mCongressLocation).zoom(16).build();
        //after set camera position get to this zoom level with some animation
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }


}

