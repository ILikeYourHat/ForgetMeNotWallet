package com.github.ilikeyourhat.fmnw.ui.screen.showcode

import com.github.ilikeyourhat.fmnw.model.CodeModel

data class ShowCodeScreenState(
    val code: CodeModel
)

interface ShowCodeScreenEvents {
    fun onCloseClicked()

    companion object {
        val DUMMY = object : ShowCodeScreenEvents {
            override fun onCloseClicked() = Unit
        }
    }
}