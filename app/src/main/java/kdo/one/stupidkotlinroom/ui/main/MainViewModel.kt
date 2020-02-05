package kdo.one.stupidkotlinroom.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kdo.one.stupidkotlinroom.data.model.CountryModel
import kdo.one.stupidkotlinroom.ui.main.MainRepository

class MainViewModel: ViewModel() {
    lateinit var repository: MainRepository

    init {
        repository = MainRepository()
    }

    fun getAllCountryList(): LiveData<List<CountryModel>> = repository.getCountries()

    fun getCountriesFromApi() {
        repository.apiCallAndPut()
    }
}