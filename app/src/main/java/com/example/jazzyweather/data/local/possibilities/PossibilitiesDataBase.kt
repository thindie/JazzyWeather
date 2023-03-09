package com.example.jazzyweather.data.local.possibilities

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [PossibilititesDbModel::class], exportSchema = false, version = 2)
abstract class PossibilitiesDataBase : RoomDatabase() {
    abstract fun bindPossibilitiesDao(): PossibilitiesDao
}