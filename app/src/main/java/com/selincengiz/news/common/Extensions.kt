package com.selincengiz.news.common

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.selincengiz.news.R


import java.util.Calendar

object Extensions {
    fun ImageView.loadUrl(url: String?) {

        Glide.with(this.context).load(url).placeholder(R.drawable.default_news)
            .error(R.drawable.default_news).into(this)

    }

    fun ImageView.loadUrl(url: Uri?) {

        Glide.with(this.context).load(url).circleCrop().placeholder(R.drawable.default_news)
            .error(R.drawable.default_news).into(this)

    }

    fun ImageView.loadUrl(url: String?, requestOptions: RequestOptions) {

        Glide.with(this.context).load(url).apply(requestOptions).into(this)

    }


}