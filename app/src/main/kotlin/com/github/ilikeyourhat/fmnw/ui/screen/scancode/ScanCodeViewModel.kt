package com.github.ilikeyourhat.fmnw.ui.screen.scancode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanCodeViewModel @Inject constructor(
    val router: Router
): ViewModel(), ScanCodeScreenEvents {

    private val _uiState = MutableLiveData(
        ScanCodeScreenState()
    )
    val uiState: LiveData<ScanCodeScreenState> = _uiState

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }
}