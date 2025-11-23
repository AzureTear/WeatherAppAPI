package com.example.weatherapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.remote.model.Item
import com.example.weatherapp.ui.viewmodel.ItemViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedDaoScreen(viewModel: ItemViewModel){
    val items by viewModel.items.collectAsState()

    var region by remember { mutableStateOf( "") }
    var comuna by remember { mutableStateOf( "") }


    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Localizaciones")})
        }
    ) {padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ){
            OutlinedTextField(
                value = region,
                onValueChange = {region = it},
                label = { Text("Region")}
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = comuna,
                onValueChange = {comuna = it},
                label = { Text("Comuna")}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (region.isNotEmpty() && comuna.isNotEmpty()){
                        viewModel.addItem(region,comuna)
                        region = ""
                        comuna =""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Localizacion")
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(items){ item ->
                    ItemlItem(item = item, onDelete = {viewModel.deleteItem(it) })
                }
            }
        }

    }
}

@Composable
fun ItemlItem(item: Item, onDelete: (Item) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable{ onDelete(item) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)){
            Text(text = item.region, style = MaterialTheme.typography.bodyMedium)
            Text(text = item.comuna, style = MaterialTheme.typography.bodyMedium)
        }
    }
}