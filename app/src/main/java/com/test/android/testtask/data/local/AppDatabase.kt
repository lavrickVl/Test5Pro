package com.test.android.testtask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.android.testtask.data.local.dao.UserDao
import com.test.android.testtask.data.local.entities.user.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],

    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object {
        @Volatile

        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_db.db"
            )
                .setJournalMode(JournalMode.TRUNCATE)
                .build()
        }

    }
}