package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.model.Item

class SampleRepository {
    private val items = List(20) { index ->
        Item(
            id = index,
            region = "Region #$index",
            comuna = "Descripción detallada de la comuna $index.",
            temperatura = 16,
            viento= 180,
            humedad= 60
        )
    }

    fun getAll(): List<Item> = items
    fun getById(id: Int): Item? = items.find { it.id == id }
}