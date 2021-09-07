package com.app.skin.skin
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.skin.bean.SkinStyleBean
import com.library.skin.callback.SkinStyleHandler
import com.library.skin.utils.SkinRes

class SkinStyleHandlerImp : SkinStyleHandler {
    override fun styleHandler(view: View, styleConfig: Any?) {
        styleConfig?.let {
            val style = styleConfig as SkinStyleBean
            when (view) {
                is TextView -> {
                    view.setTextColor(SkinRes.getColor(style.skin_text_color))
                }
                is ConstraintLayout -> {
                    view.setBackgroundColor(SkinRes.getColor(style.skin_layout_color))
                }
            }
        }
    }
}