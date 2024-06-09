package com.github.ilikeyourhat.fmnw.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StoredCode::class],
    version = 1
)
abstract class ForgetMeNotWalletDatabase : RoomDatabase() {
    abstract fun storedCodeDao(): StoredCodeDao
}
