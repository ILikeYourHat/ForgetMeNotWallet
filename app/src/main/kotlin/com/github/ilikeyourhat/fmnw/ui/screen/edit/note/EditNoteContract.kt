package com.github.ilikeyourhat.fmnw.ui.screen.edit.note

import com.github.ilikeyourhat.fmnw.model.Note

data class EditNoteScreenState(
    val note: Note = Note()
)

interface EditNoteEvents {
    fun onNameChanged(name: String)
    fun onValueChanged(value: String)
    fun onDoneClicked()
    fun onCloseClicked()

    companion object {
        val DUMMY = object : EditNoteEvents {
            override fun onNameChanged(name: String) = Unit
            override fun onValueChanged(value: String) = Unit
            override fun onDoneClicked() = Unit
            override fun onCloseClicked() = Unit
        }
    }
}