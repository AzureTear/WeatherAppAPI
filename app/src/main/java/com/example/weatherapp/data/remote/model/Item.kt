package com.example.weatherapp.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localizaciones")
data class Item (
    @PrimaryKey(true) val id: Int = 0,
    val region: String,
    val comuna: String,
    val temperatura: Int,
    val viento: Int,
    val humedad: Int
)