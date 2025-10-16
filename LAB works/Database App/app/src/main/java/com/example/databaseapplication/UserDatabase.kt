package com.example.databaseapplication

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        var INSTANCE: UserDatabase ?= null

        fun getInstance(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "akash_db"
                    ).build()

                INSTANCE = instance
                instance
            }
        }

    }
}