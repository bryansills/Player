package com.bryansills.player;

import android.net.Uri;

/**
 * Created by bryan on 1/15/14.
 */
public class VideoItem {
    private String imageUri;
    private String title;
    private Uri videoUri;

    public VideoItem(String imageUri, String title, Uri videoUri) {
        this.imageUri = imageUri;
        this.title = title;
        this.videoUri = videoUri;
    }


    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(Uri videoUri) {
        this.videoUri = videoUri;
    }
}
