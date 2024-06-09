package com.github.ilikeyourhat.fmnw.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.screen.observe(this) { state ->
            setContent {
                HomeScreen(state, viewModel)
            }
        }
        viewModel.router.attachTo(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}