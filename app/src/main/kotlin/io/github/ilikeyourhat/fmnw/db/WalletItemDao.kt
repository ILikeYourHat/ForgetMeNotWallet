package io.github.ilikeyourhat.fmnw.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletItemDao {

    @Query("SELECT * FROM wallet WHERE groupId IS :groupId")
    fun getAll(groupId: Long?): Flow<List<WalletItemEntity>>

    @Upsert
    suspend fun insertOrReplace(item: WalletItemEntity)

    @Query("DELETE FROM wallet WHERE id = :id")
    suspend fun delete(id: Long)
}