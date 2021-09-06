package com.app.skin.skin

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.library.skin.callback.SkinStyleHandler
import com.library.skin.style.SkinStyle

class SkinStyleHandlerImp : SkinStyleHandler {
    override fun styleHandler(view: View, style: SkinStyle?) {
        style?.let {
            when (view) {
                is TextView -> {
                    view.setTextColor(it.skinTextColor)
                }
                is ConstraintLayout -> {
                    view.setBackgroundColor(it.skinLayoutBackgroundColor)
                }
            }
        }
    }
}