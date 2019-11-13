package com.vanks.sound_cloud_client.viewmodel

import com.vanks.sound_cloud_client.collection.TrackCollection

interface TrackHolder {
    var imageUrl: String
    var title: String
    fun getTrackCollection(): TrackCollection
}