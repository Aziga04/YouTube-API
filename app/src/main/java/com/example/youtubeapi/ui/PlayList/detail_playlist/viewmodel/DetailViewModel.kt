package com.example.youtubeapi.ui.PlayList.detail_playlist.viewmodel

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.remote.model.Playlists
import com.example.youtubeapi.repository.Repository

class DetailViewModel : BaseViewModel() {
    fun getItemLists(id: String): LiveData<Playlists> {
        return App().repository.getItemList(id)
    }
}