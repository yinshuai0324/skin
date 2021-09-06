package com.library.skin.utils

import com.library.skin.SkinManage
import com.library.skin.style.SkinStyle

class SkinUtils {
    companion object {
        /**
         * 获取当前的样式
         */
        fun <T> getCurrentStyle(): T? {
            val style = SkinManage.getCurrentStyle()

            return SkinManage.getCurrentStyle() as T
        }
    }
}