package com.library.skin.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader


class SkinUtils {
    companion object {

        fun getAssetsJson(context: Context?, fileName: String): String {
            if (context != null) {
                val stringBuffer = StringBuffer()
                BufferedReader(InputStreamReader(context.assets.open(fileName))).use {
                    var line = ""
                    while (true) {
                        line = it.readLine() ?: break
                        stringBuffer.append(line)
                    }
                }
                return stringBuffer.toString()
            }
            return ""
        }

    }
}