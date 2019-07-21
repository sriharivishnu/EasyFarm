package com.magnitudestudios.shad_ep.easyfarm;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Integer> images;
    ArrayList<String> titles;
    ArrayList<String> bodies;
    ArrayList<ViewHolder> viewHolders;

    private Handler mHandler = new Handler();
    private Runnable updateRemainingTimeRunnable = new Runnable() {
        @Override
        public void run() {
            synchronized (viewHolders) {
                long currentTime = System.currentTimeMillis();
                for (ViewHolder viewHolder : viewHolders) {
                    viewHolder.updateTimeRemaining(currentTime);
                }
            }
        }
    };

    private static LayoutInflater inflater = null;

    public MyAdapter(Context context, ArrayList<Integer> images, ArrayList<String> titles, ArrayList<String> texts) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.images = images;
        this.titles = titles;
        this.bodies = texts;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewHolders = new ArrayList<>();


        Timer tmr = new Timer();
        tmr.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(updateRemainingTimeRunnable);
            }
        }, 1000, 1000);
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_deals, null);
            View vi = convertView;
            holder = new ViewHolder((ImageView) vi.findViewById(R.id.row_image), (TextView) vi.findViewById(R.id.row_title),(TextView) vi.findViewById(R.id.row_text),(TextView) vi.findViewById(R.id.timer), System.currentTimeMillis());
            convertView.setTag(holder);
            synchronized (viewHolders) {
                viewHolders.add(holder);
            }
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setData(position);
        return convertView;

    }
    private class ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView body;
        public TextView timer;
        public long expiration;
        private ViewHolder(ImageView image, TextView title, TextView body, TextView timer, long current) {
            this.image = image;
            this.title = title;
            this.body = body;
            this.timer = timer;
            this.expiration = current + 6000000;
        }
        public void setData(int position) {
            image.setImageResource(images.get(position));
            title.setText(titles.get(position));
            body.setText(bodies.get(position));
        }
        public void updateTimeRemaining(long currentTime) {
            long timeDiff = this.expiration - currentTime;
            if (timeDiff > 0) {
                int seconds = (int) (timeDiff / 1000) % 60;
                int minutes = (int) ((timeDiff / (1000 * 60)) % 60);
                int hours = (int) ((timeDiff / (1000 * 60 * 60)) % 24);
                timer.setText(hours + ":" + minutes + ":" + seconds);
            } else {
                timer.setText("Expired!!");
            }
        }


    }
}
