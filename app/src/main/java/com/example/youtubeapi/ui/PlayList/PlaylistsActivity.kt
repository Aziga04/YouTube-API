package com.example.youtubeapi.ui.PlayList


import DetailActivity
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.App
import com.example.youtubeapi.App.Companion.KEY
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.PlaylistMainBinding
import com.example.youtubeapi.ui.PlayList.adapter.PlaylistsAdapter
import com.example.youtubeapi.utils.ConnectionManager
import com.example.youtubeapi.utils.isNetworkConnected
import com.example.youtubeapi.utils.showToast

class PlaylistsActivity : BaseActivity<PlaylistMainBinding, PlaylistViewModel>() {

    private lateinit var adapter : PlaylistsAdapter

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(): PlaylistMainBinding {
        return PlaylistMainBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        super.initListener()
        adapter = PlaylistsAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(App.KEY, it.id)
            startActivity(intent)
        }
    }
    override fun initViewModel() {
        super.initViewModel()
        viewModel.playlist().observe(this) {
            binding.recyclerView.adapter = adapter
            adapter.setPlaylists(it.items)
        }
    }

    override fun isConnection() {
        super.isConnection()
        val connectionManager = ConnectionManager(application)
        connectionManager.observe(this) {
            if (!it) {
                binding.noInternet.isVisible = true
                binding.include.btnTryAgain.setOnClickListener {
                    if (!isNetworkConnected()) {
                        showToast(getString(R.string.no_internet))
                    } else {
                        binding.noInternet.isVisible = false
                    }
                }
            } else {
                initViewModel()
            }
        }
    }
}