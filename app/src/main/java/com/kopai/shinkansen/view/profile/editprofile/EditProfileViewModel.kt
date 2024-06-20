package com.kopai.shinkansen.view.profile.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel
@Inject
constructor(
    private val userRepository: UserRepository,
) :
    ViewModel() {

    fun updateProfile(
        userId: Int?,
        name: String?,
        gender: String?,
        birth: String?,
        email: String?,
        phone: String?,
        address: String?,
        photo: File?,
    ) = userRepository.updateProfile(userId ?: 1, name ?: "", gender ?: "", birth ?: "", email ?: "", phone ?: "", address ?: "", photo)
}