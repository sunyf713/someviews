package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.sunyf713.view.viewexercise.DpUtil
import com.sunyf713.view.viewexercise.R

class AvatarView(context:Context?, attrs:AttributeSet): View(context,attrs){

    private var paint:Paint
    private var centerX = 0
    private var centerY = 0
    private val redius = DpUtil.dp2px(100f)
    private val padding = DpUtil.dp2px(10f)
    private var rectF:RectF = RectF()
    private var rectFP:RectF = RectF()
    private var xfermode:Xfermode

    init {

        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width/2
        centerY = height/2
        rectF.set(centerX-redius,centerY-redius,centerX+redius,centerY+redius)
        rectFP.set(centerX+padding-redius, padding + centerY-redius, -padding +centerX+redius, -padding + centerY+redius)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawOval(rectF,paint)
        val count = canvas?.saveLayer(rectF,paint)
        canvas?.drawOval(rectFP, paint)
        paint.xfermode = xfermode
        canvas?.drawBitmap(getBitmap(((redius-padding)*2).toInt()),centerX+padding-redius,padding + centerY-redius,paint)
        paint.xfermode = null
        canvas?.restoreToCount(count!!)

    }

    private fun getBitmap(width:Int):Bitmap{
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources,R.drawable.header,option)
        option.inJustDecodeBounds = false
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.header,option)
    }
}