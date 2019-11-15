package com.vanks.sound_cloud_client.viewmodel

import com.vanks.sound_cloud_client.collection.TrackCollection
import com.vanks.sound_cloud_client.util.Reusable
import com.vanks.sound_cloud_client.util.getRandomInt

class Playlist : TrackHolder {
    override var id: Int = 0
    override var title: String = "Playlist Title"
    override var imageUrl: String = "https://picsum.photos/900/900?id=" + getRandomInt()
    var artistName: String = ""
    var tracks: TrackCollection = TrackCollection()

    constructor(_id: Int, _title: String, _imageUrl: String, _artistName: String) {
        this.id = _id
        this.title = _title
        this.imageUrl = _imageUrl
        this.artistName = _artistName
    }

    override fun getCreator () : String {
        return artistName
    }

    override fun toString(): String {
        return "Playlist(title='$title', imageUrl='$imageUrl')"
    }

    override fun getTrackCollection(): TrackCollection {
        return tracks
    }
}