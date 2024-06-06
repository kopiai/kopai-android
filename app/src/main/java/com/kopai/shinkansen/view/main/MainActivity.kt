package com.kopai.shinkansen.view.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityMainBinding
import com.kopai.shinkansen.view.ViewModelFactory
import com.kopai.shinkansen.view.adapter.LoadingStateAdapter
import com.kopai.shinkansen.view.adapter.StoryAdapter
import com.kopai.shinkansen.view.addstory.AddStoryActivity
import com.kopai.shinkansen.view.storymaps.StoryMapsActivity
import com.kopai.shinkansen.view.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    private val storyAdapter by lazy { StoryAdapter() }

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
        setSupportActionBar(binding.toolbarMain)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }
        binding.fabMain.setOnClickListener {
            startForResult.launch(Intent(this, AddStoryActivity::class.java))
        }

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

    private fun fetchStories() {
        binding.rvMainStories.adapter =
            storyAdapter.withLoadStateFooter(
                footer =
                    LoadingStateAdapter {
                        storyAdapter.retry()
                    },
            )
        viewModel.stories.observe(this) {
            storyAdapter.submitData(lifecycle, it)
        }
    }
}
