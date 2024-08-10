package com.github.ilikeyourhat.fmnw.ui.screen.home

import com.github.ilikeyourhat.fmnw.model.CodeModel

data class HomeScreenState(
    val codes: List<CodeModel> = emptyList()
)

interface HomeScreenEvents {
    fun onAddTextCodeClicked()
    fun onScanBarcodeFromCameraClicked()
    fun onScanBarcodeFromImageClicked()
    fun onShowCodeClicked(code: CodeModel)
    fun onDeleteCodeClicked(code: CodeModel)

    companion object {
        val DUMMY = object : HomeScreenEvents {
            override fun onAddTextCodeClicked() = Unit
            override fun onScanBarcodeFromCameraClicked() = Unit
            override fun onScanBarcodeFromImageClicked() = Unit
            override fun onShowCodeClicked(code: CodeModel) = Unit
            override fun onDeleteCodeClicked(code: CodeModel) = Unit
        }
    }
}