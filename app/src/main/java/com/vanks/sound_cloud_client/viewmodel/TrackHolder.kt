package com.vanks.sound_cloud_client.viewmodel

import com.vanks.sound_cloud_client.collection.TrackCollection

interface TrackHolder {
    var id: Int
    var imageUrl: String
    var title: String
    fun getTrackCollection(): TrackCollection
    fun getCreator () : String
}