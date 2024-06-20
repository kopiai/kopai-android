package com.kopai.shinkansen.view.blend

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import com.kopai.shinkansen.data.repository.BlendRepository
import com.kopai.shinkansen.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlendViewModel
@Inject
constructor(
    private val blendRepostory: BlendRepository,
) :
    ViewModel() {
    fun uploadBlend(
        coffeeIdOne: Int,
        coffeeIdTwo: Int,
        percentage: Int,
        weight: Int,
        roastId: Int,
        grindingId: Int,
        userId: Int,
        blendName: String,
        description: String,
    ) = blendRepostory.uploadBlend(name, email, password)
}