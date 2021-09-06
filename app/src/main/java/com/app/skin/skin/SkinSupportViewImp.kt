package com.app.skin.skin

import com.library.skin.callback.SkinSupportView

class SkinSupportViewImp : SkinSupportView {
    override fun getSupportViews(): ArrayList<String> {
        return arrayListOf("ConstraintLayout", "TextView", "Button")
    }
}