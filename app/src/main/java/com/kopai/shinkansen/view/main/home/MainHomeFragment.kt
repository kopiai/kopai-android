package com.kopai.shinkansen.view.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.databinding.FragmentMainHomeBinding
import com.kopai.shinkansen.util.SpacesItemDecoration
import com.kopai.shinkansen.view.adapter.BannerAdapter
import com.kopai.shinkansen.view.adapter.LoadingStateAdapter
import com.kopai.shinkansen.view.adapter.StoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHomeFragment : Fragment() {
    companion object {
        fun newInstance() = MainHomeFragment()
    }

    private var binding: FragmentMainHomeBinding? = null

    private val viewModel: MainHomeViewModel by viewModels()

    private val storyAdapter by lazy { StoryAdapter() }
    private val bannerAdapter by lazy { BannerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        fetchBanner()
        fetchStories()
    }

//    val startForResult =
//        registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult(),
//        ) { result: ActivityResult ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                storyAdapter.refresh()
//            }
//        }

    private fun fetchBanner() {
        binding!!.rvMainBanner.apply {
            adapter = bannerAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.getStories().observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> {
                    Toast.makeText(activity, it.error, Toast.LENGTH_SHORT).show()
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
        binding!!.rvMainStories.apply {
            adapter =
                storyAdapter.withLoadStateFooter(
                    footer =
                        LoadingStateAdapter {
                            storyAdapter.retry()
                        },
                )
            layoutManager = GridLayoutManager(activity, 2)
            addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.item_offset)))
        }
        viewModel.storiesPaging.observe(viewLifecycleOwner) {
            storyAdapter.submitData(lifecycle, it)
        }
    }
}