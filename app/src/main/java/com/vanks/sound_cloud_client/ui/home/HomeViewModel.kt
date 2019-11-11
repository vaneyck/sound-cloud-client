package com.vanks.sound_cloud_client.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vanks.sound_cloud_client.collection.AlbumCollection
import com.vanks.sound_cloud_client.collection.PlaylistCollection
import com.vanks.sound_cloud_client.collection.TrackCollection

class HomeViewModel : ViewModel() {
    var trackCollection: LiveData<TrackCollection> = MutableLiveData<TrackCollection>()
    var albumCollection: LiveData<AlbumCollection> = MutableLiveData<AlbumCollection>()
    var playlistCollection: LiveData<PlaylistCollection> = MutableLiveData<PlaylistCollection>()
}