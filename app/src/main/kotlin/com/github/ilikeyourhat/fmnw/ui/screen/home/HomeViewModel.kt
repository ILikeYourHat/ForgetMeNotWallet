package com.github.ilikeyourhat.fmnw.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.domain.DatabaseRepository
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
    private val repository: DatabaseRepository
): ViewModel(), HomeScreenEvents {

    val uiState = repository.getContent()
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
        router.navigate(Navigation.ShowCode(code))
    }

    override fun onEditCodeClicked(code: CodeModel) {
        router.navigate(Navigation.AddCode(code))
    }

    override fun onDeleteCodeClicked(code: CodeModel) {
        viewModelScope.launch {
            repository.delete(code)
        }
    }
}