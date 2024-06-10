package com.kopai.shinkansen.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kopai.shinkansen.data.remote.response.StoryItem
import com.kopai.shinkansen.databinding.ItemBannerBinding

class BannerAdapter : ListAdapter<StoryItem, BannerAdapter.ViewHolder>(DIFF_CALLBACK) {
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
        fun bind(item: StoryItem) {
            Glide.with(itemView.context)
                .load(item.photoUrl)
                .into(binding.ivItemBanner)
        }
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<StoryItem>() {
                override fun areItemsTheSame(
                    oldItem: StoryItem,
                    newItem: StoryItem,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: StoryItem,
                    newItem: StoryItem,
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}
