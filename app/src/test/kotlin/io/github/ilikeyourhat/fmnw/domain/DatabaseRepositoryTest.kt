package io.github.ilikeyourhat.fmnw.domain

import io.github.ilikeyourhat.fmnw.db.WalletItemDao
import io.github.ilikeyourhat.fmnw.db.WalletItemEntity
import io.github.ilikeyourhat.fmnw.model.Note
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DatabaseRepositoryTest {

    private val walletItemDao: WalletItemDao = mockk()
    private val repository = DatabaseRepository(walletItemDao)

    @Test
    fun `should save wallet item without id`() = runTest {
        val domainModel = Note()
        val slot = slot<WalletItemEntity>()
        coEvery { walletItemDao.insertOrReplace(capture(slot)) } just Runs

        repository.save(domainModel)

        coVerify { walletItemDao.insertOrReplace(any()) }
        slot.captured.let { entity ->
            assertEquals(null, entity.id)
            assertEquals(WalletItemEntity.Type.NOTE, entity.type)
        }
    }

    @Test
    fun `should save wallet item with id`() = runTest {
        val domainModel = Note(1234)
        val slot = slot<WalletItemEntity>()
        coEvery { walletItemDao.insertOrReplace(capture(slot)) } just Runs

        repository.save(domainModel)

        coVerify { walletItemDao.insertOrReplace(any()) }
        slot.captured.let { entity ->
            assertEquals(1234L, entity.id)
            assertEquals(WalletItemEntity.Type.NOTE, entity.type)
        }
    }

    @Test
    fun `should remove wallet item with id`() = runTest {
        val domainModel = Note(1234)
        coEvery { walletItemDao.delete(any()) } just Runs

        repository.delete(domainModel)

        coVerify { walletItemDao.delete(1234L) }
    }

    @Test
    fun `should do nothing when removing wallet item without id`() = runTest {
        val domainModel = Note()
        coEvery { walletItemDao.delete(any()) } just Runs

        repository.delete(domainModel)

        coVerify(exactly = 0) { walletItemDao.delete(any()) }
    }
}
