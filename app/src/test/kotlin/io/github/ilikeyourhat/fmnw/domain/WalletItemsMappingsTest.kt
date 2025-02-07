package io.github.ilikeyourhat.fmnw.domain

import io.github.ilikeyourhat.fmnw.db.WalletItemEntity
import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.model.Note
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WalletItemsMappingsTest {

    @Test
    fun `should handle full note object`() {
        val domainModel = Note(
            id = 1234,
            name = "note",
            groupId = 4321,
            value = "testtest"
        )
        val entity = WalletItemEntity(
            id = 1234,
            type = WalletItemEntity.Type.NOTE,
            name = "note",
            groupId = 4321,
            value = "testtest",
            barcodeType = null
        )

        assertEquals(domainModel, entity.toDomainModel())
        assertEquals(entity, domainModel.toEntity())
    }

    @Test
    fun `should handle minimal note object`() {
        val domainModel = Note()
        val entity = WalletItemEntity(
            id = null,
            type = WalletItemEntity.Type.NOTE,
            name = "",
            groupId = null,
            value = "",
            barcodeType = null
        )

        assertEquals(domainModel, entity.toDomainModel())
        assertEquals(entity, domainModel.toEntity())
    }

    @Test
    fun `should handle full group object`() {
        val domainModel = Group(
            id = 1234,
            name = "group",
            groupId = 4321
        )
        val entity = WalletItemEntity(
            id = 1234,
            type = WalletItemEntity.Type.GROUP,
            name = "group",
            groupId = 4321,
            value = null,
            barcodeType = null
        )

        assertEquals(domainModel, entity.toDomainModel())
        assertEquals(entity, domainModel.toEntity())
    }

    @Test
    fun `should handle minimal group object`() {
        val domainModel = Group()
        val entity = WalletItemEntity(
            id = null,
            type = WalletItemEntity.Type.GROUP,
            name = "",
            groupId = null,
            value = null,
            barcodeType = null
        )

        assertEquals(domainModel, entity.toDomainModel())
        assertEquals(entity, domainModel.toEntity())
    }

    @Test
    fun `should handle full loyalty card object`() {
        val domainModel = LoyaltyCard(
            id = 1234,
            name = "note",
            groupId = 4321,
            value = "testtest",
            barcodeType = BarcodeModelType.AZTEC
        )
        val entity = WalletItemEntity(
            id = 1234,
            type = WalletItemEntity.Type.LOYALTY_CARD,
            name = "note",
            groupId = 4321,
            value = "testtest",
            barcodeType = BarcodeModelType.AZTEC
        )

        assertEquals(domainModel, entity.toDomainModel())
        assertEquals(entity, domainModel.toEntity())
    }

    @Test
    fun `should handle minimal loyalty card object`() {
        val domainModel = LoyaltyCard()
        val entity = WalletItemEntity(
            id = null,
            type = WalletItemEntity.Type.LOYALTY_CARD,
            name = "",
            groupId = null,
            value = "",
            barcodeType = null
        )

        assertEquals(domainModel, entity.toDomainModel())
        assertEquals(entity, domainModel.toEntity())
    }
}
