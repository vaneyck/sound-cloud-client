package com.vanks.sound_cloud_client.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vanks.sound_cloud_client.viewmodel.Track

class PlayerViewModel : ViewModel() {
    var track: LiveData<Track> = MutableLiveData<Track>()
}