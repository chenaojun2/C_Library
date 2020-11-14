package com.example.c_library.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.c_library.R
import org.chen.cibrary.log.ChLog
import org.chen.cibrary.log.ChLogConfig
import org.chen.cibrary.log.ChLogType

class CLogDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_log_demo)
        findViewById<View>(R.id.btn_log).setOnClickListener { printLog() }
    }

    private fun printLog(){
        //自定义Log
        ChLog.log(object : ChLogConfig(){

            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }

        },ChLogType.E,"----","5566")
        ChLog.a("9900")
    }
}