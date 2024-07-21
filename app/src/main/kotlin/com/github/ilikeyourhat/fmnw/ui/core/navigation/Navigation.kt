package com.github.ilikeyourhat.fmnw.ui.core.navigation

import android.app.Activity
import android.content.Intent
import com.github.ilikeyourhat.fmnw.ui.screen.addcode.AddCodeActivity

sealed class Navigation {
    abstract fun navigate(activity: Activity)

    data object Close: Navigation() {
        override fun navigate(activity: Activity) {
            activity.finish()
        }
    }
    data object AddCode: Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, AddCodeActivity::class.java)
            activity.startActivity(intent)
        }
    }
}