package com.github.ilikeyourhat.fmnw.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ilikeyourhat.fmnw.R
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.core.theme.AppTheme
import com.github.ilikeyourhat.fmnw.ui.core.theme.Typography
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType

@Composable
fun BarcodePreview(
    barcodeModel: CodeModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .shadow(4.dp)
                .background(Color.White)
                .padding(16.dp)
        ) {
            if (barcodeModel.type != null) {
                BarcodeWrapper(
                    type = barcodeModel.type,
                    value = barcodeModel.value,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = barcodeModel.value,
                style = Typography.titleLarge,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun BarcodeWrapper(
    type: BarcodeModelType,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (LocalInspectionMode.current) {
            val placeholder = if (type.isSquare) {
                R.drawable.placeholder_2d_barcode
            } else {
                R.drawable.placeholder_1d_barcode
            }
            Image(
                painter = painterResource(placeholder),
                contentDescription = value,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .matchBarcodeType(type)
            )
        } else {
            Barcode(
                type = type.toUiType(),
                value = value,
                showProgress = true,
                modifier = Modifier
                    .matchBarcodeType(type)
            )
        }
    }
}

private fun Modifier.matchBarcodeType(type: BarcodeModelType): Modifier {
    return if (type.isSquare) {
        aspectRatio(1f)
    } else {
        fillMaxWidth()
            .height(200.dp)
    }
}

private fun BarcodeModelType.toUiType(): BarcodeType {
    return when (this) {
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

@Preview
@Composable
fun BarcodePreview_1D() {
    AppTheme {
        BarcodePreview(
            CodeModel(
                value = "12345",
                type = BarcodeModelType.ITF
            )
        )
    }
}

@Preview
@Composable
fun BarcodePreview_2D() {
    AppTheme {
        BarcodePreview(
            CodeModel(
                value = "12345",
                type = BarcodeModelType.QR_CODE
            )
        )
    }
}

@Preview
@Composable
fun BarcodePreview_2D_long() {
    AppTheme {
        BarcodePreview(
            CodeModel(
                value = "12345678901234",
                type = BarcodeModelType.QR_CODE
            ),
            modifier = Modifier
                .width(200.dp)
        )
    }
}

@Preview
@Composable
fun BarcodePreview_text() {
    AppTheme {
        BarcodePreview(
            CodeModel(
                value = "12345678",
                type = null
            )
        )
    }
}

@Preview
@Composable
fun BarcodePreview_text_multiline() {
    AppTheme {
        BarcodePreview(
            CodeModel(
                value = """
                    some text
                    that should be displayed
                    in multiple lines
                """.trimIndent(),
                type = null
            )
        )
    }
}

@Preview
@Composable
fun BarcodePreview_adjustToSize() {
    AppTheme {
        BarcodePreview(
            CodeModel(
                value = """
                    some text
                    that should be displayed
                    in multiple lines
                """.trimIndent(),
                type = null
            )
        )
    }
}