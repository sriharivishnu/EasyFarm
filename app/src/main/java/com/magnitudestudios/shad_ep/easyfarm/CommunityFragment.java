package com.magnitudestudios.shad_ep.easyfarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sriharivishnu on 2019-07-16.
 */

public class CommunityFragment extends Fragment {
    private ListView listView;
    private ArrayList<String> test;
    public static CommunityFragment newInstance() {
        CommunityFragment fragment = new CommunityFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        view.findViewById(R.id.chats_listview);

        String arr[] = {"Jake", "Lisa", "Chris"};
        String arr2[] = {"Great deal on Oranges at Regent...", "Amazing blueberries 50% off!", "Can't wait to eat these delicious..."};
        Integer arr3[] = {R.drawable.jake_pic, R.drawable.lisa_pic, R.drawable.chris_pic};

        test = new ArrayList<>(Arrays.asList(arr));
        ArrayList<String> chats = new ArrayList<>(Arrays.asList(arr2));
        ArrayList<Integer> images = new ArrayList<>(Arrays.asList(arr3));
        ShoppingAdapter adapter = new ShoppingAdapter(getContext(), test, chats, images);
        listView = view.findViewById(R.id.chats_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ShowPost.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
