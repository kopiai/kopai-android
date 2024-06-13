package com.kopai.shinkansen.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.remote.response.StoryItem
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository,
    private val storiesRepository: StoriesRepository,
) : ViewModel() {
    val storiesPaging: LiveData<PagingData<StoryItem>> =
        storiesRepository.getStoriesPaging().cachedIn(viewModelScope)

    fun getStories() = storiesRepository.getStoriesWithLocation()

    fun getSession(): LiveData<UserPrefModel> {
        return userRepository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }
}
