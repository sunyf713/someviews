package com.sunyf713.view.viewexercise

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sunyf713.view.viewexercise.view.CameraView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        findViewById<CameraView>(R.id.camera_view).startAnimation()
    }
}
