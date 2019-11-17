package com.vanks.sound_cloud_client.util;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.vanks.sound_cloud_client.repository.MusicRepository;

public class Reusable {
    public static MusicRepository musicRepository = new MusicRepository();
    public static SimpleExoPlayer player = null;
}
