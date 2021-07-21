package com.benfeder.shoppinglist.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.benfeder.shoppinglist.DATABASE_NAME

@Database(entities = arrayOf(Grocery::class), version = 1)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao

    companion object {
        private lateinit var application: Application
        private val database: ShoppingDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(application, ShoppingDatabase::class.java, DATABASE_NAME).build()
        }

        fun getDatabase(application: Application): ShoppingDatabase {
            this.application = application
            return database
        }
    }
}