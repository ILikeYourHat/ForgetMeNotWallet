package com.github.ilikeyourhat.fmnw.ui.screen.scancode

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanCodeActivity : AppCompatActivity() {

    private val viewModel: ScanCodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.uiState.observe(this) { state ->
            setContent {
                ScanCodeScreen(state, viewModel)
            }
        }
        viewModel.router.attachTo(this)
    }
}