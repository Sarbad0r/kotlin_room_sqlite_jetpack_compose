package com.example.kotlin_room_sqlite_jetpack_compose.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//for creating table
//@Entity(tableName = "another_table_name") <- if you want to set another table name in your room database
@Entity(tableName = "test_table")
data class NameEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
//    @ColumnInfo(name = "another_name")// <- if you want to set another field in your room database
    var name: String? = null,
)