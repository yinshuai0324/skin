package com.library.skin.utils

import android.content.Context
import android.content.SharedPreferences

object SpUtils {
    private lateinit var sp: SharedPreferences

    /**
     * 初始化
     */
    fun init(context: Context) {
        sp = context.getSharedPreferences("SkinSpData", Context.MODE_PRIVATE)
    }

    /**
     * 保存Int类型的数据
     */
    fun putInt(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    /**
     * 获取Int类型的数据
     */
    fun getInt(key: String, default: Int): Int {
        return sp.getInt(key, default)
    }


    /**
     * 保存Int类型的数据
     */
    fun putString(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    /**
     * 获取Int类型的数据
     */
    fun getString(key: String): String {
        return sp.getString(key, "") ?: ""
    }

    /**
     * 清理数据
     */
    fun clear() {
        sp.edit().clear().apply()
    }
}