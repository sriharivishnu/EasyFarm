package com.magnitudestudios.shad_ep.easyfarm;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Integer> images;
    ArrayList<String> titles;
    ArrayList<String> bodies;
    private ImageView image;
    private TextView title;
    private TextView body;

    private static LayoutInflater inflater = null;

    public MyAdapter(Context context, ArrayList<Integer> images, ArrayList<String> titles, ArrayList<String> texts) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.images = images;
        this.titles = titles;
        this.bodies = texts;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        vi = inflater.inflate(R.layout.row_deals, null);
        image = vi.findViewById(R.id.row_image);
        title = vi.findViewById(R.id.row_title);
        body = vi.findViewById(R.id.row_text);

        image.setImageResource(images.get(position));
        title.setText(titles.get(position));
        body.setText(bodies.get(position));
        return vi;
    }
}
