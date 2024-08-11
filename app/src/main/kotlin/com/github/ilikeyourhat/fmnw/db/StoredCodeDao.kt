package com.github.ilikeyourhat.fmnw.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface StoredCodeDao {
    @Query("SELECT * FROM stored_codes")
    fun getAll(): Flow<List<StoredCode>>

    @Upsert
    suspend fun insertOrReplace(code: StoredCode)

    @Query("DELETE FROM stored_codes WHERE id = :id")
    suspend fun delete(id: Int)
}