package io.github.ilikeyourhat.fmnw.ui.screen.home

import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.model.WalletItem

data class HomeScreenState(
    val group: Group? = null,
    val items: List<WalletItem> = emptyList()
)

interface HomeScreenEvents {
    fun onItemClicked(item: WalletItem)
    fun onEditItemClicked(item: WalletItem)
    fun onDeleteItemClicked(item: WalletItem)
    fun onAddLoyaltyCardClicked()
    fun onAddNoteClicked()
    fun onAddGroupClicked()
    fun onBackClicked()

    companion object {
        val DUMMY = object : HomeScreenEvents {
            override fun onItemClicked(item: WalletItem) = Unit
            override fun onEditItemClicked(item: WalletItem) = Unit
            override fun onDeleteItemClicked(item: WalletItem) = Unit
            override fun onAddLoyaltyCardClicked() = Unit
            override fun onAddNoteClicked() = Unit
            override fun onAddGroupClicked() = Unit
            override fun onBackClicked() = Unit
        }
    }
}