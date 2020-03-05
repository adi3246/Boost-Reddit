package com.boost.reddit

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.boost.reddit.model.TopicModel

class ApplicationClass : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        contextApp = applicationContext

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var contextApp: Context

        var topicModel : ArrayList<TopicModel> = ArrayList()
    }
}