package com.library.skin

import android.app.Activity
import android.app.Application
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.library.skin.callback.SkinStyleHandler
import com.library.skin.callback.SkinSupportView
import com.library.skin.utils.SkinUtils
import com.library.skin.utils.SpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

object SkinManage {

    private lateinit var application: Application

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
     * 换肤的主题 默认没有 需要用户自己设置
     */
    private var skinStyleTag: String? = ""

    /**
     * 存储用户切换的KEY
     */
    private const val KEY_STYLE_CONFIG = "SkinStyleConfig"

    private val skinStyleManage = SkinStyleManage()

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
                activityStyleTags[activityName] = skinStyleTag ?: ""
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
                        skinStyleHandlerImp.styleHandler(it1, getStyleByTag(skinStyleTag))
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
        skinStyleTag = tag
        applyStyle(activity)
        SpUtils.putString(KEY_STYLE_CONFIG, tag)
    }


    /**
     * 更新皮肤
     */
    fun updateSkinStyle(activity: Activity) {
        val activityName = activity.javaClass.name
        val activityStyle = activityStyleTags[activityName]
        if (activityStyle != skinStyleTag ?: "") {
            applyStyle(activity)
        }
    }


    /**
     * 默认主题
     */
    fun defaultStyleTag(tag: String) {
        val styleTag = SpUtils.getString(KEY_STYLE_CONFIG)
        if (TextUtils.isEmpty(styleTag)) {
            skinStyleTag = tag
            SpUtils.putString(KEY_STYLE_CONFIG, tag ?: "")
        } else {
            skinStyleTag = styleTag
        }
    }


    /**
     * 加载皮肤配置
     */
    fun <T> loadSkinStyle(assets: String, clazz: Class<T>) {
        GlobalScope.launch(Dispatchers.Main) {
            flow {
                val json = SkinUtils.getAssetsJson(application, assets)
                emit(json)
            }.flowOn(Dispatchers.IO).onCompletion { e ->
                if (e != null) {
                    Log.e("===>>>", "加载皮肤配置失败:${e}")
                } else {
                    Log.i("===>>>", "加载皮肤配置成功")
                }
            }.onEach {
                //处理数据
                skinStyleManage.formJson(it, clazz)
            }.catch { e ->
                Log.e("===>>>", "加载皮肤配置出现异常:${e}")
            }.collect()
        }
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
    fun setApplication(application: Application) {
        this.application = application
    }

    /**
     * 根据Key获取配置
     */
    fun getStyleByTag(tag: String?): Any? {
        if (!TextUtils.isEmpty(tag) && skinStyleManage.styleConfig.containsKey(tag)) {
            return skinStyleManage.styleConfig[tag]
        }
        return null
    }

    /**
     * 获取当前样式的Tag
     */
    fun getCurrentTag(): String? {
        return skinStyleTag
    }
}