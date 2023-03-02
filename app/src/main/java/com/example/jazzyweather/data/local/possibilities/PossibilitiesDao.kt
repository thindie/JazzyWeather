package com.example.jazzyweather.data.local.possibilities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PossibilitiesDao {
    @Query("SELECT * FROM possibility ORDER by place DESC")
    suspend fun getSavedPossibilities(): List<PossibilititesDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPossibility(possibilititesDbModel: PossibilititesDbModel)
}