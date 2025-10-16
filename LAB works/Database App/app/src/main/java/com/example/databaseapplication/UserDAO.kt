package com.example.databaseapplication

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDAO {
    @Insert
    suspend fun insertUser( // suspend to run in background
        user: User
    )
}