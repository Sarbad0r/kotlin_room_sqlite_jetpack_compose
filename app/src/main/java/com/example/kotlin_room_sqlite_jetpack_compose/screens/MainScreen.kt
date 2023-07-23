package com.example.kotlin_room_sqlite_jetpack_compose.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlin_room_sqlite_jetpack_compose.components.ListItemComponent
import com.example.kotlin_room_sqlite_jetpack_compose.view_model.MainViewModel
import kotlinx.coroutines.flow.count

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
) {
    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList()).value
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                modifier = Modifier.weight(1f),
                value = mainViewModel.textFieldText.value,
                onValueChange = { value ->
                    mainViewModel.textFieldText.value = value
                },
                label = { Text(text = "Name") },
                colors = TextFieldDefaults.textFieldColors(contentColorFor(backgroundColor = Color.Green))
            )
            IconButton(onClick = {
                Log.d("calling", "insert")
                mainViewModel.addNewItem()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "icon_for_add")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        mainViewModel.updateNewEntity.value?.let {
            Row() {
                Text(text = "${it.name}", style = TextStyle(color = Color.Blue))
                IconButton(onClick = { mainViewModel.updateNewEntity.value = null }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete_update")
                }
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items = itemsList) { _, item ->
                ListItemComponent(nameEntity = item)
            }
        }
    }
}