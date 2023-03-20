package com.example.youtubeapi.remote


import com.example.youtubeapi.remote.model.Playlists
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlist")
    fun getPlaylist(
        @Query("key") apiKey : String,
        @Query("chanellId") channelId : String,
        @Query("part") part: String,

    ):Call<Playlists>

    @GET("playlistItems")
    fun getItemLists(
        @Query("part") part : String,
        @Query("playlistId") id : String,
        @Query("key") key : String,
        @Query("maxResults") maxResults:Int = 20
    ): Call<Playlists>
}