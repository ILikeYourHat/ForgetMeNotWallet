package com.github.ilikeyourhat.fmnw.ui.screen.scancode

data class ScanCodeScreenState(
    val something: Boolean = true
)

interface ScanCodeScreenEvents {
    fun onCloseClicked()

    companion object {
        val DUMMY = object : ScanCodeScreenEvents {
            override fun onCloseClicked() = Unit
        }
    }
}