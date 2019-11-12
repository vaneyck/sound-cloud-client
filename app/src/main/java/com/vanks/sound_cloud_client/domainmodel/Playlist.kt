package com.vanks.sound_cloud_client.domainmodel

import com.vanks.sound_cloud_client.collection.TrackCollection

class Playlist : TrackHolder {
    val id: Long = (Math.random() * 1000).toLong()
    val title: String = "Playlist Title"
    override val imageUrl: String = "https://picsum.photos/200/200?id=" + Math.random()
    override fun toString(): String {
        return "Playlist(title='$title', imageUrl='$imageUrl')"
    }

    override fun getTrackCollection(): TrackCollection {
        return TrackCollection()
    }
}