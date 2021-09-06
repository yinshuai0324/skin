package com.library.skin.utils

import android.app.Application
import androidx.core.content.ContextCompat

object Attr {
    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }


    fun getColor(res: Int): Int {
        return ContextCompat.getColor(application, res)
    }
}