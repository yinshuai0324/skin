package com.library.skin.callback

import android.view.View

interface SkinStyleHandler {
    /**
     * 改变View的样式
     */
    fun styleHandler(view: View, styleConfig: Any?)
}