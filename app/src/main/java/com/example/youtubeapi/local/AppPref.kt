package com.example.youtubeapi.local

import android.content.Context

class AppPref(context: Context){
    private val prefs = context.getSharedPreferences("ololo",Context.MODE_PRIVATE)

    var onBoard:Boolean
        get() = prefs.getBoolean("kayrat",false)
        set(value) = prefs.edit().putBoolean("kayrat",value).apply()
}
