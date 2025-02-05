package io.github.ilikeyourhat.fmnw.ui.screen.showcode

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowCodeActivity : AppCompatActivity() {

    private val viewModel: ShowCodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.uiState.observe(this) { state ->
            setContent {
                ShowCodeScreen(
                    state = state,
                    events = viewModel
                )
            }
        }
        viewModel.router.attachTo(this)
    }
}