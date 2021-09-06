package com.app.skin.skin

import com.app.skin.R
import com.library.skin.style.SkinStyle
import com.library.skin.utils.Attr

class Skin1 : SkinStyle() {

    override var skinTextColor: Int = Attr.getColor(R.color.purple_200)

    override var skinLayoutBackgroundColor: Int = Attr.getColor(R.color.purple_700)


    override fun skinTag(): String {
        return "Skin1"
    }

}