package com.kopai.shinkansen.view.main.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kopai.shinkansen.R

class MainCartFragment : Fragment() {
    companion object {
        fun newInstance() = MainCartFragment()
    }

    private val viewModel: MainCartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_main_cart, container, false)
    }
}
