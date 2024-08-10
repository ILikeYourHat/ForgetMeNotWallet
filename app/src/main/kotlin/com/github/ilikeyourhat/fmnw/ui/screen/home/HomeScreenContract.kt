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
    fun onAddTextCodeClicked()
    fun onScanBarcodeFromCameraClicked()
    fun onScanBarcodeFromImageClicked()
    fun onDeleteCodeClicked(code: CodeState)

    companion object {
        val DUMMY = object : HomeScreenEvents {
            override fun onAddTextCodeClicked() = Unit
            override fun onScanBarcodeFromCameraClicked() = Unit
            override fun onScanBarcodeFromImageClicked() = Unit
            override fun onDeleteCodeClicked(code: CodeState) = Unit
        }
    }
}