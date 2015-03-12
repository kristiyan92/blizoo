package com.example.blizoo.blizoodiplom;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import static android.view.View.OnClickListener;


public class CoveringFragment extends Fragment {

    private static final LatLng LOWER_MANHATTAN = new LatLng(43.216248, 27.921407);
    private static final LatLng TIMES_SQUARE = new LatLng(43.213770, 27.922638);
    private static final LatLng BROOKLYN_BRIDGE = new LatLng(43.218098, 27.931969);
    private static final LatLng Silistra = new LatLng(43.219631, 27.920808);

    private GoogleMap googleMap;
    private SupportMapFragment mapFragment;
    private Circle mCircle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.covering_fragment, container, false);

        createMap();

        return view;
    }
/*    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }*/

 /*   @Override
    public void onResume() {
        super.onResume();
        if (googleMap == null) {
            googleMap = fragment.getMap();
            googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)));
            if (googleMap != null) {
                addLines();
            }
        }
    }*/

    private void createMap() {

        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        }
        if (googleMap == null) {
            googleMap = mapFragment.getMap();
            addMarker();

            if (googleMap != null) {
                addLines();
            }
        }
    }

    private void drawCircle(LatLng position) {
        double radiusInMeters = 500.0;
        int strokeColor = 0xffff0000; //red outline
        int shadeColor = 0x44ff0000; //opaque red fill

        //add circle
        CircleOptions circle = new CircleOptions();
        circle.center(position).fillColor(shadeColor).radius(radiusInMeters).strokeColor(strokeColor).strokeWidth(4);
        googleMap.addCircle(circle);


    }

    private void addMarker() {

        try {
            googleMap.addMarker(new MarkerOptions().position(TIMES_SQUARE)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.hub32)));
            drawCircle(TIMES_SQUARE);
            googleMap.addMarker(new MarkerOptions().position(BROOKLYN_BRIDGE)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.hub32)));
            drawCircle(BROOKLYN_BRIDGE);
            googleMap.addMarker(new MarkerOptions().position(LOWER_MANHATTAN)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.hub32)));
            drawCircle(LOWER_MANHATTAN);
            googleMap.addMarker(new MarkerOptions().position(Silistra)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.hub32)));
            drawCircle(LOWER_MANHATTAN);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Cannot add marker", Toast.LENGTH_LONG).show();
        }
    }

    private void addLines() {

        googleMap
                .addPolyline((new PolylineOptions())
                        .add(TIMES_SQUARE, BROOKLYN_BRIDGE, LOWER_MANHATTAN,
                                TIMES_SQUARE,Silistra).width(5).color(Color.BLUE)
                        .geodesic(true));
        // move camera to zoom on map
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Silistra,
                10));
    }


}



