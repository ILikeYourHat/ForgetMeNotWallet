package com.github.ilikeyourhat.fmnw.ui.screen.addcode

data class AddCodeScreenState(
    val code: String = ""
)

interface AddCodeEvents {
    fun onCodeChanged(code: String)
    fun onDoneClicked()
    fun onCloseClicked()

    companion object {
        val DUMMY = object : AddCodeEvents {
            override fun onCodeChanged(code: String) = Unit
            override fun onDoneClicked() = Unit
            override fun onCloseClicked() = Unit
        }
    }
}