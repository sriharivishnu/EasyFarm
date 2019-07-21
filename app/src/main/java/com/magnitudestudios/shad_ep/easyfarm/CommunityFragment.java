package com.magnitudestudios.shad_ep.easyfarm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sriharivishnu on 2019-07-16.
 */

public class CommunityFragment extends Fragment {

    public static CommunityFragment newInstance() {
        CommunityFragment fragment = new CommunityFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        return view;
    }
}
