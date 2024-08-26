package com.github.ilikeyourhat.fmnw.ui.screen.addcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.domain.DatabaseRepository
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType
import com.github.ilikeyourhat.fmnw.model.CodeModel
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCodeViewModel @Inject constructor(
    val router: Router,
    savedStateHandle: SavedStateHandle,
    private val repository: DatabaseRepository
) : ViewModel(), AddCodeEvents {

    private val _screen = MutableLiveData(
        AddCodeScreenState(
            barcode = savedStateHandle[Navigation.KEY_BARCODE] ?: CodeModel(),
        )
    )
    val screen: LiveData<AddCodeScreenState> = _screen

    override fun onCodeNameChanged(name: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            barcode = state.barcode.copy(name = name)
        )
    }

    override fun onCodeFormatChanged(format: BarcodeModelType?) {
        val state = _screen.value!!
        _screen.value = state.copy(
            barcode = state.barcode.copy(type = format)
        )
    }

    override fun onCodeValueChanged(value: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            barcode = state.barcode.copy(value = value)
        )
    }

    override fun onDoneClicked() {
        viewModelScope.launch {
            val codeModel = _screen.value!!.barcode
            repository.save(codeModel)
            router.navigate(Navigation.Close)
        }
    }

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }
}