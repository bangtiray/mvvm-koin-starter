package com.bangtiray.core.adapter.apps

import androidx.recyclerview.widget.DiffUtil
import com.bangtiray.core.database.entity.LocalApps

object DiffCallback : DiffUtil.ItemCallback<LocalApps>() {
    override fun areItemsTheSame(oldItem: LocalApps, newItem: LocalApps): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LocalApps, newItem: LocalApps): Boolean {
        return oldItem.id== newItem.id
    }

}
