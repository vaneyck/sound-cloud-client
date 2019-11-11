package com.vanks.sound_cloud_client.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vanks.sound_cloud_client.collection.AlbumCollection
import com.vanks.sound_cloud_client.collection.PlaylistCollection
import com.vanks.sound_cloud_client.collection.TrackCollection

class MusicRepository {
    private val albums = MutableLiveData<AlbumCollection>()
    private val tracks = MutableLiveData<TrackCollection>()
    private val playlists = MutableLiveData<PlaylistCollection>()

    fun getAlbumCollection () : LiveData<AlbumCollection> {
        albums.value = AlbumCollection()
        return albums
    }

    fun getTrackCollection () : LiveData<TrackCollection> {
        tracks.value = TrackCollection()
        return tracks
    }

    fun getPlaylistCollection () : LiveData<PlaylistCollection> {
        playlists.value = PlaylistCollection()
        return playlists
    }
}