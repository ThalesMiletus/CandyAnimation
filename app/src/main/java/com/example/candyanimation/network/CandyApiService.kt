package com.example.candyanimation.network

import com.example.candyanimation.model.Category
import com.example.candyanimation.model.Product
import com.example.candyanimation.model.ProductDetails
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val AUTH_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDMTNDNkM2OUMwRTQ0NEFDQTEzNDAxRUUxQ0JGQTRBQSIsInNjb3BlcyI6IlJPTEVfR1VFU1QiLCJpYXQiOjE1OTQ4MTkMDksImV4cCI6MTYyNjM1NTIwOX0.99jvQj3s26aSjCYf9SIqdiD4Hi-DhPcPy3Ar_e5mu48"
private const val BASE_URL = "http://212.42.196.110:6121/api/"
enum class SupportedLanguages(val value: String) { ENGLISH("en"), ARMENIAN("hy"), RUSSIAN("ru") }


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

var logging: HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(logging)

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    //.addConverterFactory(ScalarsConverterFactory.create())    //added just for first testing call
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(okHttpClient.build())
    .build()

interface CandyApiService {

    @GET("category/getTopCategoriesForMobile")
    fun getTopCategoriesAsync(
        @Header("Authorization") token: String,
        @Header("languageName") languageName: String
    ): Deferred<List<Category>>

    @GET("product/getMostPopularProducts")
    fun getMostPopularProductsAsync(
        @Header("Authorization") token: String,
        @Header("languageName") languageName: String
    ): Deferred<List<Product>>

    @GET("product/getProductById")
    fun getProductByIdAsync(
        @Header("Authorization") token: String,
        @Header("languageName") languageName: String,
        @Query("id") id: Int
    ): Deferred<ProductDetails>
}

object CandyApi {
    val retrofitService: CandyApiService by lazy { retrofit.create(CandyApiService::class.java) }
}