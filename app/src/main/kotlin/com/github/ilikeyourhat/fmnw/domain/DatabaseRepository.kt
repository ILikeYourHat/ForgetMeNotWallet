package com.github.ilikeyourhat.fmnw.domain

import com.github.ilikeyourhat.fmnw.db.StoredCode
import com.github.ilikeyourhat.fmnw.db.StoredCodeDao
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.model.CodeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val storedCodeDao: StoredCodeDao
) {

    suspend fun save(codeModel: CodeModel) {
        storedCodeDao.insertOrReplace(codeModel.toStoredCode())
    }

    fun getContent() : Flow<List<CodeModel>> {
        return storedCodeDao.getAll()
            .map { storedCodes -> storedCodes.map { it.toCodeModel() } }
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

    suspend fun delete(code: CodeModel) {
        storedCodeDao.delete(code.id!!)
    }
}