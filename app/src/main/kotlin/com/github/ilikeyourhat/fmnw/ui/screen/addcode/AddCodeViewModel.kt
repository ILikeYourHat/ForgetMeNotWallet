package com.github.ilikeyourhat.fmnw.ui.screen.addcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.db.StoredCode
import com.github.ilikeyourhat.fmnw.db.StoredCodeDao
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.core.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCodeViewModel @Inject constructor(
    val router: Router,
    private val storedCodeDao: StoredCodeDao
): ViewModel(), AddCodeEvents {

    private val _screen = MutableLiveData(
        AddCodeScreenState()
    )
    val screen: LiveData<AddCodeScreenState> = _screen

    override fun onCodeNameChanged(name: String) {
        _screen.value = _screen.value!!.copy(name = name)
    }

    override fun onCodeValueChanged(value: String) {
        _screen.value = _screen.value!!.copy(value = value)
    }

    override fun onDoneClicked() {
        viewModelScope.launch {
            val state = _screen.value!!
            storedCodeDao.insert(
                StoredCode(
                    name = state.name,
                    value = state.value
                )
            )
            router.navigate(Navigation.Close)
        }
    }

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }
}