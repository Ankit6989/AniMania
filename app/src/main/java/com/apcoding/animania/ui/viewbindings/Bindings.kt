package com.apcoding.animania.ui.viewbindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.load
import com.apcoding.animania.R
import com.apcoding.animania.utils.Utils
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

private val shimmer =
    Shimmer.AlphaHighlightBuilder()
        .setDuration(1200)
        .setBaseAlpha(0.6f)
        .setHighlightAlpha(0.9f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()
//A function that is called when the 'image' attribute is ued in the layout.
@BindingAdapter("image")
fun ImageView.setImage(url: String?) {
    val imageLoader = ImageLoader.Builder(context)
        .okHttpClient { Utils.httpClient }
        .build()
    if (!url.isNullOrEmpty())
        load(url, imageLoader) {
            crossfade(true)
            placeholder(ShimmerDrawable().apply {
                setShimmer(shimmer)
            })
            error(R.drawable.ic_broken_image)
            build()
        }
}