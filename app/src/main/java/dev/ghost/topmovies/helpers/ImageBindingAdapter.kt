package dev.ghost.topmovies.helpers

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import dev.ghost.topmovies.R


// Picasso binding adapter for images loading.
@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    if (url != "") {
        Picasso.with(imageView.context).load(url)
            .placeholder(R.drawable.icon_placeholder)
            .error(R.drawable.icon_placeholder)
            .into(imageView)
    }
}
