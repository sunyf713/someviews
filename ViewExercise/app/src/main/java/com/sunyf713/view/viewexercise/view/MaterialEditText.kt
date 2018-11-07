package com.sunyf713.view.viewexercise.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import com.sunyf713.view.viewexercise.DpUtil
import com.sunyf713.view.viewexercise.R

class MaterialEditText(context:Context?,attrs:AttributeSet):EditText(context,attrs){
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var useMaterial:Boolean = false
    private var marginTop = DpUtil.dp2px(20f)
    private val upTextHeight:Float
    private var hasShown =false
    private var animationUp:ObjectAnimator
    private var textWatcher:MyTextWatcher
    private var animationProgress:Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    init {
        paint.textSize = textSize
        upTextHeight = marginTop+textSize
        paint.color = hintTextColors.defaultColor
        val typeArray= context?.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        val type = typeArray?.getInt(R.styleable.MaterialEditText_material_style,0);
        typeArray?.recycle()
        textWatcher = MyTextWatcher()
        animationUp = ObjectAnimator.ofFloat(this, "animationProgress",0f,1f)
        if(type==0){
            openMaterial()
        }
    }

    fun openMaterial(){
        if(!useMaterial) {
            setPadding(paddingLeft, (paddingTop+upTextHeight).toInt(),paddingRight,paddingBottom)
            addTextChangedListener(textWatcher)
            useMaterial = true
            if(!TextUtils.isEmpty(text)){
                if(animationUp.isRunning){
                    animationUp.cancel()
                }
                animationUp.start()
            }
        }
    }

    fun closeMaterial(){
        if(useMaterial) {
            setPadding(paddingLeft, (paddingTop-upTextHeight).toInt(),paddingRight,paddingBottom)
            removeTextChangedListener(textWatcher)
            useMaterial = false
            if(!TextUtils.isEmpty(text)){
                if(animationUp.isRunning){
                    animationUp.cancel()
                }
                animationUp.reverse()
            }
        }
    }


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if(!TextUtils.isEmpty(hint)) {
            paint.alpha = (0xff * animationProgress).toInt()
            canvas?.drawText(hint.toString(), 20f, marginTop+paint.textSize * (1 - animationProgress), paint)
        }
    }

    inner class MyTextWatcher:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(hasShown&&TextUtils.isEmpty(s)){
                animationUp.reverse()
                hasShown = false
            }else if(!hasShown&&!TextUtils.isEmpty(s)){
                animationUp.start()
                hasShown = true
            }
        }

    }
}