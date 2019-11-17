package com.vanks.sound_cloud_client.viewmodel

import com.vanks.sound_cloud_client.util.IMAGE_URL
import com.vanks.sound_cloud_client.util.SOUND_CLOUD_CLIENT_ID
import com.vanks.sound_cloud_client.util.getRandomInt
import com.vanks.sound_cloud_client.webservice.SoundCloudTrack

class Track {
    var id: Int = 0
    var title: String = "Song title that is very long"
    var imageUrl: String = "https://picsum.photos/200/200?id=" + getRandomInt()
    var artistName: String = "Lil Qwuezy"
    var streamUrl: String = ""
    var playbackCount: Int = 0

    var soundCloudTrack: SoundCloudTrack

    constructor(soundCloudTrack: SoundCloudTrack) {
        this.soundCloudTrack = soundCloudTrack

        this.id = soundCloudTrack.id
        this.title = soundCloudTrack.title
        this.imageUrl = (soundCloudTrack.artwork_url?: IMAGE_URL).replace("large", "t300x300")
        this.artistName = soundCloudTrack.user.username
        this.streamUrl = soundCloudTrack.stream_url + "?client_id=" + SOUND_CLOUD_CLIENT_ID
        this.playbackCount = soundCloudTrack.playback_count
    }

    fun getDisplayCount () : String {
        if (playbackCount == 0) {
            return ""
        } else {
            return "$playbackCount plays"
        }
    }

    override fun toString(): String {
        return "Track(title='$title', imageUrl='$imageUrl', artistName='$artistName')"
    }
}