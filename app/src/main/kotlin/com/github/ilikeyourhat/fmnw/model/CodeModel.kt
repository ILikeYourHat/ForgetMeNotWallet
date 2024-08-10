package com.github.ilikeyourhat.fmnw.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CodeModel (
    val id: Int,
    val name: String,
    val value: String,
    val type: BarcodeModelType?
) : Parcelable