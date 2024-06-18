package com.kopai.shinkansen.view.checkout.shipping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kopai.shinkansen.R
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.databinding.FragmentCheckoutShippingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            btnShippingConfirm.setOnClickListener {
//                if (edShippingName.text.toString().isEmpty()) {
//                    edShippingName.error = getString(R.string.field_is_required, "Name")
//                    return@setOnClickListener
//                }

                if (edShippingPhone.text.toString().isEmpty()) {
                    edShippingPhone.error = getString(R.string.field_is_required, "Phone")
                    return@setOnClickListener
                }

                if (edShippingAddress.text.toString().isEmpty()) {
                    edShippingAddress.error = getString(R.string.field_is_required, "Address")
                    return@setOnClickListener
                }

                if (edShippingPostalCode.text.toString().isEmpty()) {
                    edShippingPostalCode.error = getString(R.string.field_is_required, "Postal Code")
                    return@setOnClickListener
                }

                viewModel.updateUser(
                    edShippingName.text.toString(),
                    edShippingPhone.text.toString(),
                    edShippingAddress.text.toString(),
                ).observe(viewLifecycleOwner) {
                    when (it) {
                        is ResultState.Loading -> {
                            btnShippingConfirm.isEnabled = false
                        }
                        is ResultState.Success -> {
                            btnShippingConfirm.isEnabled = true
                            Toast.makeText(activity, "Shipping details updated", Toast.LENGTH_SHORT)
                                .show()

                            Navigation.findNavController(view)
                                .navigate(R.id.action_navigation_checkout_shipping_to_navigation_checkout_payment)
                        }
                        is ResultState.Error -> {
                            btnShippingConfirm.isEnabled = true

                            Navigation.findNavController(view)
                                .navigate(R.id.action_navigation_checkout_shipping_to_navigation_checkout_payment)
                        }
                    }
                }
            }
        }
    }

    private fun setSelectProvince() {
        viewModel.getProvinces().observe(viewLifecycleOwner) {
        }
    }
}
