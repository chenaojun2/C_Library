package com.example.c_library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.c_library.demo.CLogDemoActivity
import org.chen.chui.tab.bottom.ChTabBottom
import org.chen.chui.tab.bottom.ChTabBottomInfo

/**
 *
 * */
class MainActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabBottom = findViewById<ChTabBottom>(R.id.tab_bottom)
        val homeInfo = ChTabBottomInfo(
            "首页",
            "fonts/iconfont.ttf",
            getString(R.string.if_home),
            null,
            "#ff656667",
            "#ffd44949"
        )
        tabBottom.setChTabInfo(homeInfo);
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_Clog->{
                startActivity(Intent(this,CLogDemoActivity::class.java))
            }
        }
    }
}