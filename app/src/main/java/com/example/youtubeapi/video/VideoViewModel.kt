package com.example.youtubeapi.video

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App.Companion.repository
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.remote.model.Playlists

class VideoViewModel : BaseViewModel() {

    fun getVideo(id: String): LiveData<Playlists> {
        return repository.getVideo(id)
    }
}