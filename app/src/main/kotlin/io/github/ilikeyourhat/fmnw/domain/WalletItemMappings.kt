package io.github.ilikeyourhat.fmnw.domain

import io.github.ilikeyourhat.fmnw.db.WalletItemEntity
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.model.Note
import io.github.ilikeyourhat.fmnw.model.WalletItem

fun WalletItemEntity.toDomainModel(): WalletItem {
    return when(type) {
        WalletItemEntity.Type.LOYALTY_CARD -> toLoyaltyCardDomainModel()
        WalletItemEntity.Type.NOTE -> toNoteDomainModel()
        WalletItemEntity.Type.GROUP -> toGroupDomainModel()
    }
}

private fun WalletItemEntity.toLoyaltyCardDomainModel() = LoyaltyCard(
    id = id,
    name = name,
    groupId = groupId,
    value = value!!,
    barcodeType = barcodeType,
)

private fun WalletItemEntity.toNoteDomainModel() = Note(
    id = id,
    name = name,
    groupId = groupId,
    value = value!!,
)

private fun WalletItemEntity.toGroupDomainModel() = Group(
    id = id,
    name = name,
    groupId = groupId,
)

fun WalletItem.toEntity(): WalletItemEntity {
    return when(this) {
        is LoyaltyCard -> toLoyaltyCardEntity()
        is Note -> toNoteEntity()
        is Group -> toGroupEntity()
    }
}

private fun LoyaltyCard.toLoyaltyCardEntity(): WalletItemEntity {
    return WalletItemEntity(
        id = id,
        type = WalletItemEntity.Type.LOYALTY_CARD,
        name = name,
        value = value,
        groupId = groupId,
        barcodeType = barcodeType
    )
}

private fun Note.toNoteEntity(): WalletItemEntity {
    return WalletItemEntity(
        id = id,
        type = WalletItemEntity.Type.NOTE,
        name = name,
        value = value,
        groupId = groupId
    )
}

private fun Group.toGroupEntity(): WalletItemEntity {
    return WalletItemEntity(
        id = id,
        type = WalletItemEntity.Type.GROUP,
        name = name,
        groupId = groupId
    )
}