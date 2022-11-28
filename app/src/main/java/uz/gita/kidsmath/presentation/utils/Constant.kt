package uz.gita.kidsmath.presentation.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import uz.gita.kidsmath.presentation.ui.screen.activity.MainActivity


object Constants {


    fun goToPlayMarket(activity: MainActivity) {
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://payme.uz/61dbb58c75752e8a582bc517/100000")
                )
            )
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://payme.uz/61dbb58c75752e8a582bc517/100000")
                )
            )
        }
    }
}