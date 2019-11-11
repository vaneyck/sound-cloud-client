package com.vanks.sound_cloud_client.collection

import com.vanks.sound_cloud_client.domainmodel.Album
import java.util.*
import kotlin.collections.ArrayList

class AlbumCollection {
    val albums: ArrayList<Album> = ArrayList(
        Arrays.asList(
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album(),
            Album()
        )
    )

    override fun toString(): String {
        return "AlbumCollection(albums=$albums)"
    }
}