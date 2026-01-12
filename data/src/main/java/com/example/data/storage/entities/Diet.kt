package com.example.data.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diets")
data class Diet(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)
