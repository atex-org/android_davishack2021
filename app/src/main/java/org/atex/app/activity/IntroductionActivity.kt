package org.atex.app.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_introduction.*
import org.atex.app.R
import org.atex.app.utils.Rotate3dAnimation


class IntroductionActivity : AppCompatActivity() {
    private val SPLASH_DURATION = 1500L
    var centerX = 0f
    var centerY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)
        imageView.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                imageView.viewTreeObserver.removeOnPreDrawListener(this)
                centerX = imageView.measuredWidth / 2.0f
                centerY = imageView.measuredHeight / 2.0f
                val rotation = Rotate3dAnimation(360f, 0f, centerX, centerY, 1200.0f, false)
                rotation.duration = SPLASH_DURATION
                rotation.fillAfter = true
                rotation.interpolator = DecelerateInterpolator()
                imageView.startAnimation(rotation)
                rotation.setAnimationListener(animationListener)
                return true
            }
        })
    }

    private var animationListener: Animation.AnimationListener =
        object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                success()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        }

    fun success() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

}