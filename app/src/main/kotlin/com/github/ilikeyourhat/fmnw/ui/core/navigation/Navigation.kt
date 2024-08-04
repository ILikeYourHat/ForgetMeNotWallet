package com.github.ilikeyourhat.fmnw.ui.core.navigation

import android.app.Activity
import android.content.Intent
import com.github.ilikeyourhat.fmnw.ui.screen.addcode.AddCodeActivity
import com.github.ilikeyourhat.fmnw.ui.screen.scancode.ScanCodeActivity

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
    data object ScanCode: Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, ScanCodeActivity::class.java)
            activity.startActivity(intent)
        }
    }
}