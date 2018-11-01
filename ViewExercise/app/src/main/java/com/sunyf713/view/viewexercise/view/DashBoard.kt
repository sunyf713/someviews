package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.sunyf713.view.viewexercise.DpUtil

class DashBoard(context: Context?,attrs:AttributeSet) : View(context,attrs) {

    private val radius = DpUtil.dp2px(100f)
    private var emptyAngle = 120f
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var arcPath:Path = Path()
    private var dashPath:Path = Path()
    private var pathMeasure:PathMeasure = PathMeasure()
    private lateinit var dashPathEffect:PathDashPathEffect
    private var dashAmount = 20f
    private var centerX:Int = 0
    private var centerY:Int = 0

    init {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = DpUtil.dp2px(2f)
//        paint.strokeCap = Paint.Cap.ROUND
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w/2
        centerY = h/2
        dashPath.addRect(0f,0f,DpUtil.dp2px(2f),DpUtil.dp2px(10f),Path.Direction.CW)
        arcPath .addArc(centerX-radius,centerY-radius,centerX+radius,
                centerY+radius,90+emptyAngle/2,360-emptyAngle)
        pathMeasure.setPath(arcPath,false)
        dashPathEffect = PathDashPathEffect(dashPath,
                (pathMeasure.length-DpUtil.dp2px(2f))/dashAmount,
                0f,PathDashPathEffect.Style.ROTATE)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(arcPath,paint)
        paint.pathEffect = dashPathEffect
        canvas?.drawPath(arcPath,paint)
        paint.pathEffect = null
        val angle = getAngleFromMark(2)
        canvas?.drawLine(centerX.toFloat(), centerY.toFloat(),
                (centerX+Math.cos(Math.toRadians(angle.toDouble()))*DpUtil.dp2px(50f)).toFloat(),
                (centerY+Math.sin(Math.toRadians(angle.toDouble()))*DpUtil.dp2px(50f)).toFloat(),paint)
    }

    private fun getAngleFromMark(mark:Int):Float{
        return 90+emptyAngle/2f+(360f-emptyAngle)/20f*mark
    }
}