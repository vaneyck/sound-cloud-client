package com.vanks.sound_cloud_client.viewmodel

import com.vanks.sound_cloud_client.collection.TrackCollection
import com.vanks.sound_cloud_client.util.getRandomInt

class User : TrackHolder {
    override var id: Int = 0
    override var title: String = "User Title"
    override var imageUrl: String = "https://picsum.photos/900/900?id=" + getRandomInt()
    var tracks: TrackCollection = TrackCollection()

    constructor(_id: Int, _title: String, _imageUrl: String) {
        this.id = _id
        this.title = _title
        this.imageUrl = _imageUrl
    }

    override fun getCreator(): String {
        return title
    }

    override fun toString(): String {
        return "User(title='$title', imageUrl='$imageUrl')"
    }

    override fun getTrackCollection(): TrackCollection {
        return tracks
    }

}