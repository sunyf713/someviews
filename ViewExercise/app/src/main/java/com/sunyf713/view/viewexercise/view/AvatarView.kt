package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.sunyf713.view.viewexercise.BitmapUtil
import com.sunyf713.view.viewexercise.DpUtil

class AvatarView(context:Context?, attrs:AttributeSet): View(context,attrs){

    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0
    private var centerY = 0
    private val radius = DpUtil.dp2px(100f)
    private val padding = DpUtil.dp2px(10f)
    private var rectF:RectF = RectF()
    private var rectFP:RectF = RectF()
    private var xfermode:Xfermode

    init {

        paint.style = Paint.Style.FILL
        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width/2
        centerY = height/2
        rectF.set(centerX-radius,centerY-radius,centerX+radius,centerY+radius)
        rectFP.set(centerX+padding-radius, padding + centerY-radius, -padding +centerX+radius, -padding + centerY+radius)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawOval(rectF,paint)
        val count = canvas?.saveLayer(rectF,paint)
        canvas?.drawOval(rectFP, paint)
        paint.xfermode = xfermode
        canvas?.drawBitmap(BitmapUtil.getBitmap(resources,((radius-padding)*2).toInt()),centerX+padding-radius,padding + centerY-radius,paint)
        paint.xfermode = null
        canvas?.restoreToCount(count!!)

    }

}