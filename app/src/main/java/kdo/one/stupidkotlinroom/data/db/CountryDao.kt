package kdo.one.stupidkotlinroom.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kdo.one.stupidkotlinroom.data.model.CountryModel

@Dao
interface CountryDao {
    @Query("SELECT * FROM COUNTRIES")
    fun getAllCountries(): LiveData<List<CountryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountries(list: List<CountryModel>)

    @Query("DELETE FROM COUNTRIES")
    fun deleteAllCountries()
}