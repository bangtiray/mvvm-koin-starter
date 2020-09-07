package com.bangtiray.core.adapter.apps

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangtiray.core.data.ConstantValue
import com.bangtiray.core.database.entity.LocalApps
import com.bangtiray.core.ui.activity.auth.databinding.ItemAppsBinding

class AppsItemAdapter :ListAdapter<LocalApps, AppsItemAdapter.ViewHolder>(DiffCallback){
    class ViewHolder(private var binding: ItemAppsBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(localApps: LocalApps){
            val c=Class.forName("${ConstantValue.packageName}${localApps.classname}")
            binding.item=localApps
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAppsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}