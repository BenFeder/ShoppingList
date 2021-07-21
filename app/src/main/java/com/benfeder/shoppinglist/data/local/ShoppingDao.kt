package com.benfeder.shoppinglist.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ShoppingDao {

    @Query("SELECT * FROM groceries")
    abstract fun getAllGroceries(): Flow<List<Grocery>>

    @Query("SELECT * FROM groceries WHERE grocery = :grocery")
    abstract suspend fun getGroceryByName(grocery: String): List<Grocery>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertGroceries(grocery: List<Grocery>)

    @Query("DELETE FROM groceries")
    abstract suspend fun deleteAllGroceries()

    @Delete
    abstract suspend fun deleteGrocery(grocery: Grocery)

    @Update
    abstract suspend fun updateGrocery(grocery: Grocery)

    @Transaction
    open suspend fun loadGroceries(groceries: List<Grocery>) {
        deleteAllGroceries()
        insertGroceries(groceries)
    }
}