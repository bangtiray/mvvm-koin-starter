package com.bangtiray.core.adapter.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangtiray.core.database.entity.LocalNews
import com.bangtiray.core.ui.activity.auth.databinding.ItemNewsBinding


class NewsItemAdapter : ListAdapter<LocalNews, NewsItemAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(localNews: LocalNews) {
            binding.itemNews = localNews
            binding.executePendingBindings()
            
        }
        
    }


    companion object DiffCallback : DiffUtil.ItemCallback<LocalNews>() {
        override fun areItemsTheSame(oldItem: LocalNews, newItem: LocalNews): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: LocalNews, newItem: LocalNews): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }
}
