package com.example.yajhz.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.yajhz.databinding.CategoryItemBinding
import com.example.yajhz.databinding.TrendingItemBinding
import com.example.yajhz.home.domain.model.BaseCategory
import com.example.yajhz.home.domain.model.Trending
import kotlin.properties.Delegates

class TrendingAdapter(
    list: List<Trending> = emptyList()

) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    private var list: List<Trending> by Delegates.observable(list) { _, old, new ->
        DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {
                override fun getOldListSize() = old.size

                override fun getNewListSize() = new.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    old[oldItemPosition].id == new[newItemPosition].id

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    old[oldItemPosition] == new[newItemPosition]

            }
        ).also { result ->
            result.dispatchUpdatesTo(this@TrendingAdapter)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            TrendingItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newList: List<Trending>) {
        list = newList
    }

    class ViewHolder(
        private val binding: TrendingItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trending:Trending) {
            binding.apply {
                Glide.with(trendingImg.context)
                    .load(trending.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache image
                    .into(trendingImg)
            }

        }

    }
}
