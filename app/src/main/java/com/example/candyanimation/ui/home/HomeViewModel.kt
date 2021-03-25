package com.example.candyanimation.ui.home

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.candyanimation.base.TAG_LOG
import com.example.candyanimation.model.Category
import com.example.candyanimation.model.Product
import com.example.candyanimation.network.AUTH_KEY
import com.example.candyanimation.network.CandyApi
import com.example.candyanimation.network.SupportedLanguages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val topCategories = MutableLiveData<List<Category>>()
    val products = MutableLiveData<List<Product>>()
    val selectedPopularProduct = MutableLiveData<Int>()
    val rotation = MutableLiveData(0)

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getTopCategories()
        getMostPopularProducts()
    }

    fun onProductItemClicked(id: Int) {
        selectedPopularProduct.value = id
    }

    //todo fix ERR 500 bug
    private fun getTopCategories() {
        coroutineScope.launch {
            val getPropertiesDeferred = CandyApi.retrofitService.getTopCategoriesAsync(
                AUTH_KEY,
                SupportedLanguages.ENGLISH.value
            )
            try {
                val listResult = getPropertiesDeferred.await()
                topCategories.value = listResult
            } catch (e: Exception) {
                val arrayList = ArrayList<Category>()
                for (i in 0 until 10) {
                    val category = Category(
                        i,
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbk0YDyfWSiZJNMehf3KvJrbLPvVyUTyM2nt0TGwCGjJB06-WDNg",
                        "Cakes",
                        Color.BLUE,
                        75
                    )
                    arrayList.add(category)
                }
                topCategories.postValue(arrayList)
                Log.e(TAG_LOG, "HomeViewModel getTopCategories Exception e : $e")
            }
        }
    }

    //todo fix ERR 500 bug
    private fun getMostPopularProducts() {
        coroutineScope.launch {
            val getPropertiesDeferred = CandyApi.retrofitService.getMostPopularProductsAsync(
                AUTH_KEY,
                SupportedLanguages.ENGLISH.value
            )
            try {
                val listResult = getPropertiesDeferred.await()
                products.value = listResult
            } catch (e: Exception) {
                val arrayList = ArrayList<Product>()
                for (i in 0 until 10) {
                    val product = Product(
                        i,
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbk0YDyfWSiZJNMehf3KvJrbLPvVyUTyM2nt0TGwCGjJB06-WDNg",
                        "Jelly Gummy ",
                        2500
                    )
                    arrayList.add(product)
                }
                products.postValue(arrayList)
                Log.e(
                    TAG_LOG,
                    "HomeViewModel getMostPopularProducts Exception e : $e"
                )
                products.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}