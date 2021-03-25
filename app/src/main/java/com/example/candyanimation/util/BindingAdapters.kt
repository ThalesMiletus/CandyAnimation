package com.example.candyanimation.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.candyanimation.R
import com.example.candyanimation.base.CATEGORY_RV_CIRCLE_ICON_ROTATION_SPEED_RESTRAIN_VALUE
import com.example.candyanimation.base.PRODUCTS_RV_ITEM_IMAGE_CORNER_RADIUS

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .transform(CenterCrop(), RoundedCorners(PRODUCTS_RV_ITEM_IMAGE_CORNER_RADIUS))
            .into(imgView)
    }
}

@BindingAdapter("circleImageUrl")
fun bindCircleImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .circleCrop()
            .into(imgView)
    }
}

@BindingAdapter("productPriceDramKg")
fun bindPriceDramKg(textView: TextView, price: Int?) {
    textView.text =
        java.lang.String.format(textView.context.getString(R.string.price_dram_kg_formatter), price)
}

@BindingAdapter("productPriceAMD")
fun bindPriceAmd(textView: TextView, price: Int?) {
    textView.text =
        java.lang.String.format(textView.context.getString(R.string.price_amd_formatter), price)
}

@BindingAdapter("shelfLifeMonths")
fun bindShelfLifeMonths(textView: TextView, months: Int?) {
    textView.text =
        java.lang.String.format(
            textView.context.getString(R.string.shelf_life_months_formatter),
            months
        )
}

@BindingAdapter("productsCount")
fun bindProductsCount(textView: TextView, productsCount: Int?) {
    textView.text =
        java.lang.String.format(
            textView.context.getString(R.string.shelf_life_products_count_formatter),
            productsCount
        )
}

@BindingAdapter("rotation")
fun bindRotation(imgView: ImageView, rotation: Int?) {
    if (rotation != null) {
        imgView.rotation = rotation.toFloat()/CATEGORY_RV_CIRCLE_ICON_ROTATION_SPEED_RESTRAIN_VALUE
    }
}