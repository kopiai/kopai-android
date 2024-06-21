package com.kopai.shinkansen.view.checkout.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.FragmentCheckoutPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            btnPaymentConfirm.setOnClickListener {
                if (rgPaymentMethod.checkedRadioButtonId == -1) {
                    val lastChildPos: Int = rgPaymentMethod.childCount - 1
                    (
                        rgPaymentMethod.getChildAt(
                            lastChildPos,
                        ) as RadioButton
                    ).error = getString(R.string.field_is_required, "Payment Method")

                    return@setOnClickListener
                }

                Navigation.findNavController(view)
                    .navigate(R.id.action_navigation_checkout_payment_to_navigation_checkout_overview)
            }
        }
    }

    private fun setAvailablePaymentMethods() {
        binding?.apply {
            rgPaymentMethod.removeAllViews()

            val paymentMethods = resources.getStringArray(R.array.payment_methods)
            paymentMethods.map {
                val radioButton = RadioButton(context)
                radioButton.text = it
                rgPaymentMethod.addView(radioButton)
            }
        }
    }
}
