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

    var angles:FloatArray = FloatArray(6)
    var colors:IntArray = IntArray(5)
    var out_index = 2
    var paint:Paint
    var centerX = 0
    var centerY = 0
    var REDIUS = DpUtil.dp2px(100f)
    var OUT_LENGTH = DpUtil.dp2px(20f)
    var rect: RectF
    init {
        rect = RectF()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        angles.set(0,0f)
        angles.set(1,30f)
        angles.set(2,180f)
        angles.set(3,270f)
        angles.set(4,315f)
        angles.set(5,360f)
        colors.set(0, Color.BLUE)
        colors.set(1, Color.GREEN)
        colors.set(2, Color.YELLOW)
        colors.set(3, Color.RED)
        colors.set(4, Color.GRAY)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width/2
        centerY = height/2
        rect.set(centerX-REDIUS, centerY-REDIUS, centerX+REDIUS, centerY+REDIUS)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in 0..4){
            paint.color = colors[i]
            canvas?.save()
            if(i==out_index){
                var angle = angles.get(i)+(angles.get(i+1)-angles.get(i))/2
                canvas?.translate((Math.cos(Math.toRadians(angle.toDouble()))*OUT_LENGTH).toFloat(), (Math.sin(Math.toRadians(angle.toDouble()))*OUT_LENGTH).toFloat())
            }
            canvas?.drawArc(rect, angles.get(i), angles.get(i + 1)-angles.get(i), true, paint)
            canvas?.restore()
        }
    }
}