package io.github.ilikeyourhat.fmnw.ui.screen.edit.group

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.ui.navigation.Navigation.Companion.KEY_BARCODE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditGroupActivity : AppCompatActivity() {

    private val viewModel: EditGroupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.screen.observe(this) { state ->
            setContent {
                EditGroupScreen(
                    state = state,
                    events = viewModel
                )
            }
        }
        viewModel.router.attachTo(this)
    }

    companion object {
        fun intentCreate(activity: Activity, parentGroup: Group?): Intent {
            val intent = Intent(activity, EditGroupActivity::class.java)
            val group = Group(groupId = parentGroup?.id)
            intent.putExtra(KEY_BARCODE, group)
            return intent
        }

        fun intentEdit(activity: Activity, group: Group): Intent {
            val intent = Intent(activity, EditGroupActivity::class.java)
            intent.putExtra(KEY_BARCODE, group)
            return intent
        }
    }
}