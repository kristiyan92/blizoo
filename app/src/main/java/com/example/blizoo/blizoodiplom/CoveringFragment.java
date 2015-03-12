package com.example.blizoo.blizoodiplom;


import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;

import java.util.List;

import static android.view.View.OnClickListener;


public class CoveringFragment extends Fragment implements OnClickListener {

    // Boolean key used to prevent always to send data to the server
    private Boolean mSendPostRequest = true;

    //Default debugging tag
    private static final String TAG = "Conference Destination Fragment";

    //Create google maps
    private GoogleMap googleMap;


    //variable which will hold congress destination coordination
    private LatLng mCongressLocation = new LatLng(42.649324, 23.395420);


    //Create google maps view, and with him we can show google maps on the screen
    private MapView mMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.covering_fragment, container,
                false);

        initializeLayoutsElements(view);

        mMapView.onCreate(savedInstanceState);

        //call method which will create and initialize google maps
        createMap();
        getLocationFromAddress("Варна");


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mMapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();

        mMapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mMapView.onSaveInstanceState(outState);
    }

    /**
     * Initialize logic behind Click Listeners
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Integer identifierId = view.getId();

        switch (identifierId) {


        }
    }

    /**
     * Make initialize of all layout elements and assign click listeners.
     */
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
            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    googleMap = map;
                    googleMap.getUiSettings().setMapToolbarEnabled(false);
                    addMarkerToMap();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(getActivity());
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );


        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }


    /**
     * Add marker to the map*
     */
    private void addMarkerToMap() {

        // create and add marker
        try {
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(getLocationFromAddress("Лозенград 3,Варна")).title("eCommCongress"));

        } catch (Exception exception) {
            // We can't add Google Map Marker
            Toast.makeText(getActivity(), getString(R.string.cannot_add_marker), Toast.LENGTH_LONG).show();
        }

        //set camera position to zoom 16
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(getLocationFromAddress("Лозенград 3,Варна")).zoom(10).build();
        //after set camera position get to this zoom level with some animation
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

    }


}



