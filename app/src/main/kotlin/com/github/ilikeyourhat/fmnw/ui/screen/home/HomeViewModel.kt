package com.github.ilikeyourhat.fmnw.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.domain.DatabaseRepository
import com.github.ilikeyourhat.fmnw.model.Group
import com.github.ilikeyourhat.fmnw.model.LoyaltyCard
import com.github.ilikeyourhat.fmnw.model.Note
import com.github.ilikeyourhat.fmnw.model.WalletItem
import com.github.ilikeyourhat.fmnw.ui.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.navigation.Router
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

    override fun onShowCodeClicked(item: WalletItem) {
        router.navigate(Navigation.ShowCode(item))
    }

    override fun onEditCodeClicked(item: WalletItem) {
        val navigationTarget = when(item) {
            is LoyaltyCard -> Navigation.EditLoyaltyCard(item)
            is Note -> Navigation.EditNote(item)
            is Group -> Navigation.EditGroup(item)
        }
        router.navigate(navigationTarget)
    }

    override fun onDeleteCodeClicked(item: WalletItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    override fun onAddLoyaltyCardClicked() {
        router.navigate(Navigation.AddLoyaltyCard)
    }

    override fun onAddNoteClicked() {
        router.navigate(Navigation.AddNote)
    }

    override fun onAddGroupClicked() {
        router.navigate(Navigation.AddGroup)
    }
}