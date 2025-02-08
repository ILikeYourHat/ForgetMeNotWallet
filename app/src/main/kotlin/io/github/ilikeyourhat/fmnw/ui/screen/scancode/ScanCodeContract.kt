package io.github.ilikeyourhat.fmnw.ui.screen.scancode

import com.google.mlkit.vision.barcode.common.Barcode

data class ScanCodeScreenState(
    val permissionGranted: Boolean = false,
    val barcodeDetected: Boolean = false
)

interface ScanCodeScreenEvents {
    fun onCloseClicked()
    fun onBarcodeDetected(barcode: Barcode)
    fun onInputManuallyClicked()

    companion object {
        val DUMMY = object : ScanCodeScreenEvents {
            override fun onCloseClicked() = Unit
            override fun onBarcodeDetected(barcode: Barcode) = Unit
            override fun onInputManuallyClicked() = Unit
        }
    }
}
