package io.github.ilikeyourhat.fmnw.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ForgetMeNotWalletDatabase {
        return Room.databaseBuilder(
            context,
            ForgetMeNotWalletDatabase::class.java,
            name = "fmnw_db"
        )
            .fallbackToDestructiveMigration(false) // TODO remove before MVP launch
            .build()
    }

    @Provides
    fun provideWalletItemDao(database: ForgetMeNotWalletDatabase): WalletItemDao {
        return database.walletItemDao()
    }
}
