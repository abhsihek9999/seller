package com.hk.seller.utils.onBoard

import android.animation.Animator
import android.animation.Animator.AnimatorListener

/**
 * Just sugar for code clean
 */
abstract class AnimatorEndListener : AnimatorListener {
    override fun onAnimationStart(animation: Animator) {
        //do nothing
    }

    override fun onAnimationCancel(animation: Animator) {
        //do nothing
    }

    override fun onAnimationRepeat(animation: Animator) {
        //do nothing
    }

}