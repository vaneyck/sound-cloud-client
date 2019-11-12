package com.vanks.sound_cloud_client.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vanks.sound_cloud_client.collection.AlbumCollection
import com.vanks.sound_cloud_client.collection.PlaylistCollection
import com.vanks.sound_cloud_client.collection.TrackCollection
import com.vanks.sound_cloud_client.domainmodel.Album
import com.vanks.sound_cloud_client.domainmodel.Playlist
import com.vanks.sound_cloud_client.domainmodel.TrackHolder

class MusicRepository {
    private val albums = MutableLiveData<AlbumCollection>()
    private val tracks = MutableLiveData<TrackCollection>()
    private val playlists = MutableLiveData<PlaylistCollection>()
    private val trackHolder = MutableLiveData<TrackHolder>()

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

    fun getTrackHolder(resourceId: Long,resourceType: String) : LiveData<TrackHolder> {
        if (resourceType == "Album") {
            trackHolder.value = Album()
        } else {
            trackHolder.value = Playlist()
        }
        return trackHolder
    }
}