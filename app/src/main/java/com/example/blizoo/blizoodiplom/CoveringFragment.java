package com.example.blizoo.blizoodiplom;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;


public class CoveringFragment extends Fragment {

    private static final LatLng LOWER_MANHATTAN = new LatLng(43.216248, 27.921407);
    private static final LatLng TIMES_SQUARE = new LatLng(43.218295, 27.920543);
    private static final LatLng BROOKLYN_BRIDGE = new LatLng(43.218098, 27.931969);
    private static final LatLng Silistra = new LatLng(43.219631, 27.920808);
    private static final LatLng newPoint = new LatLng(43.217631, 27.120808);

    private GoogleMap googleMap;
    private SupportMapFragment mapFragment;
    private ArrayList<LatLng> arrayPoints = null;
    private static View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //View view = inflater.inflate(R.layout.covering_fragment, container, false);
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.covering_fragment, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        createMap();
        initializeLayoutElements();

        return view;
    }

    private void initializeLayoutElements() {

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Fragment newFragment = new NearbyClientsContractsFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MainActivity.backStack.add(1);
                transaction.addToBackStack("NearbyClientsContractsFragment");
                transaction.replace(R.id.content_frame, newFragment).commit();

               /* FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                NearbyClientsContractsFragment clientContractFragment = new NearbyClientsContractsFragment();
                transaction
                        .replace(R.id.content_frame, clientContractFragment, "NearbyClientsContractsFragment")
                        .addToBackStack("NearbyClientsContractsFragment").commit();*/
                return true;
            }
        });
    }
/*    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }*/



  /*  @Override
    public void onResume() {
        super.onResume();
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.google_map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.google_map, mapFragment).commit();
        }
        if (googleMap == null) {
            googleMap = mapFragment.getMap();
            // addMarker();

            if (googleMap != null) {
                addMarker();
            }
        }
    }*/

    private void createMap() {
        arrayPoints = new ArrayList<LatLng>();
        arrayPoints.add(TIMES_SQUARE);
        arrayPoints.add(BROOKLYN_BRIDGE);
        arrayPoints.add(LOWER_MANHATTAN);
        arrayPoints.add(Silistra);
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.google_map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.google_map, mapFragment).commit();
        }
        if (googleMap == null) {
            googleMap = mapFragment.getMap();
            // addMarker();

            if (googleMap != null) {
                addMarker();
            }
        }
    }

    private void drawCircle(LatLng position) {
        double radiusInMeters = 30.0;
        int strokeColor = 0xffff0000; //red outline
        int shadeColor = 0x44ff0000; //opaque red fill

        //add circle
        CircleOptions circle = new CircleOptions();
        circle.center(position).fillColor(shadeColor).radius(radiusInMeters).strokeColor(strokeColor).strokeWidth(4);
        googleMap.addCircle(circle);


    }


    private void addMarker() {
        googleMap.clear();
        PolylineOptions rectOptions = new PolylineOptions().color(Color.BLUE).width(2).geodesic(true);

        try {
            for (int i = 0; i < arrayPoints.size(); i++) {
                googleMap.addMarker(new MarkerOptions().position(arrayPoints.get(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.hub32)));
                drawCircle(arrayPoints.get(i));
                rectOptions.add(arrayPoints.get(i));
            }
            googleMap.addPolyline(rectOptions);

            // move camera to zoom on map
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Silistra,
                    14));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Cannot add marker", Toast.LENGTH_LONG).show();
        }
    }

    public float getDistanceInMetres(LatLng firstDest, LatLng secondDest) {

        float[] dist = new float[1];
        double p1 = firstDest.latitude;
        double p2 = firstDest.longitude;
        double p3 = secondDest.latitude;
        double p4 = secondDest.longitude;
        Location.distanceBetween(p1, p2, p3, p4, dist);

        Log.d("RESULT", Float.toString(dist[0]));
        return dist[0];
    }

    private void addLines() {

        PolylineOptions rectOptions = new PolylineOptions().color(Color.BLUE).width(2).geodesic(true);

        for (int i = 0; i < arrayPoints.size(); i++) {
            rectOptions.add(arrayPoints.get(i));
        }
/*        googleMap
                .addPolyline((new PolylineOptions())
                        .add(rectOptions).width(5).color(Color.BLUE)
                        .geodesic(true));*/
        googleMap.addPolyline(rectOptions);

        // move camera to zoom on map
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Silistra,
                14));
    }


}