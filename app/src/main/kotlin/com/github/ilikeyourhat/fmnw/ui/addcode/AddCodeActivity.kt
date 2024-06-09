package com.github.ilikeyourhat.fmnw.ui.addcode

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCodeActivity : AppCompatActivity() {

    private val viewModel: AddCodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.screen.observe(this) { state ->
            setContent {
                AddCodeScreen(state, viewModel)
            }
        }
        viewModel.router.attachTo(this)
    }
}