package com.github.ilikeyourhat.fmnw.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.db.StoredCode
import com.github.ilikeyourhat.fmnw.db.StoredCodeDao
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val router: Router,
    private val storedCodeDao: StoredCodeDao
): ViewModel(), HomeScreenEvents {

    val uiState = storedCodeDao.getAll()
        .map { storedCodes -> storedCodes.map { it.toCodeModel() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
        .map { HomeScreenState(it) }
        .asLiveData()

    override fun onAddTextCodeClicked() {
        router.navigate(Navigation.AddCode())
    }

    override fun onScanBarcodeFromCameraClicked() {
        router.navigate(Navigation.ScanCodeFromCamera)
    }

    override fun onScanBarcodeFromImageClicked() {
        router.navigate(Navigation.ScanCodeFromImage)
    }

    override fun onShowCodeClicked(code: CodeModel) {
        val codeModel = CodeModel(
            id = code.id,
            name = code.name,
            value = code.value,
            type = code.type
        )
        router.navigate(Navigation.ShowCode(codeModel))
    }

    override fun onDeleteCodeClicked(code: CodeModel) {
        viewModelScope.launch {
            storedCodeDao.delete(code.id)
        }
    }

    private fun StoredCode.toCodeModel(): CodeModel {
        return CodeModel(
            id = id,
            name = name,
            type = type.toBarcodeModelType(),
            value = value
        )
    }

    private fun String.toBarcodeModelType(): BarcodeModelType? {
        return when(this) {
            "ean_8" -> BarcodeModelType.EAN_8
            "upc_e" -> BarcodeModelType.UPC_E
            "ean_13" -> BarcodeModelType.EAN_13
            "upc_a" -> BarcodeModelType.UPC_A
            "qr_code" -> BarcodeModelType.QR_CODE
            "code_39" -> BarcodeModelType.CODE_39
            "code_93" -> BarcodeModelType.CODE_93
            "code_128" -> BarcodeModelType.CODE_128
            "itf" -> BarcodeModelType.ITF
            "pdf_417" -> BarcodeModelType.PDF_417
            "codabar" -> BarcodeModelType.CODABAR
            "data_matrix" -> BarcodeModelType.DATA_MATRIX
            "aztec" -> BarcodeModelType.AZTEC
            "raw_text" -> null
            else -> null
        }
    }
}