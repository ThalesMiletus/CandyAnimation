package com.example.candyanimation.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.candyanimation.R
import com.example.candyanimation.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment() {

    private val args: ProductDetailsFragmentArgs by navArgs()
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var binding: FragmentProductDetailsBinding


    /******************************** LIFE CYCLE METHODS ******************************************/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_details, container, false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        binding.productDetailsViewModel = productDetailsViewModel

        initViews()
        loadDataFromServer()
    }

    /***************************** VIEW PARAM INITIALIZATION **************************************/
    private fun initViews() {
        binding.ivArrowClose.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }

    /******************************** LOGICAL METHODS *********************************************/
    private fun loadDataFromServer() {
        productDetailsViewModel.getProductById(args.productId)
    }
}