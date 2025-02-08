package io.github.ilikeyourhat.fmnw.model

import androidx.annotation.StringRes
import io.github.ilikeyourhat.fmnw.R
import java.util.Locale

enum class BarcodeModelType(
    val isSquare: Boolean,
    @StringRes val nameStringRes: Int
) {
    EAN_8(false, R.string.barcodeType_ean8),
    UPC_E(false, R.string.barcodeType_upcE),
    EAN_13(false, R.string.barcodeType_ean13),
    UPC_A(false, R.string.barcodeType_upcA),
    QR_CODE(true, R.string.barcodeType_qrCode),
    CODE_39(false, R.string.barcodeType_code39),
    CODE_93(false, R.string.barcodeType_code93),
    CODE_128(false, R.string.barcodeType_code128),
    ITF(false, R.string.barcodeType_itf),
    PDF_417(false, R.string.barcodeType_pdf417),
    CODABAR(false, R.string.barcodeType_codabar),
    DATA_MATRIX(true, R.string.barcodeType_dataMatrix),
    AZTEC(true, R.string.barcodeType_aztec);

    override fun toString(): String {
        return super.toString().lowercase(Locale.ROOT)
    }
}
