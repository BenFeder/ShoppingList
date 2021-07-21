package com.benfeder.shoppinglist.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groceries")
data class Grocery(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "grocery") val grocery: String,
    @ColumnInfo(name = "price") val price: Double = 5.00
)
