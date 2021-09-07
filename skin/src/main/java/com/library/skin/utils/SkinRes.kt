package com.library.skin.utils

import android.graphics.Color
import android.text.TextUtils

class SkinRes {
    companion object {
        fun getColor(color: String?): Int {
            if (!TextUtils.isEmpty(color)) {
                return Color.parseColor(color)
            }
            return 0
        }
    }
}