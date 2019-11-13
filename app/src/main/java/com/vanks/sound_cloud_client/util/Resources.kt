package com.vanks.sound_cloud_client.util

import com.vanks.sound_cloud_client.repository.MusicRepository

class Resources {
    companion object {
        @JvmStatic
        var musicRep: MusicRepository = MusicRepository()
    }
}
