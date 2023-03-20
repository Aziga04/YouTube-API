package com.example.youtubeapi.ui.PlayList

import androidx.lifecycle.LiveData

import com.example.youtubeapi.network.Resource
import com.example.youtubeapi.App.Companion.repository
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.remote.model.Playlists

class PlaylistsViewModel : BaseViewModel() {

    fun getPlaylists() : LiveData<Resource<Playlists>> {
        return repository.getPlaylists()
    }

}