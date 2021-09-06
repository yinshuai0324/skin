package com.library.skin

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.library.skin.callback.SkinStyleHandler
import com.library.skin.callback.SkinSupportView
import com.library.skin.style.SkinStyle
import com.library.skin.utils.SpUtils
import java.lang.ref.WeakReference

object SkinManage {

    /**
     * 存储Activity里面所需要改变皮肤的View
     */
    private val activityViews = hashMapOf<String, ArrayList<WeakReference<View>>>()

    /**
     * Activity的皮肤样式
     */
    private val activityStyleTags = hashMapOf<String, String>()

    /**
     * 所支持换肤的实现
     */
    private lateinit var supportViewImp: SkinSupportView

    /**
     * 设置换肤逻辑的实现
     */
    private lateinit var skinStyleHandlerImp: SkinStyleHandler

    /**
     * 用户所有的样式集合
     */
    private val skinStyles = ArrayList<SkinStyle>()

    /**
     * 换肤的主题 默认没有 需要用户自己设置
     */
    private var skinStyle: SkinStyle? = null

    /**
     * 存储用户切换的KEY
     */
    private const val KEY_STYLE_CONFIG = "SkinStyleConfig"


    /**
     * 注册Activity
     */
    fun register(activity: Activity) {
        val rootView = activity.findViewById<ViewGroup>(android.R.id.content)
        val activityName = activity.javaClass.name
        rootView?.let {
            it.post {
                val viewList = arrayListOf<WeakReference<View>>()
                addActivityViews(it, viewList)
                activityViews[activityName] = viewList
                activityStyleTags[activityName] = skinStyle?.skinTag() ?: ""
                applyStyle(activity)
            }
        }
    }

    /**
     * 反注册Activity
     */
    fun unRegister(activity: Activity) {
        val activityName = activity.javaClass.name
        activityViews.remove(activityName)
    }


    /**
     * 对支持换肤的View进行生效
     */
    private fun applyStyle(activity: Activity) {
        val activityName = activity.javaClass.name
        if (::skinStyleHandlerImp.isInitialized) {
            if (activityViews.containsKey(activityName)) {
                val views = activityViews[activityName]
                views?.forEach {
                    it.get()?.let { it1 ->
                        skinStyleHandlerImp.styleHandler(it1, skinStyle)
                    }
                }
            }
        } else {
            Log.e("skin", "请调用Skin.setSkinStyleHandlerImp()方法")
        }
    }


    /**
     * 添加Activity符合换肤的View
     */
    private fun addActivityViews(view: View, views: ArrayList<WeakReference<View>>) {
        if (view is ViewGroup) {
            view.children.forEach {
                views.add(WeakReference<View>(it))
                addActivityViews(it, views)
            }
        } else {
            if (viewIsSupportSkin(view)) {
                views.add(WeakReference<View>(view))
            }
        }
    }


    /**
     * 判断这个View是否支持换肤
     */
    private fun viewIsSupportSkin(view: View): Boolean {
        if (::supportViewImp.isInitialized) {
            supportViewImp.getSupportViews().forEach {
                val viewName = view.javaClass.name
                if (viewName.indexOf(it) != -1) {
                    return true
                }
            }
        } else {
            Log.e("skin", "请调用Skin.setSupportViewImp()方法")
        }
        return false
    }


    /**
     * 改变皮肤
     */
    fun changeSkin(activity: Activity, tag: String) {
        skinStyle = getStyleByTag(tag)
        applyStyle(activity)
        SpUtils.putString(KEY_STYLE_CONFIG, tag)
    }


    /**
     * 更新皮肤
     */
    fun updateSkinStyle(activity: Activity) {
        val activityName = activity.javaClass.name
        val activityStyle = activityStyleTags[activityName]
        if (activityStyle != skinStyle?.skinTag() ?: "") {
            applyStyle(activity)
        }
    }


    /**
     * 默认主题
     */
    fun defaultStyleTag(style: String) {
        val styleTag = SpUtils.getString(KEY_STYLE_CONFIG)
        if (TextUtils.isEmpty(styleTag)) {
            skinStyle = getStyleByTag(style)
            SpUtils.putString(KEY_STYLE_CONFIG, skinStyle?.skinTag() ?: "")
        } else {
            skinStyle = getStyleByTag(styleTag)
        }
    }


    /**
     * 根据Tag获取样式
     */
    private fun getStyleByTag(tag: String): SkinStyle? {
        skinStyles.forEach {
            if (it.skinTag() == tag) {
                return it
            }
        }
        return null
    }

    /**
     * 获取当前样式的Style
     */
    fun getCurrentStyle(): SkinStyle? {
        return skinStyle
    }


    /**
     * 设置支持的View的实现
     */
    fun setSupportViewImp(imp: SkinSupportView) {
        this.supportViewImp = imp
    }

    /**
     * 设置支持的View的实现
     */
    fun setStyleHandlerImp(imp: SkinStyleHandler) {
        this.skinStyleHandlerImp = imp
    }

    /**
     * 设置样式
     */
    fun setStyleConfig(vararg data: SkinStyle) {
        skinStyles.addAll(*data.toMutableList())
    }
}