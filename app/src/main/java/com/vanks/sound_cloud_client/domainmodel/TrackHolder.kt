package com.vanks.sound_cloud_client.domainmodel

import com.vanks.sound_cloud_client.collection.TrackCollection

interface TrackHolder {
    val imageUrl: String
    fun getTrackCollection() : TrackCollection
}