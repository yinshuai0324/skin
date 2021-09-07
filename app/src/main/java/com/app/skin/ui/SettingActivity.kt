package com.app.skin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.skin.R
import com.app.skin.SettingAdapter
import com.app.skin.bean.SettingDataBean
import com.chad.library.adapter.base.animation.ScaleInAnimation
import com.library.skin.Skin

class SettingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: SettingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        recyclerView = findViewById(R.id.recyclerView)
        initRecyclerView()


    }


    private fun initRecyclerView() {
        adapter = SettingAdapter()
        adapter.animationEnable = true
        adapter.adapterAnimation = ScaleInAnimation()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            val data = adapter.data[position] as SettingDataBean
//            data.isSelect = true
            Skin.changeSkin(this, data.tag)
        }
        initData()
    }

    private fun initData() {
        val datas = arrayListOf<SettingDataBean>()
        datas.add(SettingDataBean("皮肤1", "skin1", "#FF0000", false))
        datas.add(SettingDataBean("皮肤2", "skin2", "#FFFF00", false))
        adapter.addData(datas)
    }
}