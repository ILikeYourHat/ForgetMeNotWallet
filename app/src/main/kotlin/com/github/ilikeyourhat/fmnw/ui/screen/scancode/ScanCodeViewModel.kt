package com.github.ilikeyourhat.fmnw.ui.screen.scancode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ilikeyourhat.fmnw.model.BarcodeModel
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Router
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanCodeViewModel @Inject constructor(
    val router: Router
): ViewModel(), ScanCodeScreenEvents {

    private val _uiState = MutableLiveData(
        ScanCodeScreenState()
    )
    val uiState: LiveData<ScanCodeScreenState> = _uiState

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }

    override fun onBarcodeDetected(barcode: Barcode) {
        if (_uiState.value!!.barcodeDetected) return
        _uiState.value = _uiState.value!!.copy(barcodeDetected = true)

        val type = "qr_code"
        val value = barcode.rawValue ?: return
        val model = BarcodeModel(type, value)
        router.navigate(Navigation.AddCode(model, closeCurrent = true))
    }

    fun onPermissionGranted() {
        _uiState.value = _uiState.value!!.copy(
            permissionGranted = true
        )
    }
}