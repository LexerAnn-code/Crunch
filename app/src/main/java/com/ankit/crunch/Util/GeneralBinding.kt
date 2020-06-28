package com.ankit.crunch.Util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("setImageUrl")
fun setImages(imageView: ImageView,url:String?){
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}
//@BindingAdapter(value = ["setImageUrl"])
//fun setImagesf(imageViews: ImageView,url:String?){
//    Glide.with(imageViews.context)
//        .load(url)
//        .into(imageViews)
//}
@BindingAdapter(value = ["setAdapter"])
fun recyclerView(recyclerNews:RecyclerView,adapter: RecyclerView.Adapter<*>){
    recyclerNews.adapter=adapter
}