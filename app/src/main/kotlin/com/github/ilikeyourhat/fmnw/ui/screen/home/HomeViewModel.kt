package com.github.ilikeyourhat.fmnw.ui.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.ilikeyourhat.fmnw.domain.DatabaseRepository
import com.github.ilikeyourhat.fmnw.model.Group
import com.github.ilikeyourhat.fmnw.model.LoyaltyCard
import com.github.ilikeyourhat.fmnw.model.Note
import com.github.ilikeyourhat.fmnw.model.WalletItem
import com.github.ilikeyourhat.fmnw.ui.navigation.Navigation
import com.github.ilikeyourhat.fmnw.ui.navigation.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val router: Router,
    savedStateHandle: SavedStateHandle,
    private val repository: DatabaseRepository
): ViewModel(), HomeScreenEvents {

    private val group: Group? = savedStateHandle["group"]

    val uiState = repository.getGroupContent(group)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
        .map { HomeScreenState(group, it) }
        .asLiveData()

    override fun onItemClicked(item: WalletItem) {
        if (item is Group) {
            router.navigate(Navigation.ShowGroup(item))
        } else {
            router.navigate(Navigation.ShowCode(item))
        }
    }

    override fun onEditItemClicked(item: WalletItem) {
        val navigationTarget = when(item) {
            is LoyaltyCard -> Navigation.EditLoyaltyCard(item)
            is Note -> Navigation.EditNote(item)
            is Group -> Navigation.EditGroup(item)
        }
        router.navigate(navigationTarget)
    }

    override fun onDeleteItemClicked(item: WalletItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    override fun onAddLoyaltyCardClicked() {
        router.navigate(Navigation.AddLoyaltyCard(group))
    }

    override fun onAddNoteClicked() {
        router.navigate(Navigation.AddNote(group))
    }

    override fun onAddGroupClicked() {
        router.navigate(Navigation.AddGroup(group))
    }

    override fun onBackClicked() {
        router.navigate(Navigation.Close)
    }
}