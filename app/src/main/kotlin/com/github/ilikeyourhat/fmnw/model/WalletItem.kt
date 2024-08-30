package com.github.ilikeyourhat.fmnw.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class WalletItem : Parcelable {
    abstract val id: Long?
    abstract val name: String
    abstract val groupId: Long?

    fun isPersisted() = id != null
}

@Parcelize
data class LoyaltyCard(
    override val id: Long? = null,
    override val name: String = "",
    override val groupId: Long? = null,
    override val value: String = "",
    override val barcodeType: BarcodeModelType? = null,
) : WalletItem(), BarcodeContainer

@Parcelize
data class Note(
    override val id: Long? = null,
    override val name: String = "",
    override val groupId: Long? = null,
    val value: String = "",
) : WalletItem()

@Parcelize
data class Group(
    override val id: Long? = null,
    override val name: String = "",
    override val groupId: Long? = null,
) : WalletItem()

interface BarcodeContainer {
    val value: String
    val barcodeType: BarcodeModelType?
}