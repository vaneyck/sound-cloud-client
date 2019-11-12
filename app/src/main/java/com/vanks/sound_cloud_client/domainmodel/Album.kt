package com.vanks.sound_cloud_client.domainmodel

import com.vanks.sound_cloud_client.collection.TrackCollection

class Album : TrackHolder {
    val id: Long = (Math.random() * 1000).toLong()
    val title: String = "Album Title"
    override val imageUrl: String = "https://picsum.photos/200/200?id=" + Math.random()
    override fun toString(): String {
        return "Album(title='$title', imageUrl='$imageUrl')"
    }

    override fun getTrackCollection(): TrackCollection {
        return TrackCollection()
    }

}