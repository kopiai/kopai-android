package com.kopai.shinkansen.view.adapter

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.databinding.ItemProductBinding
import com.kopai.shinkansen.view.product.productdetails.ProductDetailsActivity

class ProductAdapter :
    ListAdapter<ProductItem, ProductAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(product.photo)
                    .into(ivItemPhoto)
                tvItemName.text = product.productName
                tvItemDescription.text = product.description

                itemView.setOnClickListener {
                    val moveIntent =
                        Intent(itemView.context, ProductDetailsActivity::class.java).run {
                            putExtra(ProductDetailsActivity.EXTRA_STORY, product)
                        }

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (it.context as ContextWrapper).baseContext as Activity,
                            Pair(ivItemPhoto, "detail_photo"),
                            Pair(tvItemName, "detail_name"),
                            Pair(tvItemDescription, "detail_description"),
                        )
                    it.context.startActivity(moveIntent, optionsCompat.toBundle())
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ProductItem>() {
                override fun areItemsTheSame(
                    oldItem: ProductItem,
                    newItem: ProductItem,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: ProductItem,
                    newItem: ProductItem,
                ): Boolean {
                    return oldItem.productId == newItem.productId
                }
            }
    }
}
