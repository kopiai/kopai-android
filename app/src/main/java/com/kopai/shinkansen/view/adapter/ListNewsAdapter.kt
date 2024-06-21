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
import com.kopai.shinkansen.data.remote.response.NewsResponseItem
import com.kopai.shinkansen.databinding.ItemBannerBinding
import com.kopai.shinkansen.databinding.ItemRowNewsBinding
import com.kopai.shinkansen.view.news.DetailNewsActivity
import com.kopai.shinkansen.view.product.productdetails.ProductDetailsActivity

class ListNewsAdapter : ListAdapter<NewsResponseItem, ListNewsAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemRowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ViewHolder(private val binding: ItemRowNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsResponseItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(item.picture)
                    .into(imgItemPhoto)
                tvItemName.text = item.newsTitle
                tvItemAuthore.text = item.newsAuthor
                tvItemDescription.text = item.description

                itemView.setOnClickListener {
                    val moveIntent =
                        Intent(itemView.context, DetailNewsActivity::class.java).run {
                            putExtra(DetailNewsActivity.EXTRA_NEWS, item)
                        }

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (it.context as ContextWrapper).baseContext as Activity,
                            Pair(imgItemPhoto, "detail_photo"),
                            Pair(tvItemName, "detail_name"),
                            Pair(tvItemAuthore, "detail_author"),
                            Pair(tvItemDescription, "detail_description"),
                        )
                    it.context.startActivity(moveIntent, optionsCompat.toBundle())
                }
            }
//            Glide.with(itemView.context)
//                .load(item.picture)
//                .into(binding.ivItemBanner)
        }
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<NewsResponseItem>() {
                override fun areItemsTheSame(
                    oldItem: NewsResponseItem,
                    newItem: NewsResponseItem,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: NewsResponseItem,
                    newItem: NewsResponseItem,
                ): Boolean {
                    return oldItem.newsID == newItem.newsID
                }
            }
    }
}