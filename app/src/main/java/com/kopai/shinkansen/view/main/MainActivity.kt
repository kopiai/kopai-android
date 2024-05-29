package com.kopai.shinkansen.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.databinding.ActivityMainBinding
import com.kopai.shinkansen.view.ViewModelFactory
import com.kopai.shinkansen.view.adapter.StoryAdapter
import com.kopai.shinkansen.view.addstory.AddStoryActivity
import com.kopai.shinkansen.view.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    private val storyAdapter by lazy { StoryAdapter() }

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

        binding.rvMainStories.adapter = storyAdapter
        binding.fabMain.setOnClickListener {
            startActivity(Intent(this, AddStoryActivity::class.java))
        }

        fetchStories()
    }

    override fun onResume() {
        super.onResume()
        fetchStories()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_main_logout -> {
                viewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun fetchStories() {
        viewModel.getStories().observe(this) {
            when (it) {
                is ResultState.Error -> {
                    binding.rvMainStories.visibility = View.GONE
                }
                ResultState.Loading -> {
                    binding.rvMainStories.visibility = View.GONE
                }
                is ResultState.Success -> {
                    binding.rvMainStories.visibility = View.VISIBLE
                    storyAdapter.submitList(it.data.listStory)
                    if (it.data.listStory?.isEmpty() ?: false) {
                        Toast.makeText(this, "No stories found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
