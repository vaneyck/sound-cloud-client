package com.vanks.sound_cloud_client.ui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vanks.sound_cloud_client.collection.TrackCollection
import com.vanks.sound_cloud_client.viewmodel.TrackHolder

class CollectionViewModel : ViewModel() {
    var trackHolder: LiveData<TrackHolder> = MutableLiveData<TrackHolder>()
    var trackCollection: LiveData<TrackCollection> = MutableLiveData<TrackCollection>()
}