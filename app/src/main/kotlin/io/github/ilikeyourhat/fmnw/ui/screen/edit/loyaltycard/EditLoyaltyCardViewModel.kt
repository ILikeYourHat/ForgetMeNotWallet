package io.github.ilikeyourhat.fmnw.ui.screen.edit.loyaltycard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.ilikeyourhat.fmnw.domain.DatabaseRepository
import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.ui.navigation.Navigation
import io.github.ilikeyourhat.fmnw.ui.navigation.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditLoyaltyCardViewModel @Inject constructor(
    val router: Router,
    savedStateHandle: SavedStateHandle,
    private val repository: DatabaseRepository
) : ViewModel(), EditLoyaltyCardEvents {

    private val _screen = MutableLiveData(
        EditLoyaltyCardScreenState(
            loyaltyCard = savedStateHandle[Navigation.KEY_BARCODE] ?: LoyaltyCard(),
        )
    )
    val screen: LiveData<EditLoyaltyCardScreenState> = _screen

    override fun onNameChanged(name: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            loyaltyCard = state.loyaltyCard.copy(name = name)
        )
    }

    override fun onFormatChanged(format: BarcodeModelType?) {
        val state = _screen.value!!
        _screen.value = state.copy(
            loyaltyCard = state.loyaltyCard.copy(barcodeType = format)
        )
    }

    override fun onValueChanged(value: String) {
        val state = _screen.value!!
        _screen.value = state.copy(
            loyaltyCard = state.loyaltyCard.copy(value = value)
        )
    }

    override fun onDoneClicked() {
        viewModelScope.launch {
            val loyaltyCard = _screen.value!!.loyaltyCard
            repository.save(loyaltyCard)
            router.navigate(Navigation.Close)
        }
    }

    override fun onCloseClicked() {
        router.navigate(Navigation.Close)
    }
}
