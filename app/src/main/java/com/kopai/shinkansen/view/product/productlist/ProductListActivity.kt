package com.kopai.shinkansen.view.product.productlist

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.kopai.shinkansen.R
import com.kopai.shinkansen.databinding.ActivityProductListBinding
import com.kopai.shinkansen.util.SpacesItemDecoration
import com.kopai.shinkansen.view.adapter.LoadingStateAdapter
import com.kopai.shinkansen.view.adapter.ProductPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding

    private val viewModel: ProductListViewModel by viewModels()

    private val productAdapter by lazy { ProductPagingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mtProductList)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fetchProducts()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchProducts() {
        binding.rvProductList.apply {
            adapter =
                productAdapter.withLoadStateFooter(
                    footer =
                        LoadingStateAdapter {
                            productAdapter.retry()
                        },
                )
            layoutManager = GridLayoutManager(this@ProductListActivity, 2)
            addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.item_offset)))
        }
        viewModel.productsPaging.observe(this) {
            productAdapter.submitData(lifecycle, it)
        }
    }
}
