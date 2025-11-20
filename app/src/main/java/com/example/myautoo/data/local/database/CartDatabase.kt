package com.example.myautoo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myautoo.data.local.dao.CartDao
import com.example.myautoo.data.local.entity.CartItemEntity

@Database(
    entities = [CartItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CarDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
