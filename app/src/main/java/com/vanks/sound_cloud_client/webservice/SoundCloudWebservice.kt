package com.vanks.sound_cloud_client.webservice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SoundCloudWebservice {
    @GET("https://api.soundcloud.com/tracks?client_id=95f22ed54a5c297b1c41f72d713623ef")
    fun searchTracks(@Query("q") searchQuery: String): Call<Array<SoundCloudTrack>>

    @GET("https://api.soundcloud.com/playlists?client_id=95f22ed54a5c297b1c41f72d713623ef")
    fun searchCollection(@Query("q") searchQuery: String): Call<Array<SoundCloudPlaylist>>

    @GET("https://api.soundcloud.com/users?client_id=95f22ed54a5c297b1c41f72d713623ef")
    fun searchUsers(@Query("q") searchQuery: String): Call<Array<SoundCloudUser>>

    @GET("https://api.soundcloud.com/playlists/{playlistId}/tracks?client_id=95f22ed54a5c297b1c41f72d713623ef")
    fun retrievePlaylistTracks(@Path("playlistId") playlistId: Int): Call<Array<SoundCloudTrack>>
}

class SoundCloudPlaylist {
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var genre: String = ""
    var release: String = ""
    var uri: String = "https://api.soundcloud.com/playlists/326149156"
    var track_count: Int = 3
    var permalink_url: String = ""
    var created_at: String = "2017/05/26 20:18:17 +0000"
    var last_modified: String = "2017/05/26 20:18:17 +0000"
    var duration: Int = 1539104
    var artwork_url: String = ""
    var user: SoundCloudUser = SoundCloudUser()
    var tracks_uri: String = "https://api.soundcloud.com/playlists/326149156/tracks"
    var likes_count: Int = 0
    var playlist_type: String = ""
}

class SoundCloudTrack {
    var id: Int = 0
    var permalink_url: String = ""
    var title: String = ""
    var duration: Int = 0
    var waveform_url: String = ""
    var stream_url : String = ""
    var uri: String = ""
    var user_id: Int = 0
    var artwork_url: String = ""
    var comment_count: Int = 0
    var description: String = ""
    var download_count: Int = 0
    var downloadable: Boolean = false
    var favoritings_count: Int = 0
    var genre: String = ""
    var release: String = ""
    var reposts_count: Int = 0
    var streamable: Boolean = false
    var tag_list: String = "Photograph Live Acoustic Pop Multiply X 2014"
    var track_type: String = ""
    var user: SoundCloudUser = SoundCloudUser()
    var likes_count: Int = 0
    var attachments_uri: String = "https://api.soundcloud.com/tracks/161084205/attachments"
    var video_url: String = ""
    var download_url: String = ""

    override fun toString(): String {
        return "SoundCloudTrack(id=$id, title='$title')"
    }
}

class SoundCloudUser {
    var avatar_url: String = ""
    var id: Int = 0
    var permalink_url: String = ""
    var uri: String = ""
    var username: String = ""
    var last_modified: String = ""
}

class TrackSearchResults {
    var tracks: Array<SoundCloudTrack> = emptyArray()
}