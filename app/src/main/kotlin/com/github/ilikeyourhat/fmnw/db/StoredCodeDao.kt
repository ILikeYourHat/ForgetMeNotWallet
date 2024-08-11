package com.github.ilikeyourhat.fmnw.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StoredCodeDao {
    @Query("SELECT * FROM stored_codes")
    fun getAll(): Flow<List<StoredCode>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(code: StoredCode)

    @Query("DELETE FROM stored_codes WHERE id = :id")
    suspend fun delete(id: Int)
}