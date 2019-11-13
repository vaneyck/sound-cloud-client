package com.vanks.sound_cloud_client.viewmodel

import com.vanks.sound_cloud_client.collection.TrackCollection
import com.vanks.sound_cloud_client.util.getRandomInt

class Album : TrackHolder {
    val id: Int = 0
    override var title: String = "Album Title"
    override var imageUrl: String = "https://picsum.photos/900/900?id=" + getRandomInt()
    override fun toString(): String {
        return "Album(title='$title', imageUrl='$imageUrl')"
    }

    override fun getTrackCollection(): TrackCollection {
        return TrackCollection()
    }

}