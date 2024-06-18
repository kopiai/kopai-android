package com.kopai.shinkansen.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kopai.shinkansen.data.remote.response.NewsResponseItem
import com.kopai.shinkansen.databinding.ItemBannerBinding

class NewsAdapter : ListAdapter<NewsResponseItem, NewsAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsResponseItem) {
            Glide.with(itemView.context)
                .load(item.picture)
                .into(binding.ivItemBanner)
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
