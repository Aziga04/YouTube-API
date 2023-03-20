package com.example.youtubeapi.ui.PlayList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.BuildConfig
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.remote.ApiService
import com.example.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.youtubeapi.remote.model.Playlists

class PlaylistViewModel : BaseViewModel() {
    private val apiService : ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlist():LiveData<Playlists>{
        return getPlaylist()
    }

    private fun getPlaylist(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()

        apiService.getPlaylist(BuildConfig.API_KEY,"UCWOA1ZGywLbqmigxE4Qlvuw","snippet,contentDetails")
            .enqueue(object : Callback<Playlists>{
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful){
                        data.value = response.body()
                    }

                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                   print(t.message)
                }

            })
        return data

    }
}