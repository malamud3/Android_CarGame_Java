package com.example.project_2.Objects.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.project_2.Objects.DataManagement.MyDB;
import com.example.project_2.Objects.DataManagement.User;
import com.example.project_2.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Fragment_high_score extends Fragment {

    private ArrayList<MaterialButton> usersHighScore;
    private final MyDB myDB = new MyDB();

    private Fragment fragment=new Fragment_Map();
    private FusedLocationProviderClient fusedLocationProviderClient;
    double  latitude;
    double  longitude;
    private String place;

    public Fragment_high_score() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freagment_high_score, container, false);
        initViews(view);
        btnHandle();
        // updateUsers();
        return view;
    }

    private void initViews(View view) {
        usersHighScore = new ArrayList<MaterialButton>() {   };

        usersHighScore.add(view.findViewById(R.id.n1));
        usersHighScore.add(view.findViewById(R.id.n2));
        usersHighScore.add(view.findViewById(R.id.n3));
        usersHighScore.add(view.findViewById(R.id.n4));
        usersHighScore.add(view.findViewById(R.id.n5));
        usersHighScore.add(view.findViewById(R.id.n6));
        usersHighScore.add(view.findViewById(R.id.n7));
        usersHighScore.add(view.findViewById(R.id.n8));
        usersHighScore.add(view.findViewById(R.id.n9));
        usersHighScore.add(view.findViewById(R.id.n10));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayoutMap ,fragment)
                .commit();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        getChildFragmentManager().beginTransaction().add(R.id.frameLayoutMap, new Fragment_Map()).commit();
    }

    public void btnHandle() {
        for (int i = 0; i < usersHighScore.size(); i++) {
            usersHighScore.get(i).setOnClickListener(v -> {
                getLocation();
                usersHighScore.get(0).setText(place);
            });
        }
    }

    public void getLocation() {
        if (getActivity() != null)
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLatitude(), 1);
                             latitude = addresses.get(0).getLatitude();
                             longitude = addresses.get(0).getLongitude();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
    }

    public void checkUserPoints(User u) {
        int place=11;// best 10
        if (myDB.getUsers().isEmpty()) {// no ppl
            usersHighScore.get(0).setText(u.getUserName() + "score is: " + u.getPoints());

            } else if (myDB.getUsers().size() < 10) { //less then 10
             usersHighScore.get(myDB.getUsers().size()).setText(u.getUserName() + "score is: " + u.getPoints());
             }
                    else { //10 ppl in bored
                          for (User userNew : myDB.getUsers()) {
                             if (Integer.getInteger(u.getPoints()) > Integer.getInteger(userNew.getPoints())) {
                             place--;//better Place
                             }
                             }
                    usersHighScore.get(myDB.getUsers().size()).setText(u.getUserName() + "score is: " + u.getPoints());
                    }
        }

    public void updateUsers(){
    myDB.readUser();
    int i=0;
    for(User userNew : myDB.getUsers() ){
      usersHighScore.get(i).setText(userNew.getUserName()+"score is: " + userNew.getPoints());
        i++;
    }
}

}