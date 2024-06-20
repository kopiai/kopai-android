package com.kopai.shinkansen.view.blend.bean

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.FragmentBlendBeanBinding

class BlendBeanFragment : Fragment() {
    companion object {
        fun newInstance() = BlendBeanFragment()
    }

    private var binding: FragmentBlendBeanBinding? = null

    private val viewModel: BlendBeanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBlendBeanBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btnBlendSingle.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_blend_bean_to_navigation_blend_config),
            )
        }
    }
}
