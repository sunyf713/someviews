package com.sunyf713.view.viewexercise

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        camera_view.postDelayed(Runnable {
            kotlin.run {
                camera_view.closeMaterial()
            }
        },5000)
        camera_view.postDelayed(Runnable {
            kotlin.run {
                camera_view.openMaterial()
            }
        },10000)
//        val objectAnimator1: ObjectAnimator  =  ObjectAnimator.ofFloat(camera_view,"rotate",45f)
//        objectAnimator1.duration = 1000
//        val objectAnimator2: ObjectAnimator  =  ObjectAnimator.ofFloat(camera_view,"angle",270f)
//        objectAnimator2.duration = 2000
//        val objectAnimator3: ObjectAnimator  =  ObjectAnimator.ofFloat(camera_view,"rotateB",-45f)
//        objectAnimator3.duration = 1000
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(objectAnimator1,objectAnimator2,objectAnimator3)
//        animatorSet.startDelay = 3000
//        animatorSet.start()




//         val propertyValuesHolder1 = PropertyValuesHolder.ofFloat("rotate",45f)
//         val propertyValuesHolder2 = PropertyValuesHolder.ofFloat("angle",270f)
//         val propertyValuesHolder3 = PropertyValuesHolder.ofFloat("rotateB",-45f)
//        val objectAnimator1: ObjectAnimator =  ObjectAnimator.ofPropertyValuesHolder(camera_view,propertyValuesHolder1,propertyValuesHolder2,propertyValuesHolder3)
//        objectAnimator1.startDelay = 3000
//        objectAnimator1.duration = 3000
//        objectAnimator1.start()



//        val objectAnimator1: ObjectAnimator  =  ObjectAnimator.ofFloat(camera_view,"rotate",45f)
//        objectAnimator1.duration = 1000
//        val k1 = Keyframe.ofFloat(0f, 0f)
//        val k2 = Keyframe.ofFloat(0.3f,90f)
//        val k3 = Keyframe.ofFloat(0.7f,180f)
//        val k4 = Keyframe.ofFloat(1f,270f)
//        val propertyValuesHolder = PropertyValuesHolder.ofKeyframe("angle",k1,k2,k3,k4)
//        val objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(camera_view,propertyValuesHolder)
//        objectAnimator2.duration = 2000
//        val objectAnimator3: ObjectAnimator  =  ObjectAnimator.ofFloat(camera_view,"rotateB",-45f)
//        objectAnimator3.duration = 1000
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(objectAnimator1,objectAnimator2,objectAnimator3)
//        animatorSet.startDelay = 3000
//        animatorSet.start()



//        val objectAnimator1: ObjectAnimator =  ObjectAnimator.ofFloat(camera_view,"rotate",45f)
//        objectAnimator1.duration = 2000
//        objectAnimator1.interpolator = AccelerateInterpolator()
//        val objectAnimator2: ObjectAnimator  =  ObjectAnimator.ofFloat(camera_view,"angle",270f)
//        objectAnimator2.duration = 3000
//        objectAnimator2.interpolator = AccelerateDecelerateInterpolator()
//        val objectAnimator3: ObjectAnimator  =  ObjectAnimator.ofFloat(camera_view,"rotateB",-45f)
//        objectAnimator3.duration = 2000
//        objectAnimator3.interpolator = DecelerateInterpolator()
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(objectAnimator1,objectAnimator2,objectAnimator3)
//        animatorSet.startDelay = 3000
//        animatorSet.start()


//        val objectAnimator = ObjectAnimator.ofObject(camera_view,"point",PointTypeEvalutor(),Point(DpUtil.dp2px(100f).toInt(), DpUtil.dp2px(100f).toInt()))
//        objectAnimator.interpolator = BounceInterpolator()
//        objectAnimator.startDelay=3000
//        objectAnimator.duration=3000
//        objectAnimator.start()
    }


//    class PointTypeEvalutor:TypeEvaluator<Point>{
//        override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
//            return Point((startValue!!.x+fraction*(endValue!!.x-startValue.x)).toInt(), (startValue.y+fraction*(endValue.y-startValue.y)).toInt())
//        }
//
//    }

}
