package com.github.ilikeyourhat.fmnw.ui.screen.showcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowCodeViewModel @Inject constructor(
    val router: Router,
    savedStateHandle: SavedStateHandle
): ViewModel(), ShowCodeScreenEvents {

    private val code: CodeModel = savedStateHandle["code"]!!

    private val _uiState = MutableLiveData(
        ShowCodeScreenState(code)
    )
    val uiState: LiveData<ShowCodeScreenState> = _uiState

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }
}