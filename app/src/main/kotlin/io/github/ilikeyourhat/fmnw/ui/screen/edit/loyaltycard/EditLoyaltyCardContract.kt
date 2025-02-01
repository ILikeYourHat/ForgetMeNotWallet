package io.github.ilikeyourhat.fmnw.ui.screen.edit.loyaltycard

import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard

data class EditLoyaltyCardScreenState(
    val loyaltyCard: LoyaltyCard = LoyaltyCard()
)

interface EditLoyaltyCardEvents {
    fun onNameChanged(name: String)
    fun onFormatChanged(format: BarcodeModelType?)
    fun onValueChanged(value: String)
    fun onDoneClicked()
    fun onCloseClicked()

    companion object {
        val DUMMY = object : EditLoyaltyCardEvents {
            override fun onNameChanged(name: String) = Unit
            override fun onFormatChanged(format: BarcodeModelType?) = Unit
            override fun onValueChanged(value: String) = Unit
            override fun onDoneClicked() = Unit
            override fun onCloseClicked() = Unit
        }
    }
}