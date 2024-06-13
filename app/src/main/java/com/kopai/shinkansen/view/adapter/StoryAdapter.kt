package com.kopai.shinkansen.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kopai.shinkansen.data.remote.response.StoryItem
import com.kopai.shinkansen.databinding.ItemStoryBinding
import com.kopai.shinkansen.view.detailproduct.DetailProductActivity

class StoryAdapter :
    PagingDataAdapter<StoryItem, StoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: StoryItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(story.photoUrl)
                    .into(ivItemPhoto)
                tvItemName.text = story.name
                tvItemDescription.text = story.description

                itemView.setOnClickListener {
                    val moveIntent =
                        Intent(itemView.context, DetailProductActivity::class.java).run {
                            putExtra(DetailProductActivity.EXTRA_STORY, story)
                        }

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            it.context as Activity,
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
