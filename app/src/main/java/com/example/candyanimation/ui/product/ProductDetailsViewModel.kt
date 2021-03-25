package com.example.candyanimation.ui.product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.candyanimation.base.TAG_LOG
import com.example.candyanimation.model.ProductDetails
import com.example.candyanimation.network.AUTH_KEY
import com.example.candyanimation.network.CandyApi
import com.example.candyanimation.network.SupportedLanguages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {
    val loadedPopularProduct = MutableLiveData<ProductDetails>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getProductById(id: Int) {

        //todo fix ERR 500 bug
        coroutineScope.launch {
            val getPropertiesDeferred = CandyApi.retrofitService.getProductByIdAsync(
                AUTH_KEY,
                SupportedLanguages.ENGLISH.value,
                id
            )
            try {
                val listResult = getPropertiesDeferred.await()
                loadedPopularProduct.value = listResult
            } catch (e: Exception) {
                val productDetails = ProductDetails(
                    2,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbk0YDyfWSiZJNMehf3KvJrbLPvVyUTyM2nt0TGwCGjJB06-WDNg",
                    "Biscuits with chocolate",
                    "Biscuits",
                    "Antoine's cookies are prepared and baked in our kitchen. Every ingredient is measured precisely down to the gram to make the most delicious cookies in the Bay Area.",
                    "Ingredients",
                    "Sugar, glucose syrup, starch, flavoring, food coloring, gelatin",
                    "Shelf life",
                    12,
                    2500
                )
                loadedPopularProduct.postValue(productDetails)
                Log.e(TAG_LOG, "ProductDetailsViewModel getProductById onFailure Exception e : $e")
            }
        }
    }
}