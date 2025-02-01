package io.github.ilikeyourhat.fmnw.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WalletItemEntity::class],
    version = 1
)
abstract class ForgetMeNotWalletDatabase : RoomDatabase() {
    abstract fun walletItemDao(): WalletItemDao
}
