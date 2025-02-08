package io.github.ilikeyourhat.fmnw.ui.screen.edit.group

import io.github.ilikeyourhat.fmnw.model.Group

data class EditGroupScreenState(
    val group: Group = Group()
)

interface EditGroupEvents {
    fun onNameChanged(name: String)
    fun onDoneClicked()
    fun onCloseClicked()

    companion object {
        val DUMMY = object : EditGroupEvents {
            override fun onNameChanged(name: String) = Unit
            override fun onDoneClicked() = Unit
            override fun onCloseClicked() = Unit
        }
    }
}
