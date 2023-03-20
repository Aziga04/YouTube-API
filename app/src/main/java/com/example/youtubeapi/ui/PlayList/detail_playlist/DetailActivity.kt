package com.example.youtubeapi.ui.PlayList.detail_playlist

import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.App
import com.example.youtubeapi.R
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityDetailBinding

import com.example.youtubeapi.ui.PlayList.detail_playlist.adapter.DetailAdapter
import com.example.youtubeapi.ui.PlayList.detail_playlist.viewmodel.DetailViewModel
import com.example.youtubeapi.ui.PlayLists.PlaylistsActivity.Companion.KEY_DESC
import com.example.youtubeapi.ui.PlayLists.PlaylistsActivity.Companion.KEY_TITLE
import com.example.youtubeapi.ui.PlayLists.PlaylistsActivity.Companion.KEY_VIDEO_COUNT
import com.example.youtubeapi.utils.ConnectionManager
import com.example.youtubeapi.utils.isNetworkConnected
import com.example.youtubeapi.utils.showToast
import com.example.youtubeapi.video.VideoActivity

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    private lateinit var adapter: DetailAdapter

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun initListener() {
        adapter = DetailAdapter {
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra(App.KEY, it.id)
            intent.putExtra(KEY_TITLE_DETAIL, it.snippet.title)
            intent.putExtra(KEY_DESC_DETAIL, it.snippet.description)
            startActivity(intent)
        }
        binding.toolbar.tvBack.setOnClickListener {
            finish()
        }
    }

    override fun isConnection() {
        val cld = ConnectionManager(application)
        cld.observe(this) { isConnected ->
            binding.checkInternet.root.isVisible = !isConnected
            binding.checkInternet.btnTryAgain.setOnClickListener {
                if (!isNetworkConnected()) {
                    showToast(getString(R.string.no_internet))
                } else {
                    binding.checkInternet.root.isVisible = false
                }
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        val title = intent.getStringExtra(KEY_TITLE)
        val desc = intent.getStringExtra(KEY_DESC)
        val id = intent.getStringExtra(App.KEY)
        val videoCount = intent.getStringExtra(KEY_VIDEO_COUNT)
        id?.let { _ ->
            viewModel.getItemLists(id).observe(this) {
                binding.rvItems.adapter = adapter
                adapter.setItems(it.items)
                binding.tvTitleDetail.text = title
                binding.tvDescription.text = desc
                binding.tvVideoCount.text = videoCount
            }
        }
    }

    companion object {
        const val KEY_DESC_DETAIL = "desc"
        const val KEY_TITLE_DETAIL = "title"
    }
}