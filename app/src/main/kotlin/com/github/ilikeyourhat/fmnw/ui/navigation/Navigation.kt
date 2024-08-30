package com.github.ilikeyourhat.fmnw.ui.navigation

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.github.ilikeyourhat.fmnw.model.Group
import com.github.ilikeyourhat.fmnw.model.LoyaltyCard
import com.github.ilikeyourhat.fmnw.model.Note
import com.github.ilikeyourhat.fmnw.model.WalletItem
import com.github.ilikeyourhat.fmnw.ui.screen.edit.group.EditGroupActivity
import com.github.ilikeyourhat.fmnw.ui.screen.edit.loyaltycard.EditLoyaltyCardActivity
import com.github.ilikeyourhat.fmnw.ui.screen.edit.note.EditNoteActivity
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
        val item: WalletItem? = null,
        val closeCurrent: Boolean = false
    ): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, EditLoyaltyCardActivity::class.java)
            intent.putExtra(KEY_BARCODE, item)
            activity.startActivity(intent)
            if (closeCurrent) {
                activity.finish()
            }
        }
    }

    data object AddLoyaltyCard: Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, ScanCodeActivity::class.java)
            activity.startActivity(intent)
        }
    }

    data class EditLoyaltyCard(val card: LoyaltyCard): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, EditLoyaltyCardActivity::class.java)
            intent.putExtra(KEY_BARCODE, card)
            activity.startActivity(intent)
        }
    }

    data object AddNote: Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, EditNoteActivity::class.java)
            activity.startActivity(intent)
        }
    }

    data class EditNote(val note: Note): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, EditNoteActivity::class.java)
            intent.putExtra(KEY_BARCODE, note)
            activity.startActivity(intent)
        }
    }

    data object AddGroup: Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, EditGroupActivity::class.java)
            activity.startActivity(intent)
        }
    }

    data class EditGroup(val group: Group): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, EditGroupActivity::class.java)
            intent.putExtra(KEY_BARCODE, group)
            activity.startActivity(intent)
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

    data class ShowCode(val item: WalletItem): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, ShowCodeActivity::class.java)
            intent.putExtra(KEY_BARCODE, item)
            activity.startActivity(intent)
        }
    }

    companion object {
        const val KEY_BARCODE = "barcode"
    }
}