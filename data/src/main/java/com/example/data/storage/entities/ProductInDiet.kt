package com.example.data.storage.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "product_in_diet",
    primaryKeys = ["dietId", "productId"],
    foreignKeys = [
        ForeignKey(
            entity = Diet::class,
            parentColumns = ["id"],
            childColumns = ["dietId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Status::class,
            parentColumns = ["id"],
            childColumns = ["statusId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("dietId"),
        Index("productId"),
        Index("statusId")
    ]
)
data class ProductInDiet(
    val dietId: Long,
    val productId: Long,
    val statusId: Long? = null
)
