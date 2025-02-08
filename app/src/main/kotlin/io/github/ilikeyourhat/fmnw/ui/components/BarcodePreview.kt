package io.github.ilikeyourhat.fmnw.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType
import io.github.ilikeyourhat.fmnw.R
import io.github.ilikeyourhat.fmnw.model.BarcodeContainer
import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.github.ilikeyourhat.fmnw.ui.theme.AppTheme
import io.github.ilikeyourhat.fmnw.ui.theme.Typography

@Composable
fun BarcodePreview(
    barcodeContainer: BarcodeContainer,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(Color.White)
                .padding(16.dp)
        ) {
            val type = barcodeContainer.barcodeType
            if (type != null) {
                BarcodeWrapper(
                    type = type,
                    value = barcodeContainer.value
                )
            }
            Text(
                text = barcodeContainer.value,
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
    value: String
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .defaultMinSize(100.dp, 100.dp)
    ) {
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
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Barcode(
                type = type.toUiType(),
                value = value,
                showProgress = true,
                resolutionFactor = 10,
                modifier = Modifier.fillMaxSize()
            )
        }
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
private fun BarcodePreview_1D() {
    AppTheme {
        BarcodePreview(
            object : BarcodeContainer {
                override val value = "12345"
                override val barcodeType = BarcodeModelType.ITF
            }
        )
    }
}

@Preview
@Composable
private fun BarcodePreview_2D() {
    AppTheme {
        BarcodePreview(
            object : BarcodeContainer {
                override val value = "12345"
                override val barcodeType = BarcodeModelType.QR_CODE
            }
        )
    }
}

@Preview
@Composable
private fun BarcodePreview_2D_long() {
    AppTheme {
        BarcodePreview(
            object : BarcodeContainer {
                override val value = "12345678901234"
                override val barcodeType = BarcodeModelType.QR_CODE
            },
            modifier = Modifier
                .width(200.dp)
        )
    }
}

@Preview
@Composable
private fun BarcodePreview_text() {
    AppTheme {
        BarcodePreview(
            object : BarcodeContainer {
                override val value = "12345678"
                override val barcodeType = null
            }
        )
    }
}

@Preview
@Composable
private fun BarcodePreview_text_multiline() {
    AppTheme {
        BarcodePreview(
            object : BarcodeContainer {
                override val value = """
                    some text
                    that should be displayed
                    in multiple lines
                """.trimIndent()
                override val barcodeType = null
            }
        )
    }
}

@Preview
@Composable
private fun BarcodePreview_onSurface() {
    AppTheme {
        Surface {
            BarcodePreview(
                object : BarcodeContainer {
                    override val value = "12345"
                    override val barcodeType = BarcodeModelType.QR_CODE
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
