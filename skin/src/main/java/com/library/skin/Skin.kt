package com.library.skin

import android.app.Activity
import android.app.Application
import com.library.skin.callback.SkinActivityLifecycleCallbacks
import com.library.skin.callback.SkinStyleHandler
import com.library.skin.callback.SkinSupportView
import com.library.skin.utils.SpUtils

/**
 * 换肤框架的入口
 */
object Skin {

    /**
     * 初始化
     */
    fun init(application: Application): Skin {
        SkinManage.setApplication(application)
        SpUtils.init(application)
        application.registerActivityLifecycleCallbacks(SkinActivityLifecycleCallbacks())
        return this
    }

    /**
     * 添加需要支持的View
     */
    fun setSupportSkinViewImp(imp: SkinSupportView): Skin {
        SkinManage.setSupportViewImp(imp)
        return this
    }

    /**
     * 设置处理换肤View的逻辑
     */
    fun setSkinStyleHandlerImp(imp: SkinStyleHandler): Skin {
        SkinManage.setStyleHandlerImp(imp)
        return this
    }

    /**
     * 添加样式
     */
    fun <T> loadSkinStyleAssets(assets: String, clazz: Class<T>): Skin {
        SkinManage.loadSkinStyle(assets, clazz)
        return this
    }

    /**
     * 默认主题
     */
    fun defaultStyleTag(tag: String): Skin {
        SkinManage.defaultStyleTag(tag)
        return this
    }


    /**
     * 改变皮肤
     */
    fun changeSkin(activity: Activity, tag: String) {
        SkinManage.changeSkin(activity, tag)
    }

    /**
     * 根据Key获取配置
     */
    fun getCurrentTag(): String? {
        return SkinManage.getCurrentTag()
    }
}