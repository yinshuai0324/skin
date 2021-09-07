package com.app.skin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.app.skin.R
import com.app.skin.ui.SettingActivity

class Fragment3 : Fragment() {
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_3, container, false)
        rootView.findViewById<Button>(R.id.settingBtn).setOnClickListener {
            startActivity(Intent(requireContext(), SettingActivity::class.java))
        }
        return rootView
    }


}