package com.github.ilikeyourhat.fmnw.ui.screen.edit.loyaltycard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditLoyaltyCardActivity : AppCompatActivity() {

    private val viewModel: EditLoyaltyCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.screen.observe(this) { state ->
            setContent {
                EditLoyaltyCardScreen(state, viewModel)
            }
        }
        viewModel.router.attachTo(this)
    }
}