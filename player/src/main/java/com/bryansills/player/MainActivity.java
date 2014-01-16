package com.bryansills.player;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private VideoItemAdapter adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue rq = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(rq, new BitmapLruCache());

        adpt = new VideoItemAdapter(new ArrayList<VideoItem>(), this, imageLoader);
        ListView lView = (ListView) findViewById(R.id.listView);

        lView.setAdapter(adpt);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoItem videoItem = adpt.getItem(position);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(videoItem.getVideoUri(), "video/*");
                startActivity(intent);
            }
        });

        String url ="http://kamcord.com/api/ingameviewer/feed/?developer_key=[snip]&type=trending";

        JsonArrayRequest jReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<VideoItem> result = new ArrayList<VideoItem>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                result.add(convertVideoItem(response
                                        .getJSONObject(i)));
                            } catch (JSONException e) {
                            }
                        }
                        adpt.setItemList(result);
                        adpt.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error

            }
        });

        rq.add(jReq);
    }

    private VideoItem convertVideoItem(JSONObject jsonObject) {
        String imageUri = null;
        String title = null;
        Uri videoUri = null;

        try {
            imageUri = jsonObject.getString("thumbnail_url");
            title = jsonObject.getString("title");
            videoUri = Uri.parse(jsonObject.getString("video_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new VideoItem(imageUri, title, videoUri);
    }

}
