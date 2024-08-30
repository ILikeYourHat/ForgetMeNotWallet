package com.github.ilikeyourhat.fmnw.domain

import com.github.ilikeyourhat.fmnw.db.WalletItemDao
import com.github.ilikeyourhat.fmnw.model.WalletItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val walletItemDao: WalletItemDao
) {

    suspend fun save(item: WalletItem) {
        walletItemDao.insertOrReplace(item.toEntity())
    }

    fun getContent(): Flow<List<WalletItem>> {
        return walletItemDao.getAll(null)
            .map { storedCodes ->
                storedCodes.map { it.toDomainModel() }
            }
    }

    suspend fun delete(item: WalletItem) {
        val uuid = item.id ?: return
        walletItemDao.delete(uuid)
    }
}