package com.example.yajhz.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.yajhz.databinding.CategoryItemBinding
import com.example.yajhz.home.domain.model.BaseCategory
import kotlin.properties.Delegates

class BaseCategoryAdapter(
    list: List<BaseCategory> = emptyList()

) : RecyclerView.Adapter<BaseCategoryAdapter.ViewHolder>() {

    private var list: List<BaseCategory> by Delegates.observable(list) { _, old, new ->
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
            result.dispatchUpdatesTo(this@BaseCategoryAdapter)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            CategoryItemBinding.inflate(
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

    fun updateData(newList: List<BaseCategory>) {
        list = newList
    }

    class ViewHolder(
        private val binding: CategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(baseCategory:BaseCategory) {
            binding.apply {
                Glide.with(categoryImg.context)
                    .load(baseCategory.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache image
                    .into(categoryImg)
                categoryName.text = baseCategory.name
            }

        }

    }
}
