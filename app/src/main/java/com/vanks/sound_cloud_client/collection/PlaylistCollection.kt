package com.vanks.sound_cloud_client.collection

import com.vanks.sound_cloud_client.domainmodel.Playlist
import java.util.*
import kotlin.collections.ArrayList

class PlaylistCollection {
    val playlists: ArrayList<Playlist> = ArrayList(
        Arrays.asList(
            Playlist(),
            Playlist(),
            Playlist(),
            Playlist(),
            Playlist(),
            Playlist(),
            Playlist(),
            Playlist(),
            Playlist()
        )
    )

    override fun toString(): String {
        return "PlaylistCollection(playlists=$playlists)"
    }
}