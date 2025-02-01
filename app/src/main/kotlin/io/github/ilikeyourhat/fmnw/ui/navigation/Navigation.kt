package io.github.ilikeyourhat.fmnw.ui.navigation

import android.app.Activity
import android.content.Intent
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.model.Note
import io.github.ilikeyourhat.fmnw.model.WalletItem
import io.github.ilikeyourhat.fmnw.ui.screen.edit.group.EditGroupActivity
import io.github.ilikeyourhat.fmnw.ui.screen.edit.loyaltycard.EditLoyaltyCardActivity
import io.github.ilikeyourhat.fmnw.ui.screen.edit.note.EditNoteActivity
import io.github.ilikeyourhat.fmnw.ui.screen.home.HomeActivity
import io.github.ilikeyourhat.fmnw.ui.screen.scancode.ScanCodeActivity
import io.github.ilikeyourhat.fmnw.ui.screen.showcode.ShowCodeActivity

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

    data class AddLoyaltyCard(val parentGroup: Group?): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, ScanCodeActivity::class.java)
            intent.putExtra("parent_group", parentGroup)
            activity.startActivity(intent)
        }
    }

    data class EditLoyaltyCard(val card: LoyaltyCard): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = EditLoyaltyCardActivity.intentEdit(activity, card)
            activity.startActivity(intent)
        }
    }

    data class AddNote(val parentGroup: Group?): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = EditNoteActivity.intentCreate(activity, parentGroup)
            activity.startActivity(intent)
        }
    }

    data class EditNote(val note: Note): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = EditNoteActivity.intentEdit(activity, note)
            activity.startActivity(intent)
        }
    }

    data class AddGroup(val parentGroup: Group?): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = EditGroupActivity.intentCreate(activity, parentGroup)
            activity.startActivity(intent)
        }
    }

    data class EditGroup(val group: Group): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = EditGroupActivity.intentEdit(activity, group)
            activity.startActivity(intent)
        }
    }

    data class ShowCode(val item: WalletItem): Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, ShowCodeActivity::class.java)
            intent.putExtra(KEY_BARCODE, item)
            activity.startActivity(intent)
        }
    }

    data class ShowGroup(val item: Group) : Navigation() {
        override fun navigate(activity: Activity) {
            val intent = Intent(activity, HomeActivity::class.java)
            intent.putExtra("group", item)
            activity.startActivity(intent)
        }
    }

    companion object {
        const val KEY_BARCODE = "barcode"
    }
}