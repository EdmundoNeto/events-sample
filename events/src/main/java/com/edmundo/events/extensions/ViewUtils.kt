package com.edmundo.events.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import  androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.edmundo.events.R


@BindingAdapter("imageUrl")
fun ImageView.setImage(url: String?) =
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_broken_image_black_24dp)
            .fitCenter()
            .into(this)
            .let { Unit }

fun Long.toFormattedDate(format: String): String {

    return try {
        val currentDate = Date(this)

        val dateFormat = SimpleDateFormat(format, Locale.getDefault())

        dateFormat.format(currentDate)

    } catch (e: Exception) {
        ""
    }
}

fun AppCompatActivity.bindingContentView(layout: Int): ViewDataBinding =
    DataBindingUtil.setContentView(this, layout)

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, expression: (T?) -> Unit) {
    liveData.observe(this, Observer(expression))
}

fun String.isValidEmail() = this.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()