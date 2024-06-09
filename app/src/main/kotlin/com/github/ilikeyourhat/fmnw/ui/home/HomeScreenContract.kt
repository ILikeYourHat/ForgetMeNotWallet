package com.github.ilikeyourhat.fmnw.ui.home

data class HomeScreenState(
    val codes: List<String> = emptyList()
)

interface HomeScreenEvents {
    fun onAddCodeClicked()

    companion object {
        val DUMMY = object : HomeScreenEvents {
            override fun onAddCodeClicked() = Unit
        }
    }
}