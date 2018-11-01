package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.sunyf713.view.viewexercise.DpUtil

class PieChart(context:Context?,attrs:AttributeSet): View(context,attrs){

    private var angles:FloatArray
    private var colors:IntArray
    private var outIndex = 2
    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0
    private var centerY = 0
    private var radius = DpUtil.dp2px(100f)
    private var outLength = DpUtil.dp2px(20f)
    private var rect: RectF = RectF()

    init {
        paint.style = Paint.Style.FILL
        angles = floatArrayOf(0f,30f,180f,270f,315f,360f)
        colors = intArrayOf(Color.BLUE,Color.GREEN,Color.YELLOW,Color.RED,Color.GRAY)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width/2
        centerY = height/2
        rect.set(centerX-radius, centerY-radius, centerX+radius, centerY+radius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in 0..4){
            paint.color = colors[i]
            canvas?.save()
            if(i==outIndex){
                val angle = angles[i] +(angles[i+1]- angles[i])/2
                canvas?.translate((Math.cos(Math.toRadians(angle.toDouble()))*outLength).toFloat(), (Math.sin(Math.toRadians(angle.toDouble()))*outLength).toFloat())
            }
            canvas?.drawArc(rect, angles[i], angles[i + 1] - angles[i], true, paint)
            canvas?.restore()
        }
    }
}