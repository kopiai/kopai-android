package com.kopai.shinkansen.view.checkout.steps

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kopai.shinkansen.databinding.FragmentCheckoutOverviewBinding
import com.kopai.shinkansen.view.checkout.CheckoutInvoiceActivity

class CheckoutOverviewFragment : Fragment() {
    companion object {
        fun newInstance() = CheckoutOverviewFragment()
    }

    private var binding: FragmentCheckoutOverviewBinding? = null

    private val viewModel: CheckoutOverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckoutOverviewBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lCheckoutOverviewBottomSheet.btnOverviewConfirm.setOnClickListener {
                view.context.startActivity(Intent(view.context, CheckoutInvoiceActivity::class.java))
            }
        }
    }
}
