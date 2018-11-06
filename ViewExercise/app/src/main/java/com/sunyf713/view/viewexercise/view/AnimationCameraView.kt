package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.sunyf713.view.viewexercise.BitmapUtil
import com.sunyf713.view.viewexercise.DpUtil

class AnimationCameraView(context:Context?, attrs:AttributeSet): View(context,attrs){

    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0
    private var centerY = 0
    private val iWidth = DpUtil.dp2px(100f)
    private val padding = DpUtil.dp2px(100f)
    private var rectFTop:RectF = RectF()
    private var rectFBottom:RectF = RectF()
    private var camera:Camera
    var angle:Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    var rotate =0f
        set(value) {
            field = value
            invalidate()
        }
    var rotateB =0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.style = Paint.Style.FILL
        camera = Camera()
        camera.setLocation(0f,0f,-8*resources.displayMetrics.density)
        rotate
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width/2
        centerY = height/2
        rectFTop.set(-iWidth, -iWidth , iWidth, 0f)
        rectFBottom.set(-iWidth, 0f , iWidth, iWidth)
    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.translate(padding+iWidth/2,padding+iWidth/2)
        canvas?.rotate(-angle)
        camera.save()
        camera.rotateX(rotateB)
        camera.applyToCanvas(canvas)
        camera.restore()
//        canvas?.translate(-(padding+iWidth/2),-(padding+iWidth/2))
//        canvas?.translate(padding+iWidth/2,padding+iWidth/2)
        canvas?.clipRect(rectFTop)
        canvas?.rotate(angle)
        canvas?.translate(-(padding+iWidth/2),-(padding+iWidth/2))
        canvas?.drawBitmap(BitmapUtil.getBitmap(resources,iWidth.toInt()),padding,padding,paint)
        canvas?.restore()


        canvas?.save()
        canvas?.translate(padding+iWidth/2,padding+iWidth/2)
        canvas?.rotate(-angle)
        camera.save()
        camera.rotateX(rotate)
        camera.applyToCanvas(canvas)
        camera.restore()
//        canvas?.translate(-(padding+iWidth/2),-(padding+iWidth/2))
//        canvas?.translate(padding+iWidth/2,padding+iWidth/2)
        canvas?.clipRect(rectFBottom)
        canvas?.rotate(angle)
        canvas?.translate(-(padding+iWidth/2),-(padding+iWidth/2))
        canvas?.drawBitmap(BitmapUtil.getBitmap(resources,iWidth.toInt()),padding,padding,paint)
        canvas?.restore()
    }


}