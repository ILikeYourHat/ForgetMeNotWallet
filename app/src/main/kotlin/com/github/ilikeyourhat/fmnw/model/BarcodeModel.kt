package com.github.ilikeyourhat.fmnw.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BarcodeModel(
    val type: BarcodeModelType?,
    val value: String
) : Parcelable

enum class BarcodeModelType {
    EAN_8,
    UPC_E,
    EAN_13,
    UPC_A,
    QR_CODE,
    CODE_39,
    CODE_93,
    CODE_128,
    ITF,
    PDF_417,
    CODABAR,
    DATA_MATRIX,
    AZTEC
}