package com.app.skin

import android.graphics.Color
import com.app.skin.bean.SettingDataBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class SettingAdapter : BaseQuickAdapter<SettingDataBean, BaseViewHolder>(R.layout.item_setting) {

    override fun convert(holder: BaseViewHolder, item: SettingDataBean) {
        holder.setText(R.id.title, item.name)
        holder.setText(R.id.tag, item.tag)
        holder.setBackgroundColor(R.id.colorView, Color.parseColor(item.color))
        holder.setGone(R.id.selectTips, !item.isSelect)
    }
}