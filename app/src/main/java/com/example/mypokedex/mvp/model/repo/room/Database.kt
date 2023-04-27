package com.example.mypokedex.mvp.model.repo.room

import androidx.room.RoomDatabase
import com.example.mypokedex.mvp.model.entity.Pokemon

@androidx.room.Database(
    entities = [
        Pokemon::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {

}