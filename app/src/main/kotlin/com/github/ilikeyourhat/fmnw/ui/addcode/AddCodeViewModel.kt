package com.github.ilikeyourhat.fmnw.ui.addcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.db.StoredCode
import com.github.ilikeyourhat.fmnw.db.StoredCodeDao
import com.github.ilikeyourhat.fmnw.ui.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.navigation.Router
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

    override fun onCodeChanged(code: String) {
        _screen.value = AddCodeScreenState(code)
    }

    override fun onDoneClicked() {
        viewModelScope.launch {
            storedCodeDao.insert(StoredCode(code = _screen.value!!.code))
            router.navigate(Navigation.Close)
        }
    }
}