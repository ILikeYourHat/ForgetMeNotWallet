package com.github.ilikeyourhat.fmnw.model

enum class BarcodeModelType(
    val isSquare: Boolean
) {
    EAN_8(false),
    UPC_E(false),
    EAN_13(false),
    UPC_A(false),
    QR_CODE(true),
    CODE_39(false),
    CODE_93(false),
    CODE_128(false),
    ITF(false),
    PDF_417(false),
    CODABAR(false),
    DATA_MATRIX(true),
    AZTEC(true)
}