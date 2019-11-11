package com.vanks.sound_cloud_client.domainmodel

class Playlist {
    val title: String = "Playlist Title"
    val imageUrl: String = "https://picsum.photos/200/200?id=" + Math.random()
    override fun toString(): String {
        return "Playlist(title='$title', imageUrl='$imageUrl')"
    }
}