package com.example.kotlin_room_sqlite_jetpack_compose

import android.app.Application
import com.example.kotlin_room_sqlite_jetpack_compose.data.db.MainDb

class App : Application() {
    val database by lazy {
        MainDb.createDatabase(this)
    }
}