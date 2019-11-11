package com.vanks.sound_cloud_client.domainmodel

class Track {
    val title: String = "Album Title"
    val imageUrl: String = "https://picsum.photos/200/200?id=" + Math.random()
    val artistName: String = "Lil Qwuezy"
    override fun toString(): String {
        return "Track(title='$title', imageUrl='$imageUrl', artistName='$artistName')"
    }
}