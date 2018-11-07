package com.sunyf713.view.viewexercise.view

import android.graphics.*
import android.graphics.drawable.Drawable

class MeshDrawable: Drawable() {
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.color = Color.parseColor("#cc0000")
        paint.strokeWidth = 1f
    }
    override fun draw(canvas: Canvas?) {
        var i = bounds.top
        var j = bounds.left
        while(i <bounds.bottom){
            canvas?.drawLine(bounds.left.toFloat(), i.toFloat(), bounds.right.toFloat(), i.toFloat(),paint)
            i+=200
            while(j<bounds.right){
                canvas?.drawLine(j.toFloat(), bounds.top.toFloat(), j.toFloat(), bounds.bottom.toFloat(),paint)
                j+=200
            }
        }
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int {
       return if(paint.alpha==0)
             PixelFormat.TRANSPARENT
        else if(paint.alpha==0xff)
            PixelFormat.OPAQUE
        else
            PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

}