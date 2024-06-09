package com.github.ilikeyourhat.fmnw.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StoredCodeDao {
    @Query("SELECT * FROM stored_codes")
    suspend fun getAll(): List<StoredCode>

    @Insert
    suspend fun insert(code: StoredCode)
}