package io.github.ilikeyourhat.fmnw.ui.components

import android.util.Size
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    onBarcodeFound: (Barcode) -> Unit = {}
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            val previewView = PreviewView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                // Preview is incorrectly scaled in Compose on some devices without this
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }

            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            val scanner = initBarcode()

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .build()
                    .also { it.surfaceProvider = previewView.surfaceProvider }

                val useCase = ImageAnalysis.Builder()
                    .setResolutionSelector(
                        ResolutionSelector.Builder()
                            .setResolutionStrategy(
                                ResolutionStrategy(
                                    Size(RESOLUTION_IMAGE_WIDTH, RESOLUTION_IMAGE_HEIGHT),
                                    ResolutionStrategy.FALLBACK_RULE_CLOSEST_HIGHER_THEN_LOWER
                                )
                            )
                            .build()
                    )
                    .build()
                    .apply {
                        setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
                            processImageProxy(scanner, imageProxy, onBarcodeFound)
                        }
                    }

                // Must unbind the use-cases before rebinding them.
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    useCase
                )
            }, ContextCompat.getMainExecutor(context))
            previewView
        }
    )
}

private fun initBarcode(): BarcodeScanner {
    val options = BarcodeScannerOptions.Builder().build()
    return BarcodeScanning.getClient(options)
}

@OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    barcodeScanner: BarcodeScanner,
    imageProxy: ImageProxy,
    barcodeCallback: (Barcode) -> Unit
) {
    imageProxy.image?.let { image ->
        val inputImage = InputImage.fromMediaImage(
            image,
            imageProxy.imageInfo.rotationDegrees
        )

        barcodeScanner.process(inputImage)
            .addOnSuccessListener { barcodeList ->
                barcodeList.getOrNull(0)
                    ?.let { barcodeCallback(it) }
            }
            .addOnCompleteListener {
                imageProxy.image?.close()
                imageProxy.close()
            }
    }
}

private const val RESOLUTION_IMAGE_WIDTH = 1000
private const val RESOLUTION_IMAGE_HEIGHT = 2500
