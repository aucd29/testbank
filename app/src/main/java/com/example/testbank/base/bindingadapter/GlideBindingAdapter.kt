package com.example.testbank.base.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.testbank.R
import com.example.testbank.base.GlideApp
import com.example.testbank.base.extension.dpToPx
import java.util.*

@BindingAdapter("imageUri", "imageRadius", requireAll = false)
fun ImageView.setImageUri(uri: String?, radiusDp: Int?) {
    if (uri.isNullOrEmpty().not()) {
        val newUrl = uri!!.toLowerCase(Locale.getDefault())
        if (!(newUrl.startsWith("https") || newUrl.startsWith("http"))) {
            setImageId(uri)
        } else {
            val glide  = GlideApp.with(this.context).load(uri)
                .thumbnail(
                    GlideApp.with(context)
                        .load(R.drawable.shape_place_holder).error(
                            GlideApp.with(this.context).load(uri)
                        )
                )
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

            radiusDp?.let {
                glide.transform(CenterCrop(), RoundedCorners(context.dpToPx(radiusDp)))
            }

            glide.into(this)
        }
    } else {
        setImageDrawable(null)
    }
}

@BindingAdapter("imageId", requireAll = false)
fun ImageView.setImageId(imageId: String?) {
    val imagesId = imageId?.let {
        context.resources.getIdentifier(it, "drawable", context.packageName)
    }

    imagesId?.let { id ->
        if (id != -1) {
            GlideApp.with(this.context)
                .load(id)
                .centerInside()
                .into(this)
        }
    } ?: setImageDrawable(null)
}
