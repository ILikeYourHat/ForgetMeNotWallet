package com.github.ilikeyourhat.fmnw.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.db.StoredCodeDao
import com.github.ilikeyourhat.fmnw.ui.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val router: Router,
    private val storedCodeDao: StoredCodeDao
): ViewModel(), HomeScreenEvents {

    private val _screen = MutableLiveData(HomeScreenState())
    val screen: LiveData<HomeScreenState> = _screen

    override fun onAddCodeClicked() {
        router.navigate(Navigation.AddCode)
    }

    fun onResume() {
        viewModelScope.launch {
            val codes = storedCodeDao.getAll().map { it.code }
            _screen.postValue(HomeScreenState(codes))
        }
    }
}