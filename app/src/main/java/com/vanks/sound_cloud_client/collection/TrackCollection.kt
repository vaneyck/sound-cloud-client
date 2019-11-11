package com.vanks.sound_cloud_client.collection

import com.vanks.sound_cloud_client.domainmodel.Track
import java.util.*
import kotlin.collections.ArrayList

class TrackCollection {
    val tracks: ArrayList<Track> = ArrayList(
        Arrays.asList(
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track(),
            Track()
        )
    )

    override fun toString(): String {
        return "TrackCollection(tracks=$tracks)"
    }
}