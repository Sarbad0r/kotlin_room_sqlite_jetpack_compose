package com.example.kotlin_room_sqlite_jetpack_compose.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.kotlin_room_sqlite_jetpack_compose.App
import com.example.kotlin_room_sqlite_jetpack_compose.data.NameEntity
import com.example.kotlin_room_sqlite_jetpack_compose.data.db.MainDb
import kotlinx.coroutines.flow.Flow
import java.util.jar.Attributes.Name

@Suppress("UNCHECKED_CAST")
class MainViewModel(private val mainDb: MainDb) : ViewModel() {
    val itemsList: Flow<List<NameEntity>> = mainDb.ownDao.getItems()

    val textFieldText = mutableStateOf("");

    var updateNewEntity = mutableStateOf<NameEntity?>(null)

    fun addNewItem() {
        if (textFieldText.value.isEmpty()) return
        if (updateNewEntity.value != null) {
            updateNewEntity.value?.name = textFieldText.value;
            mainDb.ownDao.insertItem(updateNewEntity.value!!)
        } else {
            val newEntity = NameEntity(name = textFieldText.value)
            mainDb.ownDao.insertItem(newEntity)
        }
        updateNewEntity.value = null
        textFieldText.value = ""
    }

    fun deleteItem(nameEntity: NameEntity) {
        mainDb.ownDao.deleteItem(nameEntity)
    }

    //init MainViewModel with Database
    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras,
            ): T {
                val mainDb = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return MainViewModel(mainDb) as T
            }
        }
    }
}