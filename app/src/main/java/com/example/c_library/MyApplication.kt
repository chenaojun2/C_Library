package com.example.c_library

import android.app.Application
import org.chen.cibrary.log.CLogConfig
import org.chen.cibrary.log.CLogManger

class MyApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        CLogManger.init(object :CLogConfig(){

            override fun getGlobalTag(): String {
                return "MyApplication"
            }

            override fun enable(): Boolean {
                return true
            }

        })
    }

}