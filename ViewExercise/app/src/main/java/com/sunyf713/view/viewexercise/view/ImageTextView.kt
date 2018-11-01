package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.sunyf713.view.viewexercise.BitmapUtil
import com.sunyf713.view.viewexercise.DpUtil

class ImageTextView(context:Context?, attrs:AttributeSet): View(context,attrs){

    private var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var textPaint:Paint
    private val iWidth = DpUtil.dp2px(50f)
    private val padding = DpUtil.dp2px(40f)
    private var fontMetrics:Paint.FontMetrics = Paint.FontMetrics()
    private var bloodColor = Color.parseColor("#cc0000")
    private var text = "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN," +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN," +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN，" +
            "happy halloween HAPPY HALLOWEEN，happy halloween HAPPY HALLOWEEN，" +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN," +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN," +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN," +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN," +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN," +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN," +
            "happy halloween HAPPY HALLOWEEN,happy halloween HAPPY HALLOWEEN,"
    private var curWidth = FloatArray(1)
    init {
        paint.style = Paint.Style.FILL
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.typeface = Typeface.createFromAsset(context!!.assets,"Bleed.ttf")
        textPaint.textSize = DpUtil.dp2px(16f)
        textPaint.color = bloodColor
        textPaint.getFontMetrics(fontMetrics)


    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(BitmapUtil.getBitmap(resources,iWidth.toInt()),width-padding-iWidth,padding,paint)
        var verticalOffset = -fontMetrics.top
        val length = text.length
        var i = 0
        while(i<length){
            val textTop = verticalOffset+fontMetrics.top
            val textBottom = verticalOffset+fontMetrics.bottom
            var textWidth:Float
            textWidth = if((textTop>padding&&textTop<padding+iWidth)||(textBottom>padding&&textBottom<padding+iWidth)){
                width-padding-iWidth
            }else{
                width.toFloat()
            }
            val count = textPaint.breakText(text,i,length,true,textWidth,curWidth)
            Log.d("syf",curWidth[0].toString()+":"+textWidth+"width:"+width+"padding"+padding+"iwith"+iWidth+"count"+count+"i"+i)
            canvas?.drawText(text, i, i + count, 0f, verticalOffset, textPaint)
            verticalOffset+=textPaint.fontSpacing
            i+=count
        }
    }

}