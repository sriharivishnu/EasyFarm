package com.magnitudestudios.shad_ep.easyfarm;

import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by sriharivishnu on 2019-07-16.
 */

public class HomeFragment extends Fragment implements OnMapReadyCallback{
    private MapView mMapView;
    private GoogleMap mMap;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mMapView = view.findViewById(R.id.mapView);

        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
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
