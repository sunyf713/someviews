package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.sunyf713.view.viewexercise.DpUtil

class PointView(context:Context?, attrs:AttributeSet): View(context,attrs){

    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var point:Point = Point(DpUtil.dp2px(50f).toInt(), DpUtil.dp2px(50f).toInt())
        set(value) {
            field = value
            invalidate()
        }



    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = DpUtil.dp2px(10f)
        paint.strokeCap = Paint.Cap.ROUND
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPoint(point.x.toFloat(), point.y.toFloat(),paint)
    }


}