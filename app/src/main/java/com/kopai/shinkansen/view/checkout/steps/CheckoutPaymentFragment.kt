package com.kopai.shinkansen.view.checkout.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.FragmentCheckoutPaymentBinding

class CheckoutPaymentFragment : Fragment() {
    companion object {
        fun newInstance() = CheckoutPaymentFragment()
    }

    private var binding: FragmentCheckoutPaymentBinding? = null

    private val viewModel: CheckoutPaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckoutPaymentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        setAvailablePaymentMethods()

        binding?.apply {
            btnPaymentConfirm.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_checkout_payment_to_navigation_checkout_overview),
            )
        }
    }

    private fun setAvailablePaymentMethods() {
        viewModel.getAvailablePayments().observe(viewLifecycleOwner) {}
    }
}
