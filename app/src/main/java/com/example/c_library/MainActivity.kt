package com.example.c_library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.c_library.demo.CLogDemoActivity
import com.example.c_library.demo.ChTabBottomDemoActivity
import org.chen.chui.tab.bottom.ChTabBottom
import org.chen.chui.tab.bottom.ChTabBottomInfo

/**
 *
 * */
class MainActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_Clog->{
                startActivity(Intent(this,CLogDemoActivity::class.java))
            }
            R.id.tv_tab_Bottom->{
                startActivity(Intent(this,ChTabBottomDemoActivity::class.java))
            }
        }
    }
}