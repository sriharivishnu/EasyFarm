package com.magnitudestudios.shad_ep.easyfarm;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ShowMarket extends AppCompatActivity {
    private TextView farmers_title, address_field;
    private ImageButton back_button;
    private ExpandableListView vendors_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_market);
        String[] vendors = {"John and Johnson's","Fresh Greenery", "Good Foods"};
        String[] products = {"Bananas", "Apples", "Grapes"};
        Integer[] parent_images = {R.drawable.stall, R.drawable.stall1, R.drawable.stall2};
        Integer[] child_images =  {R.drawable.banana, R.drawable.apples, R.drawable.grapes};
        farmers_title = findViewById(R.id.title_farmers_market);
        farmers_title.setText(getIntent().getStringExtra("Name"));

        address_field = findViewById(R.id.address);

        back_button = findViewById(R.id.backButton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        vendors_show = findViewById(R.id.vendors_listview);
        double longitude = getIntent().getDoubleExtra("Longitude", 0.0);
        double latitude = getIntent().getDoubleExtra("Latitude", 0.0);
//        try {
//            Geocoder geo = new Geocoder(ShowMarket.this.getApplicationContext(), Locale.getDefault());
//            List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
//            if (addresses.isEmpty()) {
//                address_field.setText("234 Farmers Road");
//            }
//            else {
//                if (addresses.size() > 0) {
//                    address_field.setText(addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
//                    //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace(); // getFromLocation() may sometimes fail
//        }
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(vendors));
        ArrayList<String> temp1 = new ArrayList<>(Arrays.asList(products));
        ArrayList<Integer> temp2 = new ArrayList<>(Arrays.asList(parent_images));
        ArrayList<Integer> temp3 = new ArrayList<>(Arrays.asList(child_images));

        ExpandableListAdapter expandableListView = new ExpandableListAdapter(this, temp, temp1, temp2, temp3);
        vendors_show.setAdapter(expandableListView);

    }
}
