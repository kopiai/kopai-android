package com.kopai.shinkansen.view.storymaps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.remote.response.StoriesResponse
import com.kopai.shinkansen.data.repository.StoriesRepository

class StoryMapsViewModel(
    private val storiesRepository: StoriesRepository,
) : ViewModel() {
    fun getStoriesWithLocation(): LiveData<ResultState<StoriesResponse>> {
        return storiesRepository.getStoriesWithLocation()
    }
}
