package com.github.ilikeyourhat.fmnw.ui.screen.home

import com.github.ilikeyourhat.fmnw.model.WalletItem

data class HomeScreenState(
    val items: List<WalletItem> = emptyList()
)

interface HomeScreenEvents {
    fun onAddTextCodeClicked()
    fun onScanBarcodeFromCameraClicked()
    fun onScanBarcodeFromImageClicked()
    fun onShowCodeClicked(item: WalletItem)
    fun onEditCodeClicked(item: WalletItem)
    fun onDeleteCodeClicked(item: WalletItem)
    fun onAddLoyaltyCardClicked()
    fun onAddNoteClicked()
    fun onAddGroupClicked()

    companion object {
        val DUMMY = object : HomeScreenEvents {
            override fun onAddTextCodeClicked() = Unit
            override fun onScanBarcodeFromCameraClicked() = Unit
            override fun onScanBarcodeFromImageClicked() = Unit
            override fun onShowCodeClicked(item: WalletItem) = Unit
            override fun onEditCodeClicked(item: WalletItem) = Unit
            override fun onDeleteCodeClicked(item: WalletItem) = Unit
            override fun onAddLoyaltyCardClicked() = Unit
            override fun onAddNoteClicked() = Unit
            override fun onAddGroupClicked() = Unit
        }
    }
}