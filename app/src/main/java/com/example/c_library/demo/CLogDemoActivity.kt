package com.example.c_library.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.c_library.R
import org.chen.cibrary.log.*

class CLogDemoActivity : AppCompatActivity() {

    var viewPrinter :ChViewPrinter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_log_demo)
        viewPrinter = ChViewPrinter(this);
        findViewById<View>(R.id.btn_log).setOnClickListener { printLog() }
        viewPrinter!!.viewProvider.showFloatingView()
    }

    private fun printLog(){
        ChLogManger.getInstance().addPrinter(viewPrinter)
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