package com.kopai.shinkansen.view.blend.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kopai.shinkansen.R

class BlendConfigFragment : Fragment() {
    companion object {
        fun newInstance() = BlendConfigFragment()
    }

    private val viewModel: BlendConfigViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_blend_config, container, false)
    }
}
