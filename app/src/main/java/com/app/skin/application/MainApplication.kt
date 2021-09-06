package com.app.skin.application

import android.app.Application
import com.app.skin.R
import com.app.skin.skin.Skin1
import com.app.skin.skin.Skin2
import com.app.skin.skin.SkinStyleHandlerImp
import com.app.skin.skin.SkinSupportViewImp
import com.library.skin.Skin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Skin.init(this)
        Skin.addStyleConfig(Skin1(), Skin2())
        Skin.defaultStyleTag("Skin1")
        Skin.setSupportSkinViewImp(SkinSupportViewImp())
        Skin.setSkinStyleHandlerImp(SkinStyleHandlerImp())
    }
}