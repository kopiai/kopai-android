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

        setupView()
    }

    private fun setupView() {
        setupShippingForm()
        setSelectProvince()
    }

    private fun validateShippingForm(): Boolean {
        val result =
            binding?.let {
                if (it.edShippingName.text.toString().isEmpty()) {
                    it.edShippingName.error = getString(R.string.field_is_required, "Name")
                    return false
                }

                if (it.edShippingPhone.text.toString().isEmpty()) {
                    it.edShippingPhone.error = getString(R.string.field_is_required, "Phone")
                    return false
                }

                if (it.edShippingAddress.text.toString().isEmpty()) {
                    it.edShippingAddress.error = getString(R.string.field_is_required, "Address")
                    return false
                }

                if (it.edShippingPostalCode.text.toString().isEmpty()) {
                    it.edShippingPostalCode.error = getString(R.string.field_is_required, "Postal Code")
                    return false
                }

                return true
            }

        return result ?: false
    }

    private fun setupShippingForm() {
        binding?.apply {
            viewModel.getUser().observe(viewLifecycleOwner) { user ->
                when (user) {
                    is ResultState.Success -> {
                        edShippingName.setText(user.data.name)
                        edShippingPhone.setText(user.data.phone)
                        edShippingAddress.setText(user.data.address)
                    }

                    is ResultState.Error -> {
                        Toast.makeText(activity, "Failed to get user data", Toast.LENGTH_SHORT)
                            .show()
                    }

                    ResultState.Loading -> {
                        // pass
                    }
                }
            }

            btnShippingConfirm.setOnClickListener {
                if (!validateShippingForm()) {
                    return@setOnClickListener
                }

                viewModel.updateUser(
                    edShippingName.text.toString(),
                    edShippingPhone.text.toString(),
                    edShippingAddress.text.toString(),
                ).observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is ResultState.Loading -> {
                            btnShippingConfirm.isEnabled = false
                        }

                        is ResultState.Success -> {
                            btnShippingConfirm.isEnabled = true
                            Toast.makeText(activity, "Shipping details updated", Toast.LENGTH_SHORT)
                                .show()

                            Navigation.findNavController(requireView())
                                .navigate(R.id.action_navigation_checkout_shipping_to_navigation_checkout_payment)
                        }

                        is ResultState.Error -> {
                            btnShippingConfirm.isEnabled = true
                        }
                    }
                }
            }
        }
    }

    private fun setSelectProvince() {
        viewModel.getProvinces().observe(viewLifecycleOwner) {}
    }
}
