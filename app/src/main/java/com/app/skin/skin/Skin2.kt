package com.app.skin.skin

import com.app.skin.R
import com.library.skin.style.SkinStyle
import com.library.skin.utils.Attr

class Skin2 : SkinStyle() {

    override var skinTextColor: Int = Attr.getColor(R.color.black)

    override var skinLayoutBackgroundColor: Int = Attr.getColor(R.color.teal_200)


    override fun skinTag(): String {
        return "Skin2"
    }

}