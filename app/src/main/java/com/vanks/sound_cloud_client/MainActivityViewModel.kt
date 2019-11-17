package com.vanks.sound_cloud_client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var inPlayerMode: LiveData<Boolean> = MutableLiveData<Boolean>()
}