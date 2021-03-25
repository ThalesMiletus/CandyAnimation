package com.example.candyanimation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.candyanimation.databinding.RvItemPopularProductBinding
import com.example.candyanimation.model.Product

class PopularProductsAdapter(private val homeViewModel: HomeViewModel) :
    ListAdapter<Product, PopularProductsAdapter.ProductViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, homeViewModel)
    }

    class ProductViewHolder(view: RvItemPopularProductBinding) : RecyclerView.ViewHolder(view.root) {

        private var binding: RvItemPopularProductBinding = view

        fun bind(product: Product, homeViewModel: HomeViewModel) {
            binding.homeViewModel = homeViewModel
            binding.product = product
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: RvItemPopularProductBinding =
                    RvItemPopularProductBinding.inflate(layoutInflater, parent, false)
                return ProductViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
}