package com.sunyf713.view.viewexercise.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.sunyf713.view.viewexercise.BitmapUtil
import com.sunyf713.view.viewexercise.DpUtil

class CameraView(context:Context?, attrs:AttributeSet): View(context,attrs){

    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0
    private var centerY = 0
    private val iWidth = DpUtil.dp2px(100f)
    private val padding = DpUtil.dp2px(100f)
    private var rectFTop:RectF = RectF()
    private var rectFBottom:RectF = RectF()
    private var camera:Camera
    private var angle:Int = 0

    init {

        paint.style = Paint.Style.FILL
        camera = Camera()
        camera.setLocation(0f,0f,-8*resources.displayMetrics.density)
        camera.rotateX(60f)


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width/2
        centerY = height/2
        rectFTop.set(0f, 0f , padding+iWidth*2, padding+iWidth/2)
        rectFBottom.set(0f, padding+iWidth/2 , padding+iWidth*2, padding+iWidth*2)
    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.rotate((-angle).toFloat(),padding+iWidth/2,padding+iWidth/2)
        canvas?.clipRect(rectFTop)
        canvas?.rotate(angle.toFloat(),padding+iWidth/2,padding+iWidth/2)
        canvas?.drawBitmap(BitmapUtil.getBitmap(resources,iWidth.toInt()),padding,padding,paint)
        canvas?.restore()

        canvas?.save()
        canvas?.rotate((-angle).toFloat(),padding+iWidth/2,padding+iWidth/2)
        canvas?.clipRect(rectFBottom)
        canvas?.translate(padding+iWidth/2,padding+iWidth/2)
        camera.applyToCanvas(canvas)
        canvas?.translate(-(padding+iWidth/2),-(padding+iWidth/2))
        canvas?.rotate(angle.toFloat(),padding+iWidth/2,padding+iWidth/2)
        canvas?.drawBitmap(BitmapUtil.getBitmap(resources,iWidth.toInt()),padding,padding,paint)
        canvas?.restore()
    }

    public fun startAnimation(){
        val animator = ValueAnimator.ofInt(0,360)
        val interpolator=AccelerateDecelerateInterpolator()
        animator.interpolator = interpolator
        animator.duration = 20000
        animator.addUpdateListener(ValueAnimator.AnimatorUpdateListener {
            angle = it.animatedValue as Int
            Log.d("syf","angle"+angle)
            invalidate()
        })
        animator.start()
    }

}