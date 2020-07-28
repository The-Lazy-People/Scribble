package com.ayushidoshi56.scribble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paintview=PaintView(this)
        setContentView(paintview)
//        paintview.co(100f,105f)
//        paintview.co(201f,701f)
//        paintview.co(302f,603f)
//        paintview.co(604f,400f)
//        paintview.co(607f,908f)
    }
}