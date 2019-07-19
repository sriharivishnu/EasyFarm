package com.magnitudestudios.shad_ep.easyfarm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sriharivishnu on 2019-07-16.
 */

public class ShoppingFragment extends Fragment {
    private ListView cart;
    private ArrayList<String> items;
    public static ShoppingFragment newInstance() {
        ShoppingFragment fragment = new ShoppingFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] user_items = {"Bananas", "Cheese"};
        items = new ArrayList<>(Arrays.asList(user_items));
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        cart = view.findViewById(R.id.shopping_list);

        ShoppingAdapter adapter = new ShoppingAdapter(getContext(), items);
        cart.setAdapter(adapter);
        return view;
    }
}
