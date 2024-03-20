package com.selincengiz.news.domain.entities

import android.graphics.drawable.Drawable

data class Countries(
    val key :String,
    var isSelected:Boolean,
    val drawable: Drawable?
)
