package com.github.ilikeyourhat.fmnw.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.core.theme.Typography
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType

@Composable
fun BarcodePreview(
    barcodeModel: CodeModel,
    modifier: Modifier = Modifier
) {
    val type = barcodeModel.type?.toUiType()
    if (type != null) {
        Barcode(
            modifier = modifier,
            type = type,
            value = barcodeModel.value
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            Text(
                text = barcodeModel.value,
                style = Typography.titleLarge
            )
        }
    }
}

private fun BarcodeModelType.toUiType() : BarcodeType {
    return when(this) {
        BarcodeModelType.EAN_8 -> BarcodeType.EAN_8
        BarcodeModelType.UPC_E -> BarcodeType.UPC_E
        BarcodeModelType.EAN_13 -> BarcodeType.EAN_13
        BarcodeModelType.UPC_A -> BarcodeType.UPC_A
        BarcodeModelType.QR_CODE -> BarcodeType.QR_CODE
        BarcodeModelType.CODE_39 -> BarcodeType.CODE_39
        BarcodeModelType.CODE_93 -> BarcodeType.CODE_93
        BarcodeModelType.CODE_128 -> BarcodeType.CODE_128
        BarcodeModelType.ITF -> BarcodeType.ITF
        BarcodeModelType.PDF_417 -> BarcodeType.PDF_417
        BarcodeModelType.CODABAR -> BarcodeType.CODABAR
        BarcodeModelType.DATA_MATRIX -> BarcodeType.DATA_MATRIX
        BarcodeModelType.AZTEC -> BarcodeType.AZTEC
    }
}