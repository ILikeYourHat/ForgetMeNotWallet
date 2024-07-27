package com.github.ilikeyourhat.fmnw.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.db.StoredCode
import com.github.ilikeyourhat.fmnw.db.StoredCodeDao
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
    private val storedCodeDao: StoredCodeDao
): ViewModel(), HomeScreenEvents {

    val uiState = storedCodeDao.getAll()
        .map { storedCodes -> storedCodes.map { it.toUiState() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
        .map { HomeScreenState(it) }
        .asLiveData()

    override fun onAddCodeClicked() {
        router.navigate(Navigation.AddCode)
    }

    override fun onDeleteCodeClicked(code: CodeState) {
        viewModelScope.launch {
            storedCodeDao.delete(code.id)
        }
    }

    private fun StoredCode.toUiState(): CodeState {
        return CodeState(
            id = id,
            name = name,
            value = value
        )
    }
}