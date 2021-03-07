package com.example.swipertest.util

import android.content.Context
import android.content.res.Resources

object DisplayUtil {

    val Float.dp: Float
        get() = this * Resources.getSystem().displayMetrics.density
    val Float.px: Float
        get() = this / Resources.getSystem().displayMetrics.density


    fun getStatusBarHeight(context: Context?): Int {
        var result = 0
        context?.resources?.getIdentifier("status_bar_height", "dimen", "android")?.let { resourceId ->
            if (resourceId > 0) {
                result = context.resources?.getDimensionPixelSize(resourceId)?: 0
            }
        }

        return result
    }

    fun getNavigationBarHeight(context: Context?): Int {
        var result = 0
        context?.resources?.getIdentifier("navigation_bar_height", "dimen", "android")?.let { resourceId ->
            if (resourceId > 0) {
                result = context.resources?.getDimensionPixelSize(resourceId)?: 0
            }
        }

        return result
    }

}