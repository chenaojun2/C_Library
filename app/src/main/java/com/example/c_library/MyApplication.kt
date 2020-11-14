package com.example.c_library

import android.app.Application
import com.google.gson.Gson
import org.chen.cibrary.log.ChConsolePrinter
import org.chen.cibrary.log.ChLogConfig
import org.chen.cibrary.log.ChLogManger
import org.chen.cibrary.log.ChLogPrinter

class MyApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        ChLogManger.init(object : ChLogConfig(){

            override fun getGlobalTag(): String {
                return "MyApplication"
            }

            override fun enable(): Boolean {
                return true
            }

            override fun injectJsonParser(): JsonParser {
                return JsonParser{src -> Gson().toJson(src) }
            }

        }, ChConsolePrinter())
    }

}