package com.github.ilikeyourhat.fmnw.ui.addcode

data class AddCodeScreenState(
    val code: String = ""
)

interface AddCodeEvents {
    fun onCodeChanged(code: String)
    fun onDoneClicked()

    companion object {
        val DUMMY = object : AddCodeEvents {
            override fun onCodeChanged(code: String) = Unit
            override fun onDoneClicked() = Unit
        }
    }
}