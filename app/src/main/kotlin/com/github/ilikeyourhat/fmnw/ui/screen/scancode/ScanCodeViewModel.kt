package com.github.ilikeyourhat.fmnw.ui.screen.scancode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ilikeyourhat.fmnw.model.BarcodeModel
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Router
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.common.Barcode.BarcodeFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanCodeViewModel @Inject constructor(
    val router: Router
) : ViewModel(), ScanCodeScreenEvents {

    private val _uiState = MutableLiveData(
        ScanCodeScreenState()
    )
    val uiState: LiveData<ScanCodeScreenState> = _uiState

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }

    override fun onBarcodeDetected(barcode: Barcode) {
        if (_uiState.value!!.barcodeDetected) return

        val barcodeModel = barcode.toBarcodeModel()
        if (barcodeModel != null) {
            _uiState.value = _uiState.value!!.copy(barcodeDetected = true)
            router.navigate(Navigation.AddCode(barcodeModel, closeCurrent = true))
        }
    }

    fun onPermissionGranted() {
        _uiState.value = _uiState.value!!.copy(
            permissionGranted = true
        )
    }

    private fun Barcode.toBarcodeModel(): BarcodeModel? {
        val type = format.toBarcodeType() ?: return null
        val value = rawValue ?: return null
        return BarcodeModel(type, value)
    }

    private fun @receiver:BarcodeFormat Int.toBarcodeType(): BarcodeModelType? {
        return when (this) {
            Barcode.FORMAT_CODE_128 -> BarcodeModelType.CODE_128
            Barcode.FORMAT_CODE_39 -> BarcodeModelType.CODE_39
            Barcode.FORMAT_CODE_93 -> BarcodeModelType.CODE_93
            Barcode.FORMAT_CODABAR -> BarcodeModelType.CODABAR
            Barcode.FORMAT_DATA_MATRIX -> BarcodeModelType.DATA_MATRIX
            Barcode.FORMAT_EAN_13 -> BarcodeModelType.EAN_13
            Barcode.FORMAT_EAN_8 -> BarcodeModelType.EAN_13
            Barcode.FORMAT_ITF -> BarcodeModelType.ITF
            Barcode.FORMAT_QR_CODE -> BarcodeModelType.QR_CODE
            Barcode.FORMAT_UPC_A -> BarcodeModelType.UPC_A
            Barcode.FORMAT_UPC_E -> BarcodeModelType.UPC_E
            Barcode.FORMAT_PDF417 -> BarcodeModelType.PDF_417
            Barcode.FORMAT_AZTEC -> BarcodeModelType.AZTEC
            else -> null
        }
    }
}