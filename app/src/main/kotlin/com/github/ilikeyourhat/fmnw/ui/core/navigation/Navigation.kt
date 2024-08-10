package com.github.ilikeyourhat.fmnw.ui.core.navigation

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.github.ilikeyourhat.fmnw.ui.screen.addcode.AddCodeActivity
import com.github.ilikeyourhat.fmnw.ui.screen.scancode.ScanCodeActivity
import com.google.mlkit.vision.barcode.common.Barcode

sealed class Navigation {
    abstract fun navigate(activity: Activity)

    data object Close: Navigation() {
        override fun navigate(activity: Activity) {
            activity.finish()
        }
    }
    data class AddCode(
        val barcode: Barcode? = null
    ): Navigation() {
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

    companion object PickCode: Navigation() {
        override fun navigate(activity: Activity) {
            Toast.makeText(activity, "Not yet implemented", Toast.LENGTH_SHORT).show()
        }
    }
}