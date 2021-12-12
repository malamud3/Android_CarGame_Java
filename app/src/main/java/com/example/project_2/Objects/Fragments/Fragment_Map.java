package com.example.project_2.Objects.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project_2.CallBack_Map;
import com.example.project_2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

public class Fragment_Map extends Fragment {

    public Fragment_Map(){}

    private AppCompatActivity activity;

    private CallBack_Map callBack_map;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setCallBack_map(CallBack_Map callBack_map) {
        this.callBack_map = callBack_map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_map, container, false);
        initViews();
        asyncMap();
        return view;
    }


    private void initViews() {}

    public void asyncMap() {
        //init
        SupportMapFragment supportMapFragment =(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        //async
        supportMapFragment.getMapAsync(googleMap -> {
            // When map is loaded
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                 // When clicked on map
                 //init marker
                    MarkerOptions markerOptions= new MarkerOptions();
                    //set position
                    markerOptions.position(latLng);
                    //set title
                    markerOptions.title(latLng.latitude+" : "+ latLng.longitude);
                    //remove all
                    googleMap.clear();
                    //zoom
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                    //add marker
                    googleMap.addMarker(markerOptions);
                }
            });

        });

    }

    public void marker(double latitude, double longitude){


    }
}
