package com.selincengiz.news.common

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


import java.util.Calendar

object Extensions {
    fun ImageView.loadUrl(url: String?) {

        Glide.with(this.context).load(url).into(this)

    }

    fun ImageView.loadUrl(url: Uri?) {

        Glide.with(this.context).load(url).circleCrop().into(this)

    }

    fun ImageView.loadUrl(url: String?, requestOptions: RequestOptions) {

        Glide.with(this.context).load(url).apply(requestOptions).into(this)

    }


}