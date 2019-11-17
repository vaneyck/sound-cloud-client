package com.vanks.sound_cloud_client.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vanks.sound_cloud_client.collection.UserCollection
import com.vanks.sound_cloud_client.collection.PlaylistCollection
import com.vanks.sound_cloud_client.collection.TrackCollection
import com.vanks.sound_cloud_client.util.IMAGE_URL
import com.vanks.sound_cloud_client.viewmodel.User
import com.vanks.sound_cloud_client.viewmodel.Playlist
import com.vanks.sound_cloud_client.viewmodel.Track
import com.vanks.sound_cloud_client.viewmodel.TrackHolder
import com.vanks.sound_cloud_client.webservice.SoundCloudPlaylist
import com.vanks.sound_cloud_client.webservice.SoundCloudTrack
import com.vanks.sound_cloud_client.webservice.SoundCloudUser
import com.vanks.sound_cloud_client.webservice.SoundCloudWebservice
import retrofit2.*

class MusicRepository {
    val TAG: String = "MusicRepository"

    //   TODO Use this track to set the notification

    private val users = MutableLiveData<UserCollection>()
    private val tracks = MutableLiveData<TrackCollection>()
    private val playlists = MutableLiveData<PlaylistCollection>()
    private val trackHolder = MutableLiveData<TrackHolder>()
    private val currentTrack = MutableLiveData<Track>()
    private val inPlayerMode = MutableLiveData<Boolean>()
    private val collectionTracks = MutableLiveData<TrackCollection>()

    var previousTrack: Track? = null

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.soundcloud.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getSoundCloudWebservice(): SoundCloudWebservice {
        return getRetrofit().create(SoundCloudWebservice::class.java)
    }

    fun searchTracks(searchTerm: String) {
        getSoundCloudWebservice().searchTracks(searchTerm).enqueue(saveTracksCallback())
    }

    fun searchPlaylists(searchTerm: String) {
        getSoundCloudWebservice().searchCollection(searchTerm).enqueue(savePlaylistsCallback())
    }

    fun searchUsers(searchTerm: String) {
        getSoundCloudWebservice().searchUsers(searchTerm).enqueue(saveUsersCallback())
    }

    fun retrieveTracksForCollection(collectionId: Int, collectionType: String) {
        if (collectionType == "User") {
            getSoundCloudWebservice().retrieveUserTracks(collectionId)
                .enqueue(saveCollectionTracksCallback(collectionId))
        } else {
            getSoundCloudWebservice().retrievePlaylistTracks(collectionId)
                .enqueue(saveCollectionTracksCallback(collectionId))
        }
    }

    fun getResourceId(index: Int, collectionType: String): Int {
        if (collectionType == "User") {
            var t = (users.value as UserCollection)
            return t.users[index].id
        } else {
            var t = (playlists.value as PlaylistCollection)
            return t.playlists[index].id
        }
    }

    fun getTracksForCollection(): LiveData<TrackCollection> {
        if (collectionTracks.value == null) {
            collectionTracks.value = TrackCollection()
        }
        return collectionTracks
    }

    fun getAlbumCollection(): LiveData<UserCollection> {
        if (users.value == null) {
            users.value = UserCollection()
        }
        return users
    }

    fun getTrackCollection(): LiveData<TrackCollection> {
        if (tracks.value == null) {
            tracks.value = TrackCollection()
        }
        return tracks
    }

    fun getPlaylistCollection(): LiveData<PlaylistCollection> {
        if (playlists.value == null) {
            playlists.value = PlaylistCollection()
        }
        return playlists
    }

    fun getTrackHolder(resourceId: Int, resourceType: String): LiveData<TrackHolder> {
        if (resourceType == "User") {
            trackHolder.value = users.value!!.users[resourceId]
        } else {
            trackHolder.value = playlists.value!!.playlists[resourceId]
        }
        return trackHolder
    }

    fun setCurrentTrack(t: Track) {
        currentTrack.value = t
    }

    fun retrieveCurrentTrack(): LiveData<Track> {
        return currentTrack
    }

    fun setInPlayerMode(inMode: Boolean) {
        inPlayerMode.value = inMode
    }

    fun retrieveInPlayerMode () : LiveData<Boolean> {
        return inPlayerMode
    }

    private fun saveTracksCallback(): Callback<Array<SoundCloudTrack>> {
        return object : Callback<Array<SoundCloudTrack>> {
            override fun onResponse(
                call: Call<Array<SoundCloudTrack>>,
                response: Response<Array<SoundCloudTrack>>
            ) {
                if (response.body() != null) {
                    var soundCloudTracks = response.body() as Array<SoundCloudTrack>
                    var trackCollection = TrackCollection()
                    for (track in soundCloudTracks) {
                        trackCollection.tracks.add(
                            Track(track)
                        )
                    }
                    tracks.value = trackCollection
                }
            }

            // Error case is left out for brevity.
            override fun onFailure(call: Call<Array<SoundCloudTrack>>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        }
    }

    private fun saveCollectionTracksCallback(collectionId: Int): Callback<Array<SoundCloudTrack>> {
        return object : Callback<Array<SoundCloudTrack>> {
            override fun onResponse(
                call: Call<Array<SoundCloudTrack>>,
                response: Response<Array<SoundCloudTrack>>
            ) {
                if (response.body() != null) {
                    var soundCloudTracks = response.body() as Array<SoundCloudTrack>
                    var trackCollection = TrackCollection()
                    for (track in soundCloudTracks) {
                        trackCollection.tracks.add(
                            Track(track)
                        )
                    }
                    collectionTracks.value = trackCollection
                }
            }

            // Error case is left out for brevity.
            override fun onFailure(call: Call<Array<SoundCloudTrack>>, t: Throwable) {
                Log.e(TAG, t.toString())
                collectionTracks.value = TrackCollection()
            }
        }
    }

    private fun savePlaylistsCallback(): Callback<Array<SoundCloudPlaylist>> {
        return object : Callback<Array<SoundCloudPlaylist>> {
            override fun onResponse(
                call: Call<Array<SoundCloudPlaylist>>,
                response: Response<Array<SoundCloudPlaylist>>
            ) {
                if (response.body() != null) {
                    var soundCloudPlaylists = response.body() as Array<SoundCloudPlaylist>
                    var playlistCollection = PlaylistCollection()
                    for (playlist in soundCloudPlaylists) {
                        playlistCollection.playlists.add(
                            Playlist(
                                playlist.id,
                                playlist.title,
                                playlist.artwork_url ?: IMAGE_URL,
                                playlist.user.username
                            )
                        )
                    }
                    playlists.value = playlistCollection
                }
            }

            // Error case is left out for brevity.
            override fun onFailure(call: Call<Array<SoundCloudPlaylist>>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        }
    }


    private fun saveUsersCallback(): Callback<Array<SoundCloudUser>> {
        return object : Callback<Array<SoundCloudUser>> {
            override fun onResponse(
                call: Call<Array<SoundCloudUser>>,
                response: Response<Array<SoundCloudUser>>
            ) {
                if (response.body() != null) {
                    var SoundCloudUsers = response.body() as Array<SoundCloudUser>
                    var userCollection = UserCollection()
                    for (user in SoundCloudUsers) {
                        userCollection.users.add(
                            User(
                                user.id,
                                user.username,
                                user.avatar_url ?: IMAGE_URL
                            )
                        )
                    }
                    users.value = userCollection
                }
            }

            // Error case is left out for brevity.
            override fun onFailure(call: Call<Array<SoundCloudUser>>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        }
    }
}