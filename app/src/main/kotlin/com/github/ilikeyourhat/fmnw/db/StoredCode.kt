package com.github.ilikeyourhat.fmnw.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stored_codes")
data class StoredCode(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: String
)
