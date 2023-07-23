package com.example.kotlin_room_sqlite_jetpack_compose.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_room_sqlite_jetpack_compose.data.NameEntity


//if you have more tables set name of that class that was created for table into array
@Database(entities = [NameEntity::class], version = 1)
abstract class MainDb : RoomDatabase() {
    companion object {
        fun createDatabase(context: Context): MainDb {
            return Room.databaseBuilder(context,
                MainDb::class.java,
                "test.db").allowMainThreadQueries().build()
        }
    }

    abstract val ownDao: Dao
}