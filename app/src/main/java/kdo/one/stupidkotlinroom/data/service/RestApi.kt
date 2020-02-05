package kdo.one.stupidkotlinroom.data.service

import kdo.one.stupidkotlinroom.data.model.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {

    @GET("all")
    fun getAllCountries(): Call<List<CountryModel>>
}