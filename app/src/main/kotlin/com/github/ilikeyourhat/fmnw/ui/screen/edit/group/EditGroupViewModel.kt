package com.github.ilikeyourhat.fmnw.ui.screen.edit.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.domain.DatabaseRepository
import com.github.ilikeyourhat.fmnw.model.Group
import com.github.ilikeyourhat.fmnw.ui.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditGroupViewModel @Inject constructor(
    val router: Router,
    savedStateHandle: SavedStateHandle,
    private val repository: DatabaseRepository
) : ViewModel(), EditGroupEvents {

    private val _screen = MutableLiveData(
        EditGroupScreenState(
            group = savedStateHandle[Navigation.KEY_BARCODE] ?: Group(),
        )
    )
    val screen: LiveData<EditGroupScreenState> = _screen

    override fun onNameChanged(name: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            group = state.group.copy(name = name)
        )
    }

    override fun onDoneClicked() {
        viewModelScope.launch {
            val group = _screen.value!!.group
            repository.save(group)
            router.navigate(Navigation.Close)
        }
    }

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }
}