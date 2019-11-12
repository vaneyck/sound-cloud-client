package com.vanks.sound_cloud_client.domainmodel

class Track {
    val id: Long = (Math.random() * 1000).toLong()
    val title: String = "Album Title"
    val imageUrl: String = "https://picsum.photos/200/200?id=" + Math.random()
    val artistName: String = "Lil Qwuezy"
    override fun toString(): String {
        return "Track(title='$title', imageUrl='$imageUrl', artistName='$artistName')"
    }
}