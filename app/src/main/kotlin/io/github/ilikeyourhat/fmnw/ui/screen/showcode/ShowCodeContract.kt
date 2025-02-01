package io.github.ilikeyourhat.fmnw.ui.screen.showcode

import io.github.ilikeyourhat.fmnw.model.WalletItem

data class ShowCodeScreenState(
    val item: WalletItem
)

interface ShowCodeScreenEvents {
    fun onCloseClicked()

    companion object {
        val DUMMY = object : ShowCodeScreenEvents {
            override fun onCloseClicked() = Unit
        }
    }
}