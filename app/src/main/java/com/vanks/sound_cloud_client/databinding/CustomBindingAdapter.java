package com.vanks.sound_cloud_client.databinding;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vanks.sound_cloud_client.R;
import com.vanks.sound_cloud_client.adapter.AlbumAdapter;
import com.vanks.sound_cloud_client.adapter.PlaylistAdapter;
import com.vanks.sound_cloud_client.adapter.TrackAdapter;
import com.vanks.sound_cloud_client.collection.AlbumCollection;
import com.vanks.sound_cloud_client.collection.PlaylistCollection;
import com.vanks.sound_cloud_client.collection.TrackCollection;

public class CustomBindingAdapter {
    private static String TAG = "CustomBindingAdapter";

    @BindingAdapter({"imageUrl"})
    public static void imageLoader(ImageView imageView, String imageUrl) {
        Log.i(TAG, "Loading image : url is " + imageUrl);
        if (imageUrl == null || imageUrl.isEmpty()) {
            return;
        }
        Picasso.get().load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }

    @BindingAdapter("trackData")
    public static void setArticlesInRecyclerView(RecyclerView recyclerView, TrackCollection collection) {
        Log.i(TAG, collection.toString());
        TrackAdapter adapter = (TrackAdapter) recyclerView.getAdapter();
        adapter.setData(collection.getTracks());
    }

    @BindingAdapter("albumData")
    public static void setArticlesInRecyclerView(RecyclerView recyclerView, AlbumCollection collection) {
        Log.i(TAG, collection.toString());
        AlbumAdapter adapter = (AlbumAdapter) recyclerView.getAdapter();
        Log.i(TAG, "Adapter is " + adapter.toString());
        adapter.setData(collection.getAlbums());
    }

    @BindingAdapter("playlistData")
    public static void setArticlesInRecyclerView(RecyclerView recyclerView, PlaylistCollection collection) {
        Log.i(TAG, collection.toString());
        PlaylistAdapter adapter = (PlaylistAdapter) recyclerView.getAdapter();
        adapter.setData(collection.getPlaylists());
    }
}
