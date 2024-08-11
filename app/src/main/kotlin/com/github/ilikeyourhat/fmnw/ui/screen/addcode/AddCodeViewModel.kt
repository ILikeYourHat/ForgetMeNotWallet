package com.github.ilikeyourhat.fmnw.ui.screen.addcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.db.StoredCode
import com.github.ilikeyourhat.fmnw.db.StoredCodeDao
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCodeViewModel @Inject constructor(
    val router: Router,
    savedStateHandle: SavedStateHandle,
    private val storedCodeDao: StoredCodeDao
) : ViewModel(), AddCodeEvents {

    private val _screen = MutableLiveData(
        AddCodeScreenState(
            barcode = savedStateHandle[Navigation.KEY_BARCODE] ?: CodeModel(),
        )
    )
    val screen: LiveData<AddCodeScreenState> = _screen

    override fun onCodeNameChanged(name: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            barcode = state.barcode.copy(name = name)
        )
    }

    override fun onCodeValueChanged(value: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            barcode = state.barcode.copy(value = value)
        )
    }

    override fun onDoneClicked() {
        viewModelScope.launch {
            val codeModel = _screen.value!!.barcode
            storedCodeDao.insertOrReplace(codeModel.toStoredCode())
            router.navigate(Navigation.Close)
        }
    }

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }

    private fun CodeModel.toStoredCode(): StoredCode {
        return StoredCode(
            id = id ?: 0,
            name = name,
            value = value,
            type = type.toStoredType()
        )
    }

    private fun BarcodeModelType?.toStoredType(): String {
        return when (this) {
            BarcodeModelType.EAN_8 -> "ean_8"
            BarcodeModelType.UPC_E -> "upc_e"
            BarcodeModelType.EAN_13 -> "ean_13"
            BarcodeModelType.UPC_A -> "upc_a"
            BarcodeModelType.QR_CODE -> "qr_code"
            BarcodeModelType.CODE_39 -> "code_39"
            BarcodeModelType.CODE_93 -> "code_93"
            BarcodeModelType.CODE_128 -> "code_128"
            BarcodeModelType.ITF -> "itf"
            BarcodeModelType.PDF_417 -> "pdf_417"
            BarcodeModelType.CODABAR -> "codabar"
            BarcodeModelType.DATA_MATRIX -> "data_matrix"
            BarcodeModelType.AZTEC -> "aztec"
            else -> "raw_text"
        }
    }
}