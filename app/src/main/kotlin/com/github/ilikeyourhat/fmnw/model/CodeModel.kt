package com.github.ilikeyourhat.fmnw.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CodeModel (
    val id: Int? = null,
    val name: String = "",
    val value: String,
    val type: BarcodeModelType?
) : Parcelable {
    fun isPersisted() = id != null
}