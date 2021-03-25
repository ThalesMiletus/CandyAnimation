package com.example.candyanimation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.candyanimation.databinding.RvItemTopCategoryBinding
import com.example.candyanimation.model.Category

class TopCategoriesAdapter (private val homeViewModel: HomeViewModel) :
    ListAdapter<Category, TopCategoriesAdapter.CategoriesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category, homeViewModel)
    }

    class CategoriesViewHolder(view: RvItemTopCategoryBinding) : RecyclerView.ViewHolder(view.root) {

        private var binding: RvItemTopCategoryBinding = view

        fun bind(category: Category, homeViewModel: HomeViewModel) {
            binding.homeViewModel = homeViewModel
            binding.category = category
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CategoriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: RvItemTopCategoryBinding =
                    RvItemTopCategoryBinding.inflate(layoutInflater, parent, false)
                return CategoriesViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }
    }
}