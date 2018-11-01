package com.sunyf713.view.viewexercise.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.sunyf713.view.viewexercise.DpUtil
import com.sunyf713.view.viewexercise.R

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(getBitmap(iWidth.toInt()),width-padding-iWidth,padding,paint)
        var verticalOffset = -fontMetrics.top
        val length = text.length
        var i = 0;
        while(i<length){
            val textTop = verticalOffset+fontMetrics.top
            val textBottom = verticalOffset+fontMetrics.bottom
            var textwidth:Float
            if((textTop>padding&&textTop<padding+iWidth)||(textBottom>padding&&textBottom<padding+iWidth)){
                textwidth = width-padding-iWidth
            }else{
                textwidth = width.toFloat()
            }
            val count = textPaint.breakText(text,i,length,true,textwidth,curWidth)
            Log.d("syf",curWidth[0].toString()+":"+textwidth+"width:"+width+"padding"+padding+"iwith"+iWidth+"count"+count+"i"+i)
            canvas?.drawText(text, i, i + count, 0f, verticalOffset, textPaint)
            verticalOffset+=textPaint.fontSpacing
            i+=count
        }
    }

    private fun getBitmap(width:Int):Bitmap{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.header,options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.header,options)
    }


}