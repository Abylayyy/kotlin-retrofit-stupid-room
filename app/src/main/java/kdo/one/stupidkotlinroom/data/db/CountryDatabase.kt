package kdo.one.stupidkotlinroom.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kdo.one.stupidkotlinroom.data.model.CountryModel

@Database(entities = [CountryModel::class], version = 1)
abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao
}