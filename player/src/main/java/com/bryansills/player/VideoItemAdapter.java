package com.bryansills.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by bryan on 1/15/14.
 */
public class VideoItemAdapter extends ArrayAdapter<VideoItem> {

    private List<VideoItem> itemList;
    private Context context;
    private ImageLoader imageLoader;

    public VideoItemAdapter(List<VideoItem> itemList, Context ctx, ImageLoader imageLoader) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
        this.imageLoader = imageLoader;
    }

    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }

    public VideoItem getItem(int position) {
        if (itemList != null)
            return itemList.get(position);
        return null;
    }

    public long getItemId(int position) {
        if (itemList != null)
            return itemList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_video_item, null);
        }

        VideoItem videoItem = itemList.get(position);

        TextView textView = (TextView) v.findViewById(R.id.title);
        textView.setText(videoItem.getTitle());

        ImageView thumbnail = (ImageView) v.findViewById(R.id.thumbnail);

        imageLoader.get(videoItem.getImageUri(),
                ImageLoader.getImageListener(thumbnail,
                        R.drawable.no_image,
                        R.drawable.error_image));

        return v;

    }

    public List<VideoItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<VideoItem> itemList) {
        this.itemList = itemList;
    }


}
