package com.example.databaseapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "csefusers")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val username: String,
    val userPhone: String,
)
