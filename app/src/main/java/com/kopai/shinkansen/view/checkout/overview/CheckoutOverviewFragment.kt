package com.kopai.shinkansen.view.checkout.overview

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.remote.response.OrderItemResponse
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.databinding.FragmentCheckoutOverviewBinding
import com.kopai.shinkansen.util.Constant
import com.kopai.shinkansen.util.Helper
import com.kopai.shinkansen.view.checkout.CheckoutInvoiceActivity
import com.kopai.shinkansen.view.product.productdetails.ProductDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutOverviewFragment : Fragment() {
    companion object {
        fun newInstance() = CheckoutOverviewFragment()
    }

    private var binding: FragmentCheckoutOverviewBinding? = null

    private val viewModel: CheckoutOverviewViewModel by viewModels()

    private var productExtra: ProductItem? = null

    private var productQuantity: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCheckoutOverviewBinding.inflate(inflater, container, false)

        productExtra =
            if (Build.VERSION.SDK_INT >= 33) {
                activity?.intent?.getParcelableExtra<ProductItem>(
                    ProductDetailsActivity.EXTRA_PRODUCT,
                    ProductItem::class.java,
                )
            } else {
                @Suppress("DEPRECATION")
                activity?.intent?.getParcelableExtra<ProductItem>(
                    ProductDetailsActivity.EXTRA_PRODUCT,
                )
            }

        return binding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupOrderItem()
        setupConfirmCheckout()
    }

    private fun updateTotalPrice() {
        binding?.apply {
            lCheckoutOverviewProduct.tvProductQuantity.text = productQuantity.toString()

            val totalPrice = (productExtra?.price?.toDouble() ?: 0.0) * productQuantity
            lCheckoutOverviewBottomSheet.tvOverviewTotal.text = Helper.rupiah(totalPrice)
            lCheckoutOverviewBottomSheet.tvOverviewSubtotal.text =
                Helper.rupiah(
                    totalPrice + Constant.FIXED_SHIPPING_COST,
                )
        }
    }

    private fun setupOrderItem() {
        binding?.apply {
            lCheckoutOverviewProduct.tvProductName.text = productExtra?.productName
            lCheckoutOverviewProduct.tvProductPrice.text =
                Helper.rupiah(productExtra?.price?.toDouble() ?: 0.0)
            lCheckoutOverviewProduct.btnProductDecrease.setOnClickListener {
                if (productQuantity > 1) {
                    productQuantity--
                    updateTotalPrice()
                }
            }
            lCheckoutOverviewProduct.btnProductIncrease.setOnClickListener {
                productQuantity++
                updateTotalPrice()
            }
        }
    }

    private fun setupConfirmCheckout() {
        binding?.lCheckoutOverviewBottomSheet?.apply {
            updateTotalPrice()
            btnOverviewConfirm.setOnClickListener {
                val createOrderParam =
                    listOf(
                        OrderItemResponse(
                            productId = productExtra!!.productId,
                            quantity = productQuantity,
                            totalPrice = productExtra!!.price.toDouble(),
                        ),
                    )
                viewModel.createOrder(createOrderParam).observe(viewLifecycleOwner) {
                    when (it) {
                        is ResultState.Loading -> {
                            btnOverviewConfirm.isEnabled = false
                        }

                        is ResultState.Success -> {
                            btnOverviewConfirm.isEnabled = true

                            Toast.makeText(
                                activity,
                                "Order created successfully",
                                Toast.LENGTH_SHORT,
                            ).show()

                            activity?.startActivity(
                                Intent(
                                    activity,
                                    CheckoutInvoiceActivity::class.java,
                                ),
                            )
                            activity?.finish()
                        }

                        is ResultState.Error -> {
                            btnOverviewConfirm.isEnabled = true
                        }
                    }
                }
            }
        }
    }
}
