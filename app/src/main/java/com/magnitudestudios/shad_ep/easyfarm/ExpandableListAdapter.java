package com.magnitudestudios.shad_ep.easyfarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> vendors;
    private ArrayList<String> options;
    private ArrayList<Integer> parent_images;
    private ArrayList<Integer> child_images;

    public ExpandableListAdapter(Context context, ArrayList<String> vendors, ArrayList<String> options, ArrayList<Integer> parent_images, ArrayList<Integer> child_images) {
        this.context = context;
        this.vendors = vendors;
        this.options = options;
        this.parent_images = parent_images;
        this.child_images = child_images;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return options.get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_product, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.row_title);
        title.setText(options.get(expandedListPosition));

        ImageView image_child = convertView.findViewById(R.id.row_image);
        image_child.setImageResource(child_images.get(expandedListPosition));

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return options.size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return vendors.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return vendors.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_vendors, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.row_title);
        listTitleTextView.setText(listTitle);
        ImageView image_group = convertView.findViewById(R.id.row_image);
        image_group.setImageResource(parent_images.get(listPosition));
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}