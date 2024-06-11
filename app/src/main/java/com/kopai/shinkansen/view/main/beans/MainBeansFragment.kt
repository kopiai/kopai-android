package com.kopai.shinkansen.view.main.beans

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kopai.shinkansen.R

class MainBeansFragment : Fragment() {

    companion object {
        fun newInstance() = MainBeansFragment()
    }

    private val viewModel: MainBeansViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main_beans, container, false)
    }
}