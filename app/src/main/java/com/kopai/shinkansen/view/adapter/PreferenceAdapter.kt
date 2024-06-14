package com.kopai.shinkansen.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kopai.shinkansen.data.remote.response.Preferencesitem
import com.kopai.shinkansen.databinding.ItemPreferencesBinding

class PreferenceAdapter(
    private val data: MutableList<Preferencesitem> = mutableListOf(),
) : RecyclerView.Adapter<PreferenceAdapter.UserViewHolder>() {

    fun setData(data: MutableList<Preferencesitem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    class UserViewHolder(private val binding: ItemPreferencesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Preferencesitem) {
//            Glide.with(itemView)
//                .load(user.avatarUrl)
//                .circleCrop()
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(binding.imgItemPhoto);
//            binding.tvItemName.text = user.login
//            binding.tvItemLink.text = user.htmlUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}