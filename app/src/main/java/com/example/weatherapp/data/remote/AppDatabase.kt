package com.example.weatherapp.data.remote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.data.remote.dao.ItemDao
import com.example.weatherapp.data.remote.model.Item

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun itemDao(): ItemDao

    companion object{
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "items_db"
                ).build().also { INSTANCE = it }
            }
        }

    }
}