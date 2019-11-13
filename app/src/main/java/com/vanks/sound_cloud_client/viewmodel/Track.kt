package com.vanks.sound_cloud_client.viewmodel

import com.vanks.sound_cloud_client.util.getRandomInt

class Track {
    var id: Int = 0
    var title: String = "Song title that is very long"
    var imageUrl: String = "https://picsum.photos/200/200?id=" + getRandomInt()
    var artistName: String = "Lil Qwuezy"

    constructor(_id: Int, _title: String, _imageUrl: String, _artistName: String) {
        this.id = _id
        this.title = _title
        this.imageUrl = _imageUrl
        this.artistName = _artistName
    }

    override fun toString(): String {
        return "Track(title='$title', imageUrl='$imageUrl', artistName='$artistName')"
    }
}