package com.vanks.sound_cloud_client.collection

import com.vanks.sound_cloud_client.viewmodel.Playlist
import java.util.*
import kotlin.collections.ArrayList

class PlaylistCollection {
    val playlists: ArrayList<Playlist> = ArrayList()

    override fun toString(): String {
        return "PlaylistCollection(playlists=$playlists)"
    }
}