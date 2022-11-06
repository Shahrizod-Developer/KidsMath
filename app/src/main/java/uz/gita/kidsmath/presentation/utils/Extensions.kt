package uz.gita.kidsmath.presentation.utils

import android.animation.Animator
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> eventFlow() =
    MutableSharedFlow<T>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


fun View.Animation(){
    animate().scaleX(1.1f)
        .scaleY(1.1f)
        .setDuration(1000)
        .setListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                animate().scaleX(1f).scaleY(1f).setDuration(1000)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {
                        }


                        override fun onAnimationEnd(animation: Animator?) {

                        }


                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationRepeat(animation: Animator?) {
                        }

                    }).start()

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        }).start()
}
fun View.onClick(block: () -> Unit) {
    animate().scaleX(1.2f)
        .scaleY(1.2f)
        .setDuration(100)
        .setListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                animate().scaleX(1f).scaleY(1f).setDuration(100)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {
                        }


                        override fun onAnimationEnd(animation: Animator?) {
                            block.invoke()
                        }


                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationRepeat(animation: Animator?) {
                        }

                    }).start()

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        }).start()
}

fun Fragment.vibratePhone() {
    val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                300, VibrationEffect.CONTENTS_FILE_DESCRIPTOR
            )
        )
    } else {
        vibrator.vibrate(300)
    }
}