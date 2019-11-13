package com.vanks.sound_cloud_client.collection

import com.vanks.sound_cloud_client.viewmodel.Track
import kotlin.collections.ArrayList

class TrackCollection {
    val tracks: ArrayList<Track> = ArrayList()

    override fun toString(): String {
        return "TrackCollection(tracks=$tracks)"
    }
}