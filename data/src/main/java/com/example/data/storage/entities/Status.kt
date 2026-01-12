package com.example.data.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statuses")
data class Status(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)
