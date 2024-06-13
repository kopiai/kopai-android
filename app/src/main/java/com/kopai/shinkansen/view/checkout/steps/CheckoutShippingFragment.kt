package com.kopai.shinkansen.view.checkout.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.FragmentCheckoutShippingBinding

class CheckoutShippingFragment : Fragment() {
    companion object {
        fun newInstance() = CheckoutShippingFragment()
    }

    private var binding: FragmentCheckoutShippingBinding? = null

    private val viewModel: CheckoutShippingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckoutShippingBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setSelectProvince()

        binding?.apply {
            btnShippingConfirm.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_checkout_shipping_to_navigation_checkout_payment),
            )
        }
    }

    private fun setSelectProvince() {
        viewModel.getProvinces().observe(viewLifecycleOwner) {
        }
    }
}
