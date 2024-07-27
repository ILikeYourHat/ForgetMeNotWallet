package com.github.ilikeyourhat.fmnw.ui.screen.home

data class HomeScreenState(
    val codes: List<CodeState> = emptyList()
)

data class CodeState(
    val id: Int,
    val name: String,
    val value: String
)

interface HomeScreenEvents {
    fun onAddCodeClicked()
    fun onDeleteCodeClicked(code: CodeState)

    companion object {
        val DUMMY = object : HomeScreenEvents {
            override fun onAddCodeClicked() = Unit
            override fun onDeleteCodeClicked(code: CodeState) = Unit
        }
    }
}