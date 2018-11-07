package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

class MeshView(context:Context?, attrs:AttributeSet): View(context,attrs){
    private var meshDrawable:MeshDrawable = MeshDrawable()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        meshDrawable.setBounds(100,100,width,height)
        meshDrawable.draw(canvas)
    }
}