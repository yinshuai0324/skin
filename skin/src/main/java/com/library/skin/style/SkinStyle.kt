package com.library.skin.style

abstract class SkinStyle {
    open var skinTextColor: Int = 0
    open var skinLayoutBackgroundColor: Int = 0

    abstract fun skinTag(): String
}