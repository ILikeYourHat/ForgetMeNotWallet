package io.github.ilikeyourhat.fmnw.ui.screen.edit.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ilikeyourhat.fmnw.domain.DatabaseRepository
import io.github.ilikeyourhat.fmnw.model.Note
import io.github.ilikeyourhat.fmnw.ui.navigation.Navigation
import io.github.ilikeyourhat.fmnw.ui.navigation.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    val router: Router,
    savedStateHandle: SavedStateHandle,
    private val repository: DatabaseRepository
) : ViewModel(), EditNoteEvents {

    private val _screen = MutableLiveData(
        EditNoteScreenState(
            note = savedStateHandle[Navigation.KEY_BARCODE] ?: Note(),
        )
    )
    val screen: LiveData<EditNoteScreenState> = _screen

    override fun onNameChanged(name: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            note = state.note.copy(name = name)
        )
    }

    override fun onValueChanged(value: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            note = state.note.copy(value = value)
        )
    }

    override fun onDoneClicked() {
        viewModelScope.launch {
            val loyaltyCard = _screen.value!!.note
            repository.save(loyaltyCard)
            router.navigate(Navigation.Close)
        }
    }

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }
}
