package com.example.c_library.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.c_library.R
import org.chen.cibrary.log.CLog

class CLogDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_log_demo)
        findViewById<View>(R.id.btn_log).setOnClickListener { printLog() }
    }

    private fun printLog(){
        CLog.a("9900")
    }
}