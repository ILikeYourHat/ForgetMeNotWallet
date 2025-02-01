package io.github.ilikeyourhat.fmnw.ui.screen.edit.note

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.model.Note
import io.github.ilikeyourhat.fmnw.ui.navigation.Navigation.Companion.KEY_BARCODE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNoteActivity : AppCompatActivity() {

    private val viewModel: EditNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.screen.observe(this) { state ->
            setContent {
                EditNoteScreen(state, viewModel)
            }
        }
        viewModel.router.attachTo(this)
    }

    companion object {
        fun intentCreate(activity: Activity, parentGroup: Group?): Intent {
            val intent = Intent(activity, EditNoteActivity::class.java)
            val note = Note(groupId = parentGroup?.id)
            intent.putExtra(KEY_BARCODE, note)
            return intent
        }

        fun intentEdit(activity: Activity, note: Note): Intent {
            val intent = Intent(activity, EditNoteActivity::class.java)
            intent.putExtra(KEY_BARCODE, note)
            return intent
        }
    }
}