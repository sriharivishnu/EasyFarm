package com.magnitudestudios.shad_ep.easyfarm;

import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sriharivishnu on 2019-07-16.
 */

public class HomeFragment extends Fragment implements OnMapReadyCallback{
    private MapView mMapView;
    private GoogleMap mMap;
    private ListView ads;
    private ArrayList<Integer> images;
    private ArrayList<String> titles, bodies;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Integer arr[] = {R.drawable.blueberries_img, R.drawable.apples};
        images = new ArrayList<>(Arrays.asList(arr));
        String arr1[] = {"Blueberries 50% off!", "Honeycrisp Apples"};
        titles = new ArrayList<>(Arrays.asList(arr1));
        String arr2[] = {"Come to Alberta's farmer's market! Quick! Offer ends in 1 hour!","Deal of the week! Freshly picked Get your Honeycrisp apples from your local producer! Only $5.99/lbs!"};
        bodies = new ArrayList<>(Arrays.asList(arr2));

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mMapView = view.findViewById(R.id.mapView);

        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        MyAdapter adapter = new MyAdapter(getContext(),images, titles, bodies);
        ads = view.findViewById(R.id.ads_listview);
        ads.setAdapter(adapter);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker in Sydney"));

    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
}
