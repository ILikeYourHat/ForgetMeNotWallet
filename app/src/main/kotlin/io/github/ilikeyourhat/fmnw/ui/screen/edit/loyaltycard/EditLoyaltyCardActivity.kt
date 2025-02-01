package io.github.ilikeyourhat.fmnw.ui.screen.edit.loyaltycard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.ui.navigation.Navigation.Companion.KEY_BARCODE
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

    companion object {
        fun intentEdit(activity: Activity, loyaltyCard: LoyaltyCard): Intent {
            val intent = Intent(activity, EditLoyaltyCardActivity::class.java)
            intent.putExtra(KEY_BARCODE, loyaltyCard)
            return intent
        }
    }
}