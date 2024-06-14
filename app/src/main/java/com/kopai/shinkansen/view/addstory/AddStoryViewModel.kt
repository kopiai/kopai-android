package com.kopai.shinkansen.view.addstory

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.StoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel
    @Inject
    constructor(
        private val storiesRepository: StoriesRepository,
    ) : ViewModel() {
        fun uploadStory(
            imageStory: File,
            description: String,
        ) = storiesRepository.uploadStory(imageStory, description)
    }
