package com.magnitudestudios.shad_ep.easyfarm;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoppingAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> items, items2;
    CheckBox item;
    boolean which;
    ArrayList<Integer> images;


    private static LayoutInflater inflater = null;

    public ShoppingAdapter(Context context, ArrayList<String> items) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.items = items;
        this.which = true;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public ShoppingAdapter(Context context, ArrayList<String> items,ArrayList<String> items2, ArrayList<Integer> images) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.items = items;
        this.items2 = items2;
        this.which = false;
        this.images = images;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (which) {
            return items.size() + 1;
        }
        else {
            return items.size();
        }

    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        if (position>items.size()) {
            return null;
        }
        return items.get(position);
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
        if (which) {
            if (position < items.size()) {
                vi = inflater.inflate(R.layout.row_cart, null);
                item = vi.findViewById(R.id.cart_item);
                item.setText(items.get(position));
            } else {
                vi = inflater.inflate(R.layout.row_add, null);
            }
        }
        else {
            vi = inflater.inflate(R.layout.row_chat, null);
            TextView name = vi.findViewById(R.id.row_title);
            TextView remaining = vi.findViewById(R.id.chat_body);
            ImageView image = vi.findViewById(R.id.row_image);

            name.setText(items.get(position));
            remaining.setText(items2.get(position));
            image.setImageResource(images.get(position));
        }



        return vi;
    }
}
