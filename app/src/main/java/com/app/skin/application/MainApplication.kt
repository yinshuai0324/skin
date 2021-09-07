package com.app.skin.application

import android.app.Application
import com.app.skin.bean.SkinStyleBean
import com.app.skin.skin.SkinStyleHandlerImp
import com.app.skin.skin.SkinSupportViewImp
import com.library.skin.Skin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Skin.init(this)
            .loadSkinStyleAssets("skin_config.json", SkinStyleBean::class.java)
            .setSkinStyleHandlerImp(SkinStyleHandlerImp())
            .setSupportSkinViewImp(SkinSupportViewImp())
            .defaultStyleTag("skin1")
    }
}