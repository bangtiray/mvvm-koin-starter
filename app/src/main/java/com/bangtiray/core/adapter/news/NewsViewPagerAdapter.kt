package com.bangtiray.core.adapter.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangtiray.core.data.ConstantValue
import com.bangtiray.core.database.entity.LocalNews
import com.bangtiray.core.ui.activity.auth.R
import com.bumptech.glide.Glide
import com.github.islamkhsh.CardSliderAdapter
import kotlinx.android.synthetic.main.item_news_slider.view.*

class NewsViewPagerAdapter(private val localNews: List<LocalNews>) :
    CardSliderAdapter<NewsViewPagerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun bindVH(holder: ViewHolder, position: Int) {
        val items = localNews[position]
        holder.itemView.run {
            Glide.with(this).load("${ConstantValue.blogImageUrl}${items.featured_image}")
                .into(backdrop)
            title.text=items.name
        }
    }

    override fun getItemCount()=localNews.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news_slider, parent, false)
        return ViewHolder(view)
    }

}