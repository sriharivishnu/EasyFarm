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
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> items;
    CheckBox item;


    private static LayoutInflater inflater = null;

    public ShoppingAdapter(Context context, ArrayList<String> items) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.items = items;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size()+1;
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
        if (position<items.size()) {
            vi = inflater.inflate(R.layout.row_cart, null);
            item = vi.findViewById(R.id.cart_item);
            item.setText(items.get(position));
        }
        else {
            vi = inflater.inflate(R.layout.row_add, null);
        }


        return vi;
    }
}
