package kdo.one.stupidkotlinroom

import android.app.Application
import androidx.room.Room
import kdo.one.stupidkotlinroom.data.db.CountryDatabase

class MyRoomApplication: Application() {

    companion object {
        var database: CountryDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, CountryDatabase::class.java, "country_db").
                fallbackToDestructiveMigration().build()
    }
}