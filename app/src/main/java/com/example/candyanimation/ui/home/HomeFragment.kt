package com.example.candyanimation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.candyanimation.R
import com.example.candyanimation.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var topCategoriesAdapter: TopCategoriesAdapter
    private lateinit var popularProductsAdapter: PopularProductsAdapter

    /******************************** LIFE CYCLE METHODS ******************************************/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initCategoriesRv()
        initProductsRv()
        initObservers()
    }

    /***************************** VIEW PARAM INITIALIZATION **************************************/
    private fun initCategoriesRv() {
        topCategoriesAdapter = TopCategoriesAdapter(homeViewModel)
        binding.rvCategories.adapter = topCategoriesAdapter

        binding.rvCategories.addOnScrollListener(
            (object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    homeViewModel.rotation.value = homeViewModel.rotation.value?.plus(dx)
                    topCategoriesAdapter.notifyDataSetChanged()
                }
            })
        )
    }

    private fun initProductsRv() {
        popularProductsAdapter = PopularProductsAdapter(homeViewModel)
        binding.rvProducts.adapter = popularProductsAdapter
    }

    private fun initObservers() {
        homeViewModel.selectedPopularProduct.observe(viewLifecycleOwner, {
            it?.let {
                homeViewModel.selectedPopularProduct.postValue(null)
                Navigation.findNavController(binding.root)
                    .navigate(HomeFragmentDirections.actionNavigationHomeToProductDetailsFragment(id))
            }
        })

        homeViewModel.topCategories.observe(viewLifecycleOwner, {
            it?.let { topCategoriesAdapter.submitList(it) }
        })

        homeViewModel.products.observe(viewLifecycleOwner, {
            it?.let { popularProductsAdapter.submitList(it) }
        })
    }
}