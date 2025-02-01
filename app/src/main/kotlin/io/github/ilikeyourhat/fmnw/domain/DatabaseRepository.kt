package io.github.ilikeyourhat.fmnw.domain

import io.github.ilikeyourhat.fmnw.db.WalletItemDao
import io.github.ilikeyourhat.fmnw.model.Group
import io.github.ilikeyourhat.fmnw.model.WalletItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val walletItemDao: WalletItemDao
) {

    suspend fun save(item: WalletItem) {
        walletItemDao.insertOrReplace(item.toEntity())
    }

    fun getGroupContent(group: Group?): Flow<List<WalletItem>> {
        return walletItemDao.getAll(group?.id)
            .map { items ->
                items.map { it.toDomainModel() }
                    .sortedWith(compareBy({ it !is Group }, { it.name }))
            }
    }

    suspend fun delete(item: WalletItem) {
        val uuid = item.id ?: return
        walletItemDao.delete(uuid)
    }
}