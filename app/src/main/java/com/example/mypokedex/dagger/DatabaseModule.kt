package com.example.mypokedex.dagger

import androidx.room.Room
import com.example.mypokedex.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) : Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build
}