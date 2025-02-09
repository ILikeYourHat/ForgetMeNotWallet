package io.github.ilikeyourhat.fmnw.domain

import io.github.ilikeyourhat.fmnw.db.WalletItemEntity
import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.model.LoyaltyCard
import io.github.ilikeyourhat.fmnw.model.Note
import io.github.ilikeyourhat.fmnw.model.WalletItem
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class WalletItemsMappingsTest {

    @Test
    fun `should handle full note object`() {
        Note(
            id = 1234,
            name = "note",
            groupId = 4321,
            value = "testtest"
        ).shouldMapFromAndTo(
            WalletItemEntity(
                id = 1234,
                type = WalletItemEntity.Type.NOTE,
                name = "note",
                groupId = 4321,
                value = "testtest",
                barcodeType = null
            )
        )
    }

    @Test
    fun `should handle minimal note object`() {
        Note().shouldMapFromAndTo(
            WalletItemEntity(
                id = null,
                type = WalletItemEntity.Type.NOTE,
                name = "",
                groupId = null,
                value = "",
                barcodeType = null
            )
        )
    }

    @Test
    fun `should handle full group object`() {
        Group(
            id = 1234,
            name = "group",
            groupId = 4321
        ).shouldMapFromAndTo(
            WalletItemEntity(
                id = 1234,
                type = WalletItemEntity.Type.GROUP,
                name = "group",
                groupId = 4321,
                value = null,
                barcodeType = null
            )
        )
    }

    @Test
    fun `should handle minimal group object`() {
        Group().shouldMapFromAndTo(
            WalletItemEntity(
                id = null,
                type = WalletItemEntity.Type.GROUP,
                name = "",
                groupId = null,
                value = null,
                barcodeType = null
            )
        )
    }

    @Test
    fun `should handle full loyalty card object`() {
        LoyaltyCard(
            id = 1234,
            name = "note",
            groupId = 4321,
            value = "testtest",
            barcodeType = BarcodeModelType.AZTEC
        ).shouldMapFromAndTo(
            WalletItemEntity(
                id = 1234,
                type = WalletItemEntity.Type.LOYALTY_CARD,
                name = "note",
                groupId = 4321,
                value = "testtest",
                barcodeType = BarcodeModelType.AZTEC
            )
        )
    }

    @Test
    fun `should handle minimal loyalty card object`() {
        LoyaltyCard().shouldMapFromAndTo(
            WalletItemEntity(
                id = null,
                type = WalletItemEntity.Type.LOYALTY_CARD,
                name = "",
                groupId = null,
                value = "",
                barcodeType = null
            )
        )
    }

    private fun WalletItem.shouldMapFromAndTo(entity: WalletItemEntity) {
        this.toEntity().shouldBe(entity)
        entity.toDomainModel().shouldBe(this)
    }
}
