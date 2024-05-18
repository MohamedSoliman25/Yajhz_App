package com.example.yajhz.home.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.yajhz.R
import com.example.yajhz.databinding.CategoryItemBinding
import com.example.yajhz.databinding.PopularItemBinding
import com.example.yajhz.home.domain.model.BaseCategory
import com.example.yajhz.home.domain.model.Popular
import kotlin.properties.Delegates

class PopularAdapter(
    list: List<Popular> = emptyList()

) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private var list: List<Popular> by Delegates.observable(list) { _, old, new ->
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
            result.dispatchUpdatesTo(this@PopularAdapter)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            PopularItemBinding.inflate(
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

    fun updateData(newList: List<Popular>) {
        list = newList
    }

    class ViewHolder(
        private val binding: PopularItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(popular: Popular) {
            binding.apply {
                Log.d("TAG", "bindknad: "+popular)
                if (popular.is_favorite){
                    imgFav.setImageResource(R.drawable.dark_love)
                }
                else{
                    imgFav.setImageResource(R.drawable.light_love)
                }
                Glide.with(popularImg.context)
                    .load(popular.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache image
                    .into(popularImg)
                name.text = popular.name
                rate.text = "Rating : ${popular.rate}"
//                ratingBar.numStars = 5
//                ratingBar.rating = popular.rate.toFloat()
            }

        }

    }
}
