package com.library.skin

import android.app.Activity
import android.app.Application
import androidx.annotation.StyleRes
import com.library.skin.callback.SkinActivityLifecycleCallbacks
import com.library.skin.callback.SkinStyleHandler
import com.library.skin.callback.SkinSupportView
import com.library.skin.style.SkinStyle
import com.library.skin.utils.Attr
import com.library.skin.utils.SpUtils

/**
 * 换肤框架的入口
 */
object Skin {

    /**
     * 初始化
     */
    fun init(application: Application) {
        SpUtils.init(application)
        Attr.init(application)
        application.registerActivityLifecycleCallbacks(SkinActivityLifecycleCallbacks())
    }

    /**
     * 添加需要支持的View
     */
    fun setSupportSkinViewImp(imp: SkinSupportView) {
        SkinManage.setSupportViewImp(imp)
    }

    /**
     * 设置处理换肤View的逻辑
     */
    fun setSkinStyleHandlerImp(imp: SkinStyleHandler) {
        SkinManage.setStyleHandlerImp(imp)
    }

    /**
     * 添加样式
     */
    fun addStyleConfig(vararg config: SkinStyle) {
        SkinManage.setStyleConfig(*config)
    }

    /**
     * 默认主题
     */
    fun defaultStyleTag(tag: String) {
        SkinManage.defaultStyleTag(tag)
    }


    fun changeSkin(activity: Activity, tag: String) {
        SkinManage.changeSkin(activity, tag)
    }
}