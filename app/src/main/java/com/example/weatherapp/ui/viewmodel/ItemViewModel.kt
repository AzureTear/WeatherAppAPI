package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.model.Item
import com.example.weatherapp.data.remote.dao.ItemDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemViewModel(private val dao: ItemDao): ViewModel() {

    val items = dao.getAllItems()
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList())

    fun addItem(region: String, comuna: String){
        viewModelScope.launch {
            dao.addItem(Item(region = region, comuna = comuna, temperatura = 0, viento = 0, humedad = 0))
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch {
            dao.deleteItem(item)
        }
    }
}