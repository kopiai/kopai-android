package com.kopai.shinkansen.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import com.kopai.shinkansen.di.Injection
import com.kopai.shinkansen.view.addstory.AddStoryViewModel
import com.kopai.shinkansen.view.login.LoginViewModel
import com.kopai.shinkansen.view.main.MainViewModel
import com.kopai.shinkansen.view.signup.SignupViewModel
import com.kopai.shinkansen.view.storymaps.StoryMapsViewModel

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val storiesRepository: StoriesRepository,
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository, storiesRepository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository, storiesRepository) as T
            }

            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(storiesRepository) as T
            }

            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
                AddStoryViewModel(storiesRepository) as T
            }

            modelClass.isAssignableFrom(StoryMapsViewModel::class.java) -> {
                StoryMapsViewModel(storiesRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (instance == null) {
                synchronized(ViewModelFactory::class.java) {
                    instance =
                        ViewModelFactory(
                            Injection.provideRepository(context),
                            Injection.provideStoriesRepository(context),
                        )
                }
            }
            return instance as ViewModelFactory
        }

        @JvmStatic
        fun clearInstance() {
            StoriesRepository.clearInstance()
            instance = null
        }
    }
}
