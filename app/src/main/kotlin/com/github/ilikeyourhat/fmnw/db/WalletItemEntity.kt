package com.github.ilikeyourhat.fmnw.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ilikeyourhat.fmnw.model.BarcodeModelType

@Entity(tableName = "wallet")
data class WalletItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val type: Type,
    val name: String,
    val groupId: Long? = null,
    val value: String? = null,
    val barcodeType: BarcodeModelType? = null
) {
    enum class Type {
        LOYALTY_CARD, NOTE, GROUP
    }
}