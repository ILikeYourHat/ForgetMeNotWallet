package io.github.ilikeyourhat.fmnw.db

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import io.github.ilikeyourhat.fmnw.model.BarcodeModelType
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSingleElement
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

@ExtendWith(RobolectricExtension::class)
class WalletItemDaoTest {

    private lateinit var database: ForgetMeNotWalletDatabase
    private lateinit var dao: WalletItemDao

    @BeforeEach
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = DatabaseModule().provideDatabase(context)
        dao = DatabaseModule().provideWalletItemDao(database)
    }

    @AfterEach
    fun tearDown() {
        database.close()
    }

    @Test
    fun `should be empty at the beginning`() = runTest {
        getCurrentState()
            .shouldBeEmpty()
    }

    @Test
    fun `should add new item and assign an id`() = runTest {
        dao.insertOrReplace(EXAMPLE_WALLET_ITEM.copy(id = null))

        getCurrentState()
            .shouldHaveSingleElement(EXAMPLE_WALLET_ITEM.copy(id = 1))
    }

    @Test
    fun `should update existing item and assign an id`() = runTest {
        dao.insertOrReplace(EXAMPLE_WALLET_ITEM)

        getCurrentState()
            .shouldHaveSingleElement(EXAMPLE_WALLET_ITEM)

        dao.insertOrReplace(EXAMPLE_WALLET_ITEM.copy(name = "ugabuga"))

        getCurrentState()
            .shouldHaveSingleElement(EXAMPLE_WALLET_ITEM.copy(name = "ugabuga"))
    }

    @Test
    fun `should handle item with minimal data`() = runTest {
        dao.insertOrReplace(MINIMAL_WALLET_ITEM)

        getCurrentState()
            .shouldHaveSingleElement(MINIMAL_WALLET_ITEM)
    }

    @Test
    fun `should assign item to different group`() = runTest {
        dao.insertOrReplace(EXAMPLE_WALLET_ITEM.copy(groupId = 777))

        getCurrentState()
            .shouldBeEmpty()
        getCurrentState(groupId = 777)
            .shouldHaveSingleElement(EXAMPLE_WALLET_ITEM.copy(groupId = 777))
        getCurrentState(groupId = 123)
            .shouldBeEmpty()
    }

    @Test
    fun `should remove items based on id`() = runTest {
        dao.insertOrReplace(EXAMPLE_WALLET_ITEM)

        getCurrentState()
            .shouldHaveSingleElement(EXAMPLE_WALLET_ITEM)

        dao.delete(EXAMPLE_WALLET_ITEM.id!!)

        getCurrentState()
            .shouldBeEmpty()
    }

    @Test
    fun `should do nothing if given id is not present`() = runTest {
        dao.delete(1234) // no error

        getCurrentState()
            .shouldBeEmpty()
    }

    @Test
    fun `should handle flow of events`() = runTest {
        dao.getAll(null).test {
            awaitItem()
                .shouldBeEmpty()

            dao.insertOrReplace(EXAMPLE_WALLET_ITEM)
            awaitItem()
                .shouldContainExactly(EXAMPLE_WALLET_ITEM)

            dao.insertOrReplace(EXAMPLE_WALLET_ITEM.copy(id = 4321))
            awaitItem()
                .shouldContainExactly(EXAMPLE_WALLET_ITEM, EXAMPLE_WALLET_ITEM.copy(id = 4321))

            // different groupId, so it should not alter the current state
            // but because Room logic, a new state event is propagated anyway
            dao.insertOrReplace(EXAMPLE_WALLET_ITEM.copy(id = 666, groupId = 666))
            awaitItem()
                .shouldContainExactly(EXAMPLE_WALLET_ITEM, EXAMPLE_WALLET_ITEM.copy(id = 4321))

            dao.delete(EXAMPLE_WALLET_ITEM.id!!)
            awaitItem()
                .shouldContainExactly(EXAMPLE_WALLET_ITEM.copy(id = 4321))
        }
    }

    private suspend fun TestScope.getCurrentState(groupId: Long? = null): List<WalletItemEntity> {
        return dao.getAll(groupId)
            .flowOn(testScheduler)
            .take(1)
            .single()
    }

    private companion object {
        val EXAMPLE_WALLET_ITEM = WalletItemEntity(
            id = 1234,
            type = WalletItemEntity.Type.LOYALTY_CARD,
            name = "note",
            groupId = null,
            value = "testtest",
            barcodeType = BarcodeModelType.AZTEC
        )
        val MINIMAL_WALLET_ITEM = WalletItemEntity(
            id = 1234,
            type = WalletItemEntity.Type.GROUP,
            name = "some_group",
            groupId = null,
            value = null,
            barcodeType = null
        )
    }
}
