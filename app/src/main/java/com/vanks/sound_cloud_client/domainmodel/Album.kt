package com.vanks.sound_cloud_client.domainmodel

class Album {
    val title: String = "Album Title"
    val imageUrl: String = "https://picsum.photos/200/200?id=" + Math.random()
    override fun toString(): String {
        return "Album(title='$title', imageUrl='$imageUrl')"
    }
}