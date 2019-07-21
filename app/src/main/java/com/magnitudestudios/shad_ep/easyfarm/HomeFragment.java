package com.magnitudestudios.shad_ep.easyfarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by sriharivishnu on 2019-07-16.
 */

public class HomeFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{
    private MapView mMapView;
    private GoogleMap mMap;
    private ListView ads;
    private ArrayList<Integer> images;
    private ArrayList<String> titles, bodies;
    private ProgressBar progressBar;
    private int item;
    private boolean threadState;
    private boolean runThread;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Integer arr[] = {R.drawable.blueberries_img, R.drawable.apples, R.drawable.apple_fritters};
        images = new ArrayList<>(Arrays.asList(arr));

        String arr1[] = {"Blueberries 50% off!", "Honeycrisp Apples","Apple Fritters"};
        titles = new ArrayList<>(Arrays.asList(arr1));
        String arr2[] = {"Come to Alberta's farmer's market! Quick! Offer ends in 1 hour!","Deal of the week! Freshly picked Get your Honeycrisp apples from your local producer! Only $5.99/lbs!", "Get your local farmers market’s baked special! Get a dozen fresh apple fritters for only $6.99!"};
        bodies = new ArrayList<>(Arrays.asList(arr2));

        item = 0;

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progressBar2);
        mMapView = view.findViewById(R.id.mapView);

        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        final MyAdapter adapter = new MyAdapter(getContext(),images, titles, bodies);
        ads = view.findViewById(R.id.ads_listview);
        ads.setAdapter(adapter);
        ads.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ShowMarket.class);
                intent.putExtra("Name", "Baseline Farmers Market");
                intent.putExtra("Longitude", -113.2993);
                intent.putExtra("Latitude", 53.4085);
                startActivity(intent);
            }
        });

        runThread = true;
        threadState = false;
        if (threadState == false) {
            (new Thread(new Runnable() {

                @Override
                public void run() {
                    while (!Thread.interrupted())
                        try {
                            Thread.sleep(5000);
                            if (getActivity() != null) {
                                getActivity().runOnUiThread(new Runnable() // start actions in UI thread
                                {
                                    @Override
                                    public void run() {
                                        if (runThread) {
                                            ads.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    item++;
                                                    if (item > adapter.getCount()) {
                                                        item = 0;
                                                    }
                                                    ads.smoothScrollToPosition(item);
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                            // ooops
                        }
                }
            })).start();
            threadState = true;
        }

        return view;
    }
    @Override
    public boolean onMarkerClick(final Marker marker) {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(getActivity(), ShowMarket.class);
        intent.putExtra("Name", marker.getTitle());
        intent.putExtra("Longitude", marker.getPosition().longitude);
        intent.putExtra("Latitude", marker.getPosition().latitude);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnMarkerClickListener(this);
        mMap.addMarker(new MarkerOptions().position(new LatLng(50, -107)).title("Farmers Market"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(55.8277, -117.3392)).title("Simard Artizan Farm"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(53.4085, -53.4085)).title("Hinton Farmers' Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(51.1958,-114.4798)).title("Cochrane Farmers Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.5305,-114.2145)).title("Carvel Station Farmer's Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.5423,-113.2993)).title("Baseline Farmers Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(52.3103,-114.1017)).title("Sylvan Lake Farmers market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(52.2617,-113.8060)).title("The Market at Red Deer"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(51.0597,-113.3944)).title("Strathmore Farmers' Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.4477,-113.4795)).title("South Common Farmers Market"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(53.5423,-113.2993)).title("Baseline Farmers Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(55.8277,-113.2993)).title("Simard Artizan Farm"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(50.7569,-113.9776)).title("Okotoks Farmers' Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(50.9858,-114.0512)).title("Calgary Farmers' Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(52.3443,-114.0682)).title("Hidden Valley Garden"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.5461,-113.4868)).title("Edmonton Downtown Farmers Market"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.4931,-113.5347)).title("Green and Gold Community Garden"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(53.5525,-113.5356)).title("124 Grand Market"));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(53.5423,-113.2993)).zoom(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    @Override
    public void onPause() {
        super.onPause();
        threadState = false;
        runThread = false;
    }
    @Override
    public void onResume() {
        super.onResume();
        runThread = true;
        threadState = false;
        mMapView.onResume();
    }
}
