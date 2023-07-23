package com.example.kotlin_room_sqlite_jetpack_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlin_room_sqlite_jetpack_compose.data.NameEntity
import com.example.kotlin_room_sqlite_jetpack_compose.ui.theme.Purple200
import com.example.kotlin_room_sqlite_jetpack_compose.ui.theme.Teal200
import com.example.kotlin_room_sqlite_jetpack_compose.view_model.MainViewModel

@Composable
fun ListItemComponent(nameEntity: NameEntity?, mainViewModel: MainViewModel = viewModel()) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            mainViewModel.updateNewEntity.value = nameEntity
        }, backgroundColor = Purple200, shape = RoundedCornerShape(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            nameEntity?.let {
                Text(text = "${it.name}", modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(10.dp))
            }
            IconButton(onClick = { mainViewModel.deleteItem(nameEntity!!) }) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "delete_item",
                    tint = Color.White)
            }
        }
    }
}