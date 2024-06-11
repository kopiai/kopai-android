package com.kopai.shinkansen.view.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kopai.shinkansen.databinding.FragmentMainProfileBinding

class MainProfileFragment : Fragment() {
    companion object {
        fun newInstance() = MainProfileFragment()
    }

    private var binding: FragmentMainProfileBinding? = null

    private val viewModel: MainProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainProfileBinding.inflate(inflater, container, false)
        return binding!!.root
    }
}
