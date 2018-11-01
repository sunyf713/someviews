package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.sunyf713.view.viewexercise.DpUtil

class CircleProgressBar(context:Context?, attrs:AttributeSet): View(context,attrs){

    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var textPaint:Paint
    private var centerX = 0
    private var centerY = 0
    private val radius = DpUtil.dp2px(140f)
    private val progressWidth = DpUtil.dp2px(10f)
    private var rectF:RectF = RectF()
    private var progress:Int
    private var angleProgress:Float
    private var fontMetrics:Paint.FontMetrics = Paint.FontMetrics()
    private var bloodColor = Color.parseColor("#cc0000")

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = progressWidth
        paint.strokeCap = Paint.Cap.ROUND
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.typeface = Typeface.createFromAsset(context!!.assets,"Bleed.ttf")
        textPaint.textSize = DpUtil.dp2px(24f)
        textPaint.color = bloodColor
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.getFontMetrics(fontMetrics)
        progress = 80
        angleProgress =360/100f*progress;


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width/2
        centerY = height/2
        rectF.set(centerX-radius,centerY-radius,centerX+radius,centerY+radius)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.GRAY
        canvas?.drawOval(rectF,paint)
        paint.color = bloodColor
        canvas?.drawArc(rectF,-90f,angleProgress,false,paint)
        val offset = (fontMetrics.ascent+fontMetrics.descent)/2
        canvas?.drawText("happy halloween", centerX.toFloat(), centerY.toFloat()-offset,textPaint)

    }


}