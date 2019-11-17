package com.vanks.sound_cloud_client.util

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

fun buildMediaSource(uri: Uri, context: Context?): MediaSource {
    val dataSourceFactory = DefaultDataSourceFactory(context, "exoplayer-codelab")
    return ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(uri)
}