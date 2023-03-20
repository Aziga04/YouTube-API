package com.example.youtubeapi.ui.PlayList.detail_playlist.viewmodel

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App.Companion.repository
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.remote.model.Playlists

class DetailViewModel : BaseViewModel() {
    fun getItemLists(id: String): LiveData<Playlists> {
        return repository.getItemList(id)
    }
}