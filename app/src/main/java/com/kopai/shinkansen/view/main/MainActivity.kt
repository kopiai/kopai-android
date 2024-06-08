package com.kopai.shinkansen.view.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.databinding.ActivityMainBinding
import com.kopai.shinkansen.util.SpacesItemDecoration
import com.kopai.shinkansen.view.ViewModelFactory
import com.kopai.shinkansen.view.adapter.BannerAdapter
import com.kopai.shinkansen.view.adapter.LoadingStateAdapter
import com.kopai.shinkansen.view.adapter.StoryAdapter
import com.kopai.shinkansen.view.checkout.CheckoutActivity
import com.kopai.shinkansen.view.storymaps.StoryMapsActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    private val storyAdapter by lazy { StoryAdapter() }
    private val bannerAdapter by lazy { BannerAdapter() }

    val startForResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                storyAdapter.refresh()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel.getSession().observe(this) { user ->
//            if (!user.isLogin) {
//                startActivity(Intent(this, WelcomeActivity::class.java))
//                finish()
//            }
//        }

        binding.btnMainPreferences.setOnClickListener {
            startForResult.launch(Intent(this, CheckoutActivity::class.java))
        }

        fetchBanner()
        fetchStories()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_main_maps -> {
                startActivity(Intent(this, StoryMapsActivity::class.java))
                true
            }
            R.id.action_main_logout -> {
                viewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun fetchBanner() {
        binding.rvMainBanner.apply {
            adapter = bannerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.getStories().observe(this) {
            when (it) {
                is ResultState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                ResultState.Loading -> {
                    Log.d("MainActivity", "Loading fetch banner")
                }
                is ResultState.Success -> {
                    bannerAdapter.submitList(it.data.listStory)
                }
            }
        }
    }

    private fun fetchStories() {
        binding.rvMainStories.apply {
            adapter =
                storyAdapter.withLoadStateFooter(
                    footer =
                        LoadingStateAdapter {
                            storyAdapter.retry()
                        },
                )
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.item_offset)))
        }
        viewModel.storiesPaging.observe(this) {
            storyAdapter.submitData(lifecycle, it)
        }
    }
}
