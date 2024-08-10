package com.github.ilikeyourhat.fmnw.ui.core.navigation

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.screen.addcode.AddCodeActivity
import com.github.ilikeyourhat.fmnw.ui.screen.scancode.ScanCodeActivity
import com.github.ilikeyourhat.fmnw.ui.screen.showcode.ShowCodeActivity

sealed class Navigation {
    abstract fun navigate(activity: Activity)

    data object Close: Navigation() {
        override fun navigate(activity: Activity) {
            activity.finish()
        }
    }

    data class AddCode(
        val barcode: CodeModel? = null,
        val closeCurrent: Boolean = false
    ): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, AddCodeActivity::class.java)
            intent.putExtra("barcode", barcode)
            activity.startActivity(intent)
            if (closeCurrent) {
                activity.finish()
            }
        }
    }

    data object ScanCodeFromCamera: Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, ScanCodeActivity::class.java)
            activity.startActivity(intent)
        }
    }

    data object ScanCodeFromImage: Navigation() {
        override fun navigate(activity: Activity) {
            Toast.makeText(activity, "Not yet implemented", Toast.LENGTH_SHORT).show()
        }
    }

    data class ShowCode(val code: CodeModel): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, ShowCodeActivity::class.java)
            intent.putExtra("code", code)
            activity.startActivity(intent)
        }
    }
}