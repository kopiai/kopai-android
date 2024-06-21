package com.kopai.shinkansen.view.main.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kopai.shinkansen.databinding.FragmentMainProfileBinding
import com.kopai.shinkansen.util.getImageUri
import com.kopai.shinkansen.view.authentication.login.LoginActivity
import com.kopai.shinkansen.view.authentication.preferences.PreferencesViewModel
import com.kopai.shinkansen.view.profile.editprofile.EditProfileActivity
import com.kopai.shinkansen.view.shared.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainProfileFragment : Fragment() {
    companion object {
        fun newInstance() = MainProfileFragment()
    }

    private lateinit var binding: FragmentMainProfileBinding

    private val mainProfileViewModel: MainProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEditProfile.setOnClickListener {
            startActivity(Intent(requireActivity(), EditProfileActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            mainProfileViewModel.logout()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }

    }

}
