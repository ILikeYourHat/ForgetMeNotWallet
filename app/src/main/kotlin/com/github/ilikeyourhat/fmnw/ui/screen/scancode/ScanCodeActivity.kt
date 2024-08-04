package com.github.ilikeyourhat.fmnw.ui.screen.scancode

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanCodeActivity : AppCompatActivity() {

    private val viewModel: ScanCodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.uiState.observe(this) { state ->
            setContent {
                ScanCodeScreen(state, viewModel)
                if (!state.permissionGranted) {
                    handleCameraPermission()
                }
            }
        }
        viewModel.router.attachTo(this)
    }

    // Declare a launcher for the camera permission request, handling the permission result
    private val cameraPermissionRequestLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted, update the state
                viewModel.onPermissionGranted()
            } else {
                // Permission denied: inform the user to enable it through settings
                Toast.makeText(
                    this,
                    "Go to settings and enable camera permission to use this feature",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    // Checks camera permission and either starts the camera directly or requests permission
    private fun handleCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Permission is already granted, update the state
                viewModel.onPermissionGranted()
            }

            else -> {
                // Permission is not granted: request it
                cameraPermissionRequestLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}