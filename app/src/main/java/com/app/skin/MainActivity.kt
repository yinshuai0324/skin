package com.app.skin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.skin.ui.fragment.Fragment1
import com.app.skin.ui.fragment.Fragment2
import com.app.skin.ui.fragment.Fragment3
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.library.skin.Skin

class MainActivity : AppCompatActivity() {
    private lateinit var navigationMenu: BottomNavigationView
    private val fragments = hashMapOf<Int, Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationMenu = findViewById(R.id.navigationBar)
        initFragment()
        navigationMenu.setOnNavigationItemSelectedListener {
            showFragment(it.itemId)
            true
        }
    }

    private fun initFragment() {
        val ft = supportFragmentManager.beginTransaction()
        fragments[R.id.fragment1] = Fragment1()
        fragments[R.id.fragment2] = Fragment2()
        fragments[R.id.fragment3] = Fragment3()
        ft.add(R.id.content, fragments[R.id.fragment1]!!)
        ft.add(R.id.content, fragments[R.id.fragment2]!!)
        ft.add(R.id.content, fragments[R.id.fragment3]!!)
        fragments.forEach {
            ft.hide(it.value)
        }
        ft.show(fragments[R.id.fragment1]!!)
        ft.commit()
    }

    private fun showFragment(id: Int) {
        Log.i("===>>>", "点击:${id}")
        val ft = supportFragmentManager.beginTransaction()
        fragments.forEach {
            ft.hide(it.value)
        }
        ft.show(fragments[id]!!)
        ft.commit()
    }

}

