package com.library.skin

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import org.json.JSONObject

class SkinStyleManage {
    var styleConfig = hashMapOf<String, Any>()

    fun <T> formJson(json: String?, clazz: Class<T>) {
        if (TextUtils.isEmpty(json)) {
            return
        }
        try {
            val jsonObject = JSONObject(json)
            val jsonIterator = jsonObject.keys()
            while (jsonIterator.hasNext()) {
                val key = jsonIterator.next()
                val valueJson = jsonObject.optJSONObject(key)
                if (valueJson != null) {
                    val value = Gson().fromJson(valueJson.toString(), clazz)
                    if (value != null) {
                        styleConfig[key] = value
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("===>>>", "解析皮肤Json失败")
        }
    }
}