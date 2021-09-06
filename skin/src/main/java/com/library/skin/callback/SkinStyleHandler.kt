package com.library.skin.callback

import android.view.View
import com.library.skin.style.SkinStyle

interface SkinStyleHandler {
    /**
     * 改变View的样式
     */
    fun styleHandler(view: View, style: SkinStyle?)
}