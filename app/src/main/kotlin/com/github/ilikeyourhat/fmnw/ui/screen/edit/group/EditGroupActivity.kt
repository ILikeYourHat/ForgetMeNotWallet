package com.github.ilikeyourhat.fmnw.ui.screen.edit.group

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditGroupActivity : AppCompatActivity() {

    private val viewModel: EditGroupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.screen.observe(this) { state ->
            setContent {
                EditGroupScreen(state, viewModel)
            }
        }
        viewModel.router.attachTo(this)
    }
}