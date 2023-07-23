package com.example.kotlin_room_sqlite_jetpack_compose.data.db

import androidx.room.*
import com.example.kotlin_room_sqlite_jetpack_compose.data.NameEntity
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {

    //insert and update in one func
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(nameEntity: NameEntity)

    @Delete
    fun deleteItem(nameEntity: NameEntity)

    @Update
    suspend fun updateItem(nameEntity: NameEntity)


    //if you will update or insert item in table, you should not get all items from table
    //that's why we use Flow -> Flow will get only that items that is not in array
    @Query("SELECT * FROM test_table")
    fun getItems(): Flow<List<NameEntity>>
}