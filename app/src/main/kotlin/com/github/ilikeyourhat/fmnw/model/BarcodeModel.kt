package com.github.ilikeyourhat.fmnw.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BarcodeModel(
    val type: String,
    val value: String
) : Parcelable